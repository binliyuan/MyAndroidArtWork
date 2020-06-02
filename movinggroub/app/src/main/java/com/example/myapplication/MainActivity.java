package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "DemoActivity_2";

    private  MyView mmyView;
    private Button textView;
    private LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }
    private void initView() {
        LayoutInflater inflater = getLayoutInflater();
        mmyView =  findViewById(R.id.my);

        View layout =  inflater.inflate(
                R.layout.mylayout, mmyView, false);
        //layout.setLayoutParams(new LinearLayout.LayoutParams(1000, 1000));
        layout.setBackgroundColor(Color.GREEN);


        mmyView.addView(layout);
        mmyView.setBackgroundColor(Color.RED);
        textView = findViewById(R.id.text);
        linearLayout=findViewById(R.id.line1);

        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(1000, 1000));
        //textView.setBackgroundColor(Color.YELLOW);
    }
}
