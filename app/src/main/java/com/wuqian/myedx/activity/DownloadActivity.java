package com.wuqian.myedx.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;

import com.wuqian.myedx.R;
import com.wuqian.myedx.fragment.DownloadFragment;
import com.wuqian.myedx.fragment.DownloadFragment2;
import com.wuqian.myedx.util.TitleBar;

public class DownloadActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private PagerTabStrip pagerTabStrip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_download);
        initTitleBar();
        initView();
    }

    private void initView(){
        viewPager= (ViewPager) findViewById(R.id.view_pager);
        pagerTabStrip= (PagerTabStrip) findViewById(R.id.pager_tab_strip);
        pagerTabStrip.setTextColor(getResources().getColor(R.color.colorPrimary));
        pagerTabStrip.setTabIndicatorColor(getResources().getColor(R.color.colorPrimary));
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
    }

    private void initTitleBar(){

        new TitleBar(this).setTitle("我的下载")
                .setLeftImage(R.mipmap.ic_back)
                .setLeftOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
    }

    class MyPagerAdapter extends FragmentStatePagerAdapter{
        private String[] titles={"完成下载","正在下载"};
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if(position==0){
                return  new DownloadFragment();
            }else{
                return new DownloadFragment2();

            }
           // return null;
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return  titles[position];
        }
    }
}
