package com.example.myapplication;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPageAdapter extends PagerAdapter {
    List<View> mList = new ArrayList<>();
    public ViewPageAdapter(List<View> mList) {
        this.mList = mList;
    }

    /**
     * @ClassName:ViewPageAdapter - 返回List数量
     * @author:CaoJiaHao
     * @Param:
     **/

    @Override
    public int getCount() {
        return mList.size();
    }

    /**
     * @ClassName:ViewPageAdapter - 将窗体进行绑定
     * @author:CaoJiaHao
     * @Param:
     **/
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    /**
     *@ClassName:ViewPageAdapter - 销毁View的过程
     *@author:CaoJiaHao
     *@Param:
     **/

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(mList.get(position));
    }

    /**
     *@ClassName:ViewPageAdapter - 这个为显示的View
     *@author:CaoJiaHao
     *@Param:
     **/
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(mList.get(position));
        return mList.get(position);
    }

}
