package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.InputDevice;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private String TAG = "frist";
    private LinearLayout layout;

    private Button scrollToBtn;

    private Button scrollByBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout = (LinearLayout) findViewById(R.id.layout);
        scrollToBtn = (Button) findViewById(R.id.scroll_to_btn);
        scrollByBtn = (Button) findViewById(R.id.scroll_by_btn);
        //ObjectAnimator.ofFloat(scrollByBtn,"translationX",0,100)
        //        .setDuration(1000).start();
        scrollToBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.scrollTo(-60, -100);
                Log.d(TAG, "onClick: "+ InputDevice.SOURCE_MOUSE);
            }
        });
        scrollByBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.scrollBy(-60, -100);
                Log.d(TAG, "onClick: "+ InputDevice.SOURCE_MOUSE);
            }
        });
        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

    }

}
