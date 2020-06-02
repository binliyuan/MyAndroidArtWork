package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Fruit> fruitList = new ArrayList<Fruit>();
    FruitAdapter adapter;
    SimpleDateFormat s_format = new SimpleDateFormat("hh时mm分ss秒");
    private Button btn;
    private TextView textView;
    ListView listView;
    String b="";
    float x1 = 0;
    float x2 = 0;
    float x3 = 0;
    float y1 = 0;
    float y2 = 0;
    float y3 = 0;
    int i=0,j=0;
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
    TextView responseText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button sendRequest = (Button) findViewById(R.id.send_request);
        responseText = (TextView) findViewById(R.id.response_text);
        sendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.send_request) {
//            sendRequestWithHttpURLConnection();
                    Log.d("haha","go");
                    run1();
                }
            }
        });
        btn =  findViewById(R.id.btn);
        textView=findViewById(R.id.text);

        listView = (ListView) findViewById(R.id.list_view);
        adapter= new FruitAdapter(MainActivity.this, R.layout.fruit_item, fruitList);
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

                        }else if(HoverTime==0&&MiddleTime==0){
                            b="只是点击\n";
                            b += s_format.format(new Date()) + ":" + "点击" + (endTime - startTime) + "Millisecond" + "\n";

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
                        //  }
                        Log.i("1aa", "aaaaaa" + HoverTime);
                        Log.i("1aa", "cccccc" + MiddleTime);


                    }
                    j1 = j2 = false;
                    y2 = x2 = 0;
                    textView.setText(b);
                    Fruit fruit = new Fruit(b);
                    fruitList.add(fruit);
                    listView.setAdapter(adapter);
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


    private void run1() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("haha","go1");
                HttpURLConnection conn=null;
                BufferedReader br=null;
                try {
                    URL url=new URL("https://www.fzdm.com/");
                    conn= (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(8000);
                    conn.setReadTimeout(8000);
                    InputStream in=conn.getInputStream();
                    br=new BufferedReader(new InputStreamReader(in));

                    StringBuilder sb=new StringBuilder();
                    String s;
                    while((s = br.readLine())!=null){
                        sb.append(s);
                    }
                    setContent(sb.toString());
                    Log.d("123","---"+sb.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("haha",e.getMessage());
                }finally {
                    if (conn!=null){
                        conn.disconnect();
                    }
                    if (br!=null){
                        try {
                            br.close();
                        } catch (IOException e) {
                            e.printStackTrace();

                        }
                    }
                }
            }
        }).start();

    }
    public void setContent(final String s) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                responseText.setText(s);
                Log.d("haha",s);
            }
        });
    }

}
