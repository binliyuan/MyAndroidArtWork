package com.example.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyView extends ViewGroup {
    private int mChildrenSize;
    private int mChildWidth;
    private View view;
    int rawX=0;
    int rawY=0;
    int i=0;
    int x1=0;
    int y1=0;
    boolean j=true;
    boolean intercepted;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    String str = formatter.format(new Date());
    private static final String TAG = "MyView";
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childLeft = 0;
        final int childCount = getChildCount();
        mChildrenSize = childCount;

        for (int i = 0; i < childCount; i++) {
            final View childView = getChildAt(i);
            if (childView.getVisibility() != View.GONE) {
                final int childWidth = childView.getMeasuredWidth();
                mChildWidth = childWidth;
                childView.layout(childLeft, 0, childLeft + childWidth,
                        childView.getMeasuredHeight());
                childLeft += childWidth;
            }
        }
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        intercepted = false;
        rawX = (int) event.getRawX();
        rawY = (int) event.getRawY();
        int x = (int) event.getX();
        int y = (int) event.getY();
        final int childCount = getChildCount();
        view=getChildAt(0);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                if((rawX>view.getWidth()-100)) {
                    intercepted = true;
                }
                if((rawY>view.getHeight()-100)){
                    intercepted = true;
                }
                x1=view.getWidth();
                y1=view.getHeight();
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                //intercepted = true;
                break;
            }
            case MotionEvent.ACTION_UP: {
                //intercepted = true;
            }
            default:
                break;
        }

        Log.d(TAG, "intercepted=" + intercepted);


        return intercepted;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        rawX = (int) event.getRawX();
        rawY = (int) event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                view.setBackgroundColor(Color.YELLOW);
                break;
            }
            case MotionEvent.ACTION_MOVE: {

                if((rawX>view.getWidth()-100)){
                    Log.d(TAG, "onTouch: "+view.getWidth());
                    int x=(int) event.getRawX();
                    Log.d(TAG, "onTouch:x "+x);
                    Log.d(TAG, "onTouch:rawX "+x1);
                    int changx=x-x1;
                    view.setLayoutParams(new LinearLayout.LayoutParams((view.getWidth()+changx),view.getHeight()));
                    Log.d(TAG, "onTouch: "+(changx));


                    //view.invalidate();
                    x1=(int) event.getRawX();

                   // rawX = (int) event.getRawX();
                }
                if((rawY>view.getHeight()-100)){
                    Log.d(TAG, "onTouch: "+view.getHeight());
                    int y=(int) event.getRawY();
                    Log.d(TAG, "onTouch:y "+y);
                    Log.d(TAG, "onTouch:rawY "+y1);
                    int changy=y-y1;
                    view.setLayoutParams(new LinearLayout.LayoutParams(view.getWidth(),(view.getHeight()+changy)));
                    Log.d(TAG, "onTouch: "+(changy));


                    //view.invalidate();
                    y1=(int) event.getRawY();
                }
                break;
            }
            case MotionEvent.ACTION_UP: {
                rawX=0;
                rawY=0;
                i=0;
                j=false;
                break;
            }
            default:
                break;
        }


        return true;

    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measuredWidth = 0;
        int measuredHeight = 0;
        final int childCount = getChildCount();
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int widthSpaceSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSpaceSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        if (childCount == 0) {
            setMeasuredDimension(0, 0);
        } else if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST) {
            final View childView = getChildAt(0);
            measuredWidth = childView.getMeasuredWidth() * childCount;
            measuredHeight = childView.getMeasuredHeight();
            setMeasuredDimension(measuredWidth, measuredHeight);
        } else if (heightSpecMode == MeasureSpec.AT_MOST) {
            final View childView = getChildAt(0);
            measuredHeight = childView.getMeasuredHeight();
            setMeasuredDimension(widthSpaceSize, childView.getMeasuredHeight());
        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
            final View childView = getChildAt(0);
            measuredWidth = childView.getMeasuredWidth() * childCount;
            setMeasuredDimension(measuredWidth, heightSpaceSize);
        }
    }
}
