package com.wuqian.myedx.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wuqian.myedx.R;
import com.wuqian.myedx.adapter.MyFragmentPagerAdapter;
import com.wuqian.myedx.astuetz.PagerSlidingTabStrip;

/**
 * Created by wuqian on 2016/5/10.
 *
 * 发现课程页面
 */
public class DiscoverFragment extends Fragment {

    private PagerSlidingTabStrip tabs;
    /**
     * 获取当前屏幕密度
     */
    private DisplayMetrics dm;
    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.frag_discover, null);
        init();
        return view;
    }

    private void init(){
        dm=getResources().getDisplayMetrics();
        ViewPager pager=(ViewPager)view.findViewById(R.id.pager);
        tabs=(PagerSlidingTabStrip) view.findViewById(R.id.tabs);

        pager.setAdapter(new MyFragmentPagerAdapter(getActivity().getSupportFragmentManager()));
        tabs.setViewPager(pager);

       setTabsValue();
    }

    /**
     * 对PagerSlidingTabStrip的各项属性进行赋值。
     */
    private void setTabsValue(){
        //设置tab自动填充满屏幕
        tabs.setShouldExpand(true);
        //设置tab的分割线是透明的
        tabs.setDividerColor(Color.TRANSPARENT);
        //设置tab底部线的高度
        tabs.setUnderlineHeight((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 0, dm));
        // 设置Tab Indicator的高度
        tabs.setIndicatorHeight((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 4, dm));
        // 设置Tab标题文字的大小
        tabs.setTextSize((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 18, dm));
        // 设置Tab Indicator的颜色
        tabs.setIndicatorColor(Color.parseColor("#3F51B5"));
        // 设置选中Tab文字的颜色 (这是我自定义的一个方法)
        tabs.setSelectedTextColor(Color.parseColor("#3F51B5"));
        // 取消点击Tab时的背景色
        tabs.setTabBackground(0);
    }
}
