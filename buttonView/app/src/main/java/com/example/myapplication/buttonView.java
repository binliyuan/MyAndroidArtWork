package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

@SuppressLint("AppCompatCustomView")
public class buttonView extends Button {
    public buttonView(Context context) {
        super(context);
    }

    public buttonView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public buttonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



}
