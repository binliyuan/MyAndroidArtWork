package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String[] data = { "Apple", "Banana", "Orange", "Watermelon",
            "Pear", "Grape", "Pineapple", "Strawberry", "Cherry", "Mango" };

    private List<View> viewList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                MainActivity.this, android.R.layout.simple_list_item_1, data);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);


        View view1= new View(this);
        view1.setBackgroundColor(Color.RED);
        View view2= new View(this);
        view2.setBackgroundColor(Color.GREEN);
        View view3= new View(this);
        view3.setBackgroundColor(Color.BLACK);
        View view4= new View(this);
        view4.setBackgroundColor(Color.YELLOW);



        viewList.add(listView);
        viewList.add(view2);
        viewList.add(view3);
        viewList.add(view4);





        ViewPager viewPager = findViewById(R.id.ViewPager1);
        viewPager.setAdapter(new ViewPageAdapter(viewList));


    }


}
