package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {
    private static final String TAG = "MainActivity";
    private Viewwindow mListContainer;
    private Button button;
    int rawX=0;
    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mLayoutParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        //mListContainer =findViewById(R.id.container);
        mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        button=findViewById(R.id.button);

    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onButtonClick(View v) {
        if (FloatingButtonService.isStarted) {
            return;
        }
        if (!Settings.canDrawOverlays(this)) {
            Toast.makeText(this, "当前无权限，请授权", Toast.LENGTH_SHORT);
            startActivityForResult(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName())), 0);
        } else {
            Toast.makeText(this, "start successful", Toast.LENGTH_SHORT);
            startService(new Intent(MainActivity.this, FloatingButtonService.class));
        }

//                mLayoutParams = new WindowManager.LayoutParams(
//                WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, 0, 0,
//                PixelFormat.TRANSPARENT);
//        mLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
//                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
//                | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;
//        mLayoutParams.type = WindowManager.LayoutParams.FIRST_SUB_WINDOW;
//
//        mLayoutParams.gravity = Gravity.LEFT | Gravity.TOP;
//        mLayoutParams.x = 0;
//        mLayoutParams.y = 300;
//        mLayoutParams.width=1000;
//        mLayoutParams.height=1000;
        //Log.d(TAG, "onTouch: "+"X"+mFloatingButton.getWidth()+"y"+mFloatingButton.getHeight());


//        mWindowManager.addView(mListContainer, mLayoutParams);
        //mListContainer.setOnTouchListener(this);

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
//        if(event.getAction()==MotionEvent.ACTION_DOWN){
//            rawX = (int) event.getRawX();
//            Log.d(TAG, "onTouch: "+rawX);
//        }else if(event.getAction()==MotionEvent.ACTION_MOVE){
//            if(rawX>button.getWidth()-100){
//                int x=(int) event.getRawX();
//                int changx=x-rawX;
//                button.setWidth(button.getWidth()+changx);
//                mWindowManager.updateViewLayout(button, mLayoutParams);
//                button.invalidate();
//                rawX = (int) event.getRawX();
//            }
//        }else if(event.getAction()==MotionEvent.ACTION_UP){
//            rawX=0;
//        }

        return false;
    }

}
