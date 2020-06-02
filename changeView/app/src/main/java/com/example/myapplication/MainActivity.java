package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    Button button ;
    Button button1 ;
    LinearLayout linearLayout;
    int rawX=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout=findViewById(R.id.linearlayout);
        linearLayout.setBackgroundColor(Color.YELLOW);
        button = findViewById(R.id.button);
        button1=findViewById(R.id.button1);
        button.setText("love me");
        button1.setText("love you");
        button.setWidth(100);
        button.setHeight(100);
        button1.setWidth(100);
        button1.setHeight(100);
        button.setBackgroundColor(Color.RED);
        button1.setBackgroundColor(Color.GREEN);

        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    rawX = (int) event.getRawX();
                    Log.d(TAG, "onTouch: "+linearLayout.getWidth());
                }else if(event.getAction()==MotionEvent.ACTION_MOVE){
                    if(rawX>button.getWidth()-100){

                        int x=(int) event.getRawX();
                        int changx=x-rawX;
                        button.setWidth(button.getWidth()+changx);
                        button.invalidate();
                        button1.setWidth(button1.getWidth()+changx);
                        button1.invalidate();
                        rawX = (int) event.getRawX();
                    }
                }else if(event.getAction()==MotionEvent.ACTION_UP){
                    rawX=0;
                }
                return false;
            }
        });
    }
}