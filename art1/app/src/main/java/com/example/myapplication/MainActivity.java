package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.gesture.Gesture;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.Scroller;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private VelocityTracker mVelocityTracker;
    private GestureDetector mGestureDetector;
    private CustomView customView;
//    private Scroller mScroller = new Scroller(null);
   // private Scroller mScroller = new Scroller(context);



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("xw", "onTouchEvent");

        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();//获得VelocityTracker类实例
        }
        mVelocityTracker.addMovement(event);
        final VelocityTracker velocityTracker = mVelocityTracker;
        velocityTracker.computeCurrentVelocity(1, (float)0.01);//设置maxVelocity值为0.1时，速率大于0.01时，显示的速率都是0.01,速率小于0.01时，显示正常
       // Log.i("test","velocityTraker"+velocityTracker.getXVelocity());
        velocityTracker.computeCurrentVelocity(1000); //设置units的值为1000，意思为一秒时间内运动了多少个像素
      //  Log.i("test","velocityTraker"+velocityTracker.getXVelocity());
        int xVelocity = (int) velocityTracker.getXVelocity();
        int yVelocity = (int) velocityTracker.getYVelocity();
        Log.i("test","velocityTraker1111"+velocityTracker.getXVelocity());
        Log.i("test","velocityTraker1111"+velocityTracker.getYVelocity());




        return super.onTouchEvent(event);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("xw", "OnTouchListener");
        textView=findViewById(R.id.text);
        customView=findViewById(R.id.customView);
        customView.smoothScrollTo(500,400);
        customView.computeScroll();
        mGestureDetector = new GestureDetector(this, new MyOnGestureListener());
        textView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.e("xw", "OnTouchListener");
                Log.i(getClass().getName(), "onTouch-----" + getActionName(event.getAction()));
                mGestureDetector.onTouchEvent(event);
                return false;
            }
        });
        textView.scrollTo(400,500);
        textView.computeScroll();

    }


    private String getActionName(int action) {
        String name = "";
        switch (action) {
            case MotionEvent.ACTION_DOWN: {
                name = "ACTION_DOWN";
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                name = "ACTION_MOVE";
                break;
            }
            case MotionEvent.ACTION_UP: {
                name = "ACTION_UP";
                break;
            }
            default:
                break;
        }
        return name;
    }

    class MyOnGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            Log.i(getClass().getName(), "onSingleTapUp-----" + getActionName(e.getAction()));
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            Log.i(getClass().getName(), "onLongPress-----" + getActionName(e.getAction()));
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.i(getClass().getName(),
                    "onScroll-----" + getActionName(e2.getAction()) + ",(" + e1.getX() + "," + e1.getY() + ") ,("
                            + e2.getX() + "," + e2.getY() + ")");
            return false;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.i(getClass().getName(),
                    "onFling-----" + getActionName(e2.getAction()) + ",(" + e1.getX() + "," + e1.getY() + ") ,("
                            + e2.getX() + "," + e2.getY() + ")");
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {
            Log.i(getClass().getName(), "onShowPress-----" + getActionName(e.getAction()));
        }

        @Override
        public boolean onDown(MotionEvent e) {
            Log.i(getClass().getName(), "onDown-----" + getActionName(e.getAction()));
            return false;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Log.i(getClass().getName(), "onDoubleTap-----" + getActionName(e.getAction()));
            return false;
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            Log.i(getClass().getName(), "onDoubleTapEvent-----" + getActionName(e.getAction()));
            return false;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            Log.i(getClass().getName(), "onSingleTapConfirmed-----" + getActionName(e.getAction()));
            return false;
        }
    }
}
