package com.example.myapplication;



import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class TestActivity extends Activity implements View.OnTouchListener {

    private static final String TAG = "TestActivity";

    private Button mCreateWindowButton;
    private boolean a;
    private Button mFloatingButton;

    private WindowManager.LayoutParams mLayoutParams;

    private WindowManager mWindowManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initView();
        //Log.d(TAG, "onTouch: "+"X"+mCreateWindowButton.getWidth()+"y"+mCreateWindowButton.getHeight());
    }

    private void initView() {
        mCreateWindowButton = (Button) findViewById(R.id.button1);
        mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
    }

    public void onButtonClick(View v) {
        if (v == mCreateWindowButton) {
            mFloatingButton = new Button(this);
            //mFloatingButton.setText("click me");
            mFloatingButton.setWidth(1000);
            mFloatingButton.setHeight(1000);
           // Log.d(TAG, "onTouch: "+"X"+mCreateWindowButton.getWidth()+"y"+mCreateWindowButton.getHeight());
            mLayoutParams = new WindowManager.LayoutParams(
                    WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, 0, 0,
                    PixelFormat.TRANSPARENT);
            mLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                    | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                    | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;
            mLayoutParams.type = WindowManager.LayoutParams.FIRST_SUB_WINDOW;

            mLayoutParams.gravity = Gravity.LEFT | Gravity.TOP;
            mLayoutParams.x = 100;
            mLayoutParams.y = 300;
            //Log.d(TAG, "onTouch: "+"X"+mFloatingButton.getWidth()+"y"+mFloatingButton.getHeight());

            mFloatingButton.setOnTouchListener(this);
            mWindowManager.addView(mFloatingButton, mLayoutParams);

        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int rawX = (int) event.getRawX();
        int rawY = (int) event.getRawY();
        mLayoutParams.width=mFloatingButton.getWidth();
        mLayoutParams.height=mFloatingButton.getHeight();
        Log.d(TAG, "onTouch:111111 "+mFloatingButton.getWidth());
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                mFloatingButton.setBackgroundColor(Color.YELLOW);
                int x = (int) event.getX();
                int y = (int) event.getY();
                if(x>mLayoutParams.x&&y>mLayoutParams.y&&x<(mLayoutParams.x+mLayoutParams.width)
                &&x<(mLayoutParams.y+mLayoutParams.height)){
                    Log.d(TAG, "onTouch: ");
                    if(x>(mLayoutParams.x+mLayoutParams.width-500)&&x<(mLayoutParams.x+mLayoutParams.width)){
                        Log.d(TAG, "onTouch:111 ");
                        a=true;

                    }

                }
//                Log.d(TAG, "onTouch: "+(mLayoutParams.x+mLayoutParams.width));
//                if(x>mLayoutParams.x&&y>mLayoutParams.y){
//                    Log.d(TAG, "onTouch: ");
//                }
                //Log.d(TAG, "onTouch: "+"X"+x+"y"+y);
                //Log.d(TAG, "onTouch: "+"X"+mLayoutParams.x+"y"+mLayoutParams.y);
               // Log.d(TAG, "onTouch: "+"X"+(mFloatingButton.getWidth())+"y"+(mFloatingButton.getHeight()));
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                int x = (int) event.getX();
                int y = (int) event.getY();
                if(a==true){
                    Log.d(TAG, "onTouch:111111 ");
                    int changex=x-mLayoutParams.x;
                    int changey=y-mLayoutParams.y;
                    mFloatingButton.setWidth(1000-changex);
                    Log.d(TAG, "onTouch: "+changex);
                }

                //mLayoutParams.x = rawX;
                //mLayoutParams.y = rawY;
                mWindowManager.updateViewLayout(mFloatingButton, mLayoutParams);
                break;
            }
            case MotionEvent.ACTION_UP: {
                a=false;
                break;
            }
            default:
                break;
        }

        return false;
    }

    @Override
    protected void onDestroy() {
        try {
            mWindowManager.removeView(mFloatingButton);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }
}
