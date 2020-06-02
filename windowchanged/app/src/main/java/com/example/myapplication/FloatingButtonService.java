package com.example.myapplication;

import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import static android.content.ContentValues.TAG;

public class FloatingButtonService extends Service implements View.OnTouchListener {


    public static boolean isStarted = false;
    private WindowManager windowManager;
    private WindowManager.LayoutParams layoutParams;
    private MyView button;
    private LinearLayout linearLayout;

    int x1=0;
    int y1=0;

    public FloatingButtonService() {


    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        isStarted=true;
        System.out.println("onStartCommand执行了");
        Toast.makeText(this, "start successful", Toast.LENGTH_SHORT).show();
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        layoutParams = new WindowManager.LayoutParams();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            layoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            layoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        }
        layoutParams.format = PixelFormat.RGBA_8888;
        layoutParams.gravity = Gravity.LEFT | Gravity.TOP;
        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        final int screenWidth = MyUtils.getScreenMetrics(this).widthPixels;
        final int screenHeight = MyUtils.getScreenMetrics(this).heightPixels;

        layoutParams.width = 1000;
        layoutParams.height = 1000;
        layoutParams.x = 100;
        layoutParams.y = 100;



    }



    @RequiresApi(api = Build.VERSION_CODES.M)
    private void showFloatingWindow() {
        if (Settings.canDrawOverlays(this)) {
            button = new MyView(getApplicationContext());

            LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
            View layout =  inflater.inflate(
                    R.layout.mylayout, button, false);
            //layout.setLayoutParams(new LinearLayout.LayoutParams(1000, 1000));
            //layout.setBackgroundColor(Color.GREEN);
            button.setBackgroundColor(Color.GREEN);
            button.addView(layout);

            button.setOnTouchListener(this);
            windowManager.addView(button, layoutParams);
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        showFloatingWindow();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        windowManager.removeViewImmediate(button);
        System.out.println("onDestroy执行了");
        Toast.makeText(this, "stop successful", Toast.LENGTH_SHORT).show();

    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int rawX = (int) event.getRawX();
        int rawY = (int) event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                x1=(int) event.getRawX();
                y1=(int) event.getRawY();
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                int x = (int) event.getRawX();
                int y = (int) event.getRawY();
                int changex = x - x1;
                int changey = y - y1;
                Log.d(TAG, "move width" + (layoutParams.width));
                Log.d(TAG, "move lx" + (layoutParams.x));
                Log.d(TAG, "move hight" + (layoutParams.height));
                Log.d(TAG, "move x" + x);
                if(x<(layoutParams.x+layoutParams.width)-100) {
                    if((layoutParams.x + changex)>0&&(layoutParams.y + changey)>0){
                        layoutParams.x = layoutParams.x + changex;
                        layoutParams.y = layoutParams.y + changey;

//                        button.setLayoutParams(new LinearLayout.LayoutParams(button.getChildAt(0).getWidth(),
//                                button.getChildAt(0).getHeight()));
                        layoutParams.width=button.getChildAt(0).getWidth();
                        layoutParams.height=button.getChildAt(0).getHeight();
                        Log.d(TAG, "linearLayout.getWidth()= " +button.getChildAt(0).getWidth());
                        //Log.d(TAG, "linearLayout.getWidth()= " +linearLayout.getWidth() );

                        windowManager.updateViewLayout(button, layoutParams);
                    }

                }else{
                    if(layoutParams.width+changex>0&&layoutParams.height+changey>0) {
                        layoutParams.width = layoutParams.width + changex;
                        layoutParams.height = layoutParams.height + changey;
//                        button.setLayoutParams(new LinearLayout.LayoutParams(button.getChildAt(0).getWidth(),
//                                button.getChildAt(0).getHeight()));
                        layoutParams.width=button.getChildAt(0).getWidth();
                        layoutParams.height=button.getChildAt(0).getHeight();
                        Log.d(TAG, "linearLayout.getWidth()= " +button.getChildAt(0).getWidth());

                        windowManager.updateViewLayout(button, layoutParams);
                    }

                }
                x1 = (int) event.getRawX();
                y1 = (int) event.getRawY();
                break;
            }
            case MotionEvent.ACTION_UP: {
                x1=0;
                y1=0;
                break;
            }
            default:
                break;
        }
        return false;
    }
}


