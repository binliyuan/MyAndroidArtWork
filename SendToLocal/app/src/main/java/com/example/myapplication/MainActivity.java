package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    TextView textView;
//    shuji
    SimpleDateFormat s_format = new SimpleDateFormat("hh时mm分ss秒");
    private Button btn;
    String b="";
    float x1 = 0;
    float x2 = 0;
    float x3 = 0;
    float y1 = 0;
    float y2 = 0;
    float y3 = 0;
    int i=0,j=0;
    Integer k=0;
    boolean j1=false;
    boolean j2=false;
    long startTime; // 获取开始时间
    long MiddleTime;
    long HoverTime;
    long sTime;
    long eTime;
    long stTime;
    long enTime;
    // doThing(); // 测试的代码段
    long endTime; // 获取结束时间
    int distance=10;
    int distance1=5;
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView =findViewById(R.id.text1);
       // save(textView.getText().toString());
       // save("111");



        btn =  findViewById(R.id.btn);
        btn.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                stTime=System.currentTimeMillis();
                Log.i("1aaaaaa","cccc"+(stTime-enTime));
//                Log.i("1a","dddd"+x1+"ddddd"+x2);
//
//                Log.i("1a","cccc"+x1+"ccccc"+x2);
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    startTime= System.currentTimeMillis();
                }
                else if(event.getAction() == MotionEvent.ACTION_MOVE){
                    sTime=System.currentTimeMillis();

                    if(j2==false){

                        MiddleTime= System.currentTimeMillis();
                        j++;
                        j2=true;
                    }
                    x1 = event.getX();
                    y1 = event.getY();
                    if(x2+distance>=x1&&x1>=x2-distance&&y2+distance>=y1&&y1>=y2-distance){
                        // Log.i("1aa","bbbbbb"+HoverTime);
                        if(i<1){
                            HoverTime=System.currentTimeMillis();
                            x3=x1;
                            y3=y1;
                        }
                        else if(i>=1){
                            if((x1<=x3-distance1||x1>=x3+distance1)&&(y1<=y3-distance1||y1>=y3+distance1)){
                                HoverTime=System.currentTimeMillis();
                            }
                            x3=x1;
                            y3=y1;
                        }

                        i++;
                    }
                    //Log.i("1aa","bbbbbb"+HoverTime);

                    x2 =x1;
                    y2 =y1;
                    eTime=System.currentTimeMillis();
                }
                else if(event.getAction() == MotionEvent.ACTION_UP){
                    endTime= System.currentTimeMillis();


                    Log.i("1aaa","点击"+startTime);
                    Log.i("1aaa","开始动"+MiddleTime);
                    Log.i("1aaa","动到不动"+HoverTime);
                    Log.i("1aaa","抬起"+endTime);
                    Log.i("1aaa","----------");
                    if(MiddleTime==0||HoverTime==0){

                        if(HoverTime==0&&MiddleTime!=0){
                            //b="无悬停状态\n";
                            b = s_format.format(new Date()) + ":" + "点击" + (MiddleTime - startTime) + "Millisecond" + "\n";

                            b += s_format.format(new Date()) + ":" + "抬起" + (endTime - MiddleTime) + "Millisecond" + "\n";

                            b += s_format.format(new Date()) + ":" + "悬停" + "0" + "Millisecond" + "\n";
                            b += s_format.format(new Date()) + ":" + "总共" + (endTime - startTime) + "Millisecond" + "\n";
                            b += "\n\n\n";

                        }else if(HoverTime==0&&MiddleTime==0){
                            b="只是点击\n";
                            b += s_format.format(new Date()) + ":" + "总共" + (endTime - startTime) + "Millisecond" + "\n";
                            b += "\n\n\n";

                            // b += s_format.format(new Date()) + ":" + "抬起" + (endTime - MiddleTime) + "Millisecond" +"次数"+j+ "\n";

                        }

                    }else {


                        //if (MiddleTime != 0 && startTime != 0) {
                        b = s_format.format(new Date()) + ":" + "点击" + (MiddleTime - startTime) + "Millisecond" + "\n";
                        //}
                        //b+=s_format.format(new Date())+":"+"移动"+(endTime-MiddleTime)+"Millisecond"+"\n";
                        // if (HoverTime != 0 && MiddleTime != 0) {
                        b += s_format.format(new Date()) + ":" + "移动" + (HoverTime - MiddleTime) + "Millisecond" + "\n";
                        //}
                        //if (endTime != 0 && HoverTime != 0) {
                        b += s_format.format(new Date()) + ":" + "悬停" + (endTime - HoverTime) + "Millisecond" + "\n";
                        b += s_format.format(new Date()) + ":" + "总共" + (endTime - startTime) + "Millisecond" + "\n";
                        b += "\n\n\n";
                        //  }

                        Log.i("1aa", "aaaaaa" + HoverTime);
                        Log.i("1aa", "cccccc" + MiddleTime);


                    }
                    k++;
                    j1 = j2 = false;
                    y2 = x2 = 0;
                    btn.setText(k.toString());
                    save(b);
                    textView.setText(b);
                    b = "";
                    HoverTime = 0;
                    MiddleTime = 0;
                    startTime = 0;
                    endTime = 0;
                    i-=0;j=0;
                }
                enTime=System.currentTimeMillis();
                return true;
            }
        });
    }

    public void save(String inputText) {
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {
            out = openFileOutput("data1", Context.MODE_APPEND);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(inputText);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
