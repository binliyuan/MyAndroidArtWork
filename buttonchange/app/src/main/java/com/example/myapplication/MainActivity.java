package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    Button button ;
    int rawX=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        button.setText("love me");
        button.setWidth(1000);
        button.setHeight(1000);
        button.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    rawX = (int) event.getRawX();
                    Log.d(TAG, "onTouch: "+rawX);
                }else if(event.getAction()==MotionEvent.ACTION_MOVE){
                    if(rawX>900){
                        int x=(int) event.getRawX();
                        int changx=x-rawX;
                        button.setWidth(1000+changx);
                        button.invalidate();
                    }
                }else if(event.getAction()==MotionEvent.ACTION_UP){
                    rawX=0;
                }
                return false;
            }
        });
    }
}
