package com.wuqian.myedx.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.wuqian.myedx.fragment.CategoryFragment;
import com.wuqian.myedx.fragment.HomeFragment;

/**
 * Created by wuqian on 2016/5/10.
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private final String[] titles = { "推荐课程","课程分类"};
    private HomeFragment homeFragment;
    private CategoryFragment categoryFragment;



    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                if(homeFragment==null){
                    homeFragment=new HomeFragment();
                }
                return homeFragment;
            case 1:
                if(categoryFragment==null){
                    categoryFragment=new CategoryFragment();
                }
                return categoryFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return titles.length;
    }
}
