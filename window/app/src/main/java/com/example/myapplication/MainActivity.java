package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button1);
        Log.d(TAG, "onTouch: "+"X"+button.getWidth()+"y"+button.getHeight());
    }
    public void onButtonClick(View v) {
        if (v.getId() == R.id.button1) {
            Log.d(TAG, "onTouch: "+"X"+button.getWidth()+"y"+button.getHeight());
            Intent intent = new Intent(this, TestActivity.class);
            startActivity(intent);
        }else if (v.getId() == R.id.button2) {
            Intent intent = new Intent(this, demoActivity.class);
            startActivity(intent);
        }

    }

}
