package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewGroup content = (ViewGroup) findViewById(android.R.id.content);
        content.setBackgroundColor(Color.RED);

        int childCount = content.getChildCount();
        Log.e("count", "" + childCount);
        View layout = content.getChildAt(0);//这里就是布局中的ConstraintLayout
        //Button btn = (Button) layout.findViewById(R.id.button);//这个就是ContrainLayout中的那个按钮
        //btn.setText("my is button");
    }
}
