package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private Button button;
    int rawX=0;
    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mLayoutParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        //mListContainer =findViewById(R.id.container);
        mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        button=findViewById(R.id.button);

    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onButtonClick(View v) {
        if (FloatingButtonService.isStarted) {
            return;
        }
        if (!Settings.canDrawOverlays(this)) {
            Toast.makeText(this, "当前无权限，请授权", Toast.LENGTH_SHORT);
            startActivityForResult(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName())), 0);
        } else {
            Toast.makeText(this, "start successful", Toast.LENGTH_SHORT);
            startService(new Intent(MainActivity.this, FloatingButtonService.class));
        }



    }




}
