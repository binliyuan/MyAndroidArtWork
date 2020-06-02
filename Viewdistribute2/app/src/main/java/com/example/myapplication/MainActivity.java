package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {

    private myview mListContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LayoutInflater inflater = getLayoutInflater();
        mListContainer =  findViewById(R.id.container);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.container, mListContainer, false);
        layout.getLayoutParams().width = 1000;
        layout.setBackgroundColor(Color.rgb(255 , 255 , 0));
        mListContainer.addView(layout);
        ViewGroup layout1 = (ViewGroup) inflater.inflate(R.layout.container, mListContainer, false);
        layout1.getLayoutParams().width = 1000;
        layout1.setBackgroundColor(Color.rgb(255 , 255 , 0));
        mListContainer.addView(layout1);

    }
}
