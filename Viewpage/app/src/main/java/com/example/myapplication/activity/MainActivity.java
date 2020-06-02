package com.example.myapplication.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    private LayoutInflater mInflater;
    private ViewPager viewPager;
    private ViewPagerAdapter pagerAdapter;
    private List<View> views;
    private View viewOne;
    private View viewTwo;
    private LinearLayout mLlDot;

    private Button mBtnOne;
    private Button mBtnTwo;

    /**
     * 当前显示的是第几页
     */
    private int curIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInflater = LayoutInflater.from(this);
        initView();
        setOvalLayout();
    }

    private void initView() {
        viewPager = findViewById(R.id.viewpager);
        mLlDot = (LinearLayout) findViewById(R.id.ll_dot);

        views = new ArrayList<View>();
        viewOne = mInflater.inflate(R.layout.layout_one, null);
        viewTwo = mInflater.inflate(R.layout.layout_two, null);
        views.add(viewOne);
        views.add(viewTwo);
        pagerAdapter = new ViewPagerAdapter(views);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(curIndex);
        viewPager.setOffscreenPageLimit(2);
        viewPager.addOnPageChangeListener(this);

        mBtnOne = viewOne.findViewById(R.id.btn_one);
        mBtnTwo = viewTwo.findViewById(R.id.btn_two);

        mBtnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "你点击第一个页面按钮", Toast.LENGTH_SHORT).show();
            }
        });
        mBtnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "你点击第二个页面按钮", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        // 取消圆点选中
        mLlDot.getChildAt(curIndex)
                .findViewById(R.id.v_dot)
                .setBackgroundResource(R.drawable.dot_normal);
        // 圆点选中
        mLlDot.getChildAt(position)
                .findViewById(R.id.v_dot)
                .setBackgroundResource(R.drawable.dot_selected);
        curIndex = position;

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * 设置圆点
     */
    public void setOvalLayout() {
        for (int i = 0; i < views.size(); i++) {
            mLlDot.addView(mInflater.inflate(R.layout.dot, null));
        }
        // 默认显示第一页
        mLlDot.getChildAt(0).findViewById(R.id.v_dot)
                .setBackgroundResource(R.drawable.dot_selected);
    }
}

