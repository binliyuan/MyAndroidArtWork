package com.example.myapplication;

import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

public class FloatingButtonService extends Service {


    public static boolean isStarted = false;
    private WindowManager windowManager;
    private WindowManager.LayoutParams layoutParams;
    private Button button;
    private LinearLayout linearLayout;
    private Viewwindow mListContainer;
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
        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        layoutParams.width = 1000;
        layoutParams.height = 1000;
        layoutParams.x = 300;
        layoutParams.y = 300;


    }



    @RequiresApi(api = Build.VERSION_CODES.M)
    private void showFloatingWindow() {
        if (Settings.canDrawOverlays(this)) {
            button = new Button(getApplicationContext());
            button.setText("Floating Window");
            button.setBackgroundColor(Color.BLUE);
            mListContainer = new Viewwindow(getApplicationContext());
            mListContainer.setBackgroundColor(Color.YELLOW);
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


}
