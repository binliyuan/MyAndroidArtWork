package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private buttonView mbuttonView;
    private Button mbuttonView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mbuttonView =findViewById(R.id.button);
        mbuttonView1 =findViewById(R.id.button1);
        mbuttonView.setWidth(1000);
        mbuttonView1.setBackgroundColor(Color.RED);
        mbuttonView.setBackgroundColor(Color.YELLOW);
    }
}
