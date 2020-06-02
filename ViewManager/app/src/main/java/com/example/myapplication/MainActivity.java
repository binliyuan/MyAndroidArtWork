package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {
    private static final String TAG = "MainActivity";
    Button mbutton ;
    Button button ;
    int rawX=0;
    private WindowManager.LayoutParams mLayoutParams;
    private WindowManager mWindowManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();


    }
    private void initView() {
        mbutton =(Button) findViewById(R.id.button);
        mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
    }
    public void onButtonClick(View v) {
        button = new Button(this);
        button.setHeight(1000);
        button.setWidth(1000);
        mLayoutParams = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, 0, 0,
                PixelFormat.TRANSPARENT);
        mLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;
        mLayoutParams.type = WindowManager.LayoutParams.FIRST_SUB_WINDOW;

        mLayoutParams.gravity = Gravity.LEFT | Gravity.TOP;
        mLayoutParams.x = 0;
        mLayoutParams.y = 300;
        //Log.d(TAG, "onTouch: "+"X"+mFloatingButton.getWidth()+"y"+mFloatingButton.getHeight());

        button.setOnTouchListener(this);
        mWindowManager.addView(button, mLayoutParams);

        }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            rawX = (int) event.getRawX();
            Log.d(TAG, "onTouch: "+rawX);
        }else if(event.getAction()==MotionEvent.ACTION_MOVE){
            if(rawX>button.getWidth()-100){
                int x=(int) event.getRawX();
                int changx=x-rawX;
                button.setWidth(button.getWidth()+changx);
                mWindowManager.updateViewLayout(button, mLayoutParams);
                button.invalidate();
                rawX = (int) event.getRawX();
            }
        }else if(event.getAction()==MotionEvent.ACTION_UP){
            rawX=0;
        }

        return false;
    }
}
