package com.wuqian.myedx.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wuqian.myedx.R;
import com.wuqian.myedx.fragment.DiscoverFragment;
import com.wuqian.myedx.fragment.MyCourseFragment;
import com.wuqian.myedx.util.SPFUtils;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int SHOW_DISCOVER_FRAGMENT=1;
    private static final int SHOW_MYCOURSE_FRAGMENT=2;

    private FragmentManager fm;
    private DiscoverFragment discoverFragment;
    private MyCourseFragment myCourseFragment;

    Toolbar toolbar;
    private String toolbarTitle="发现课程";

    NavigationView navigationView;
    /**
     *
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fm=getSupportFragmentManager();
        showFragment(SHOW_DISCOVER_FRAGMENT);
        initMyView();

        IntentFilter filter=new IntentFilter(ACTION_LOGIN_SUCCESS);
        registerReceiver(loginSuccessBroadcase,filter);
    }

    /**
     * 初始化头像、登录按钮。。。
     */
    LinearLayout ll_login;
    LinearLayout ll_user_info;
    ImageView iv_profile;
    TextView tv_username;
    private void initMyView(){
        View view=View.inflate(this,R.layout.nav_header_main,null);
        ll_login= (LinearLayout) view.findViewById(R.id.ll_login);
        ll_user_info= (LinearLayout) view.findViewById(R.id.ll_user_info);
        iv_profile= (ImageView) view.findViewById(R.id.iv_profile);
        tv_username= (TextView) view.findViewById(R.id.tv_username);
        view.findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeDrawer();
                startActivityForResult(new Intent(MainActivity.this,LoginActivity.class),LOGIN_SUCCESS);
            }
        });
        iv_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,UserInfoActivity.class));
            }
        });
        navigationView.addHeaderView(view);

        boolean isLogin= SPFUtils.getBoolean(SPFUtils.USER_IS_LOGIN,false);
        if(isLogin){
            ll_login.setVisibility(View.INVISIBLE);
            ll_user_info.setVisibility(View.VISIBLE);
            tv_username.setText(SPFUtils.getString(SPFUtils.TOKEN_USERNAME,""));
        }else{
            ll_login.setVisibility(View.VISIBLE);
            ll_user_info.setVisibility(View.INVISIBLE);
        }
    }
    public static final int LOGIN_SUCCESS=1;
    public static final int LOGOUT=2;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode!= Activity.RESULT_OK){
            return;
        }
        if(requestCode==LOGIN_SUCCESS){
            ll_login.setVisibility(View.INVISIBLE);
            ll_user_info.setVisibility(View.VISIBLE);
            tv_username.setText(SPFUtils.getString(SPFUtils.TOKEN_USERNAME,""));
        }else if(requestCode==LOGOUT){
            ll_login.setVisibility(View.VISIBLE);
            ll_user_info.setVisibility(View.INVISIBLE);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        super.onResume();
        toolbar.setTitle(toolbarTitle);

    }


/*    private void showUserInfo(UserInfo info){
        ll_login.setVisibility(View.INVISIBLE);
        ll_user_info.setVisibility(View.VISIBLE);
        tv_username.setText(info.getEmail());
    }*/



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void closeDrawer(){
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_my_course) {
            showFragment(SHOW_MYCOURSE_FRAGMENT);
        } else if (id == R.id.nav_discover) {
            showFragment(SHOW_DISCOVER_FRAGMENT);
        } else if (id == R.id.nav_focus) {
            startActivity(new Intent(MainActivity.this,FocusCourseActivity.class));
        } else if (id == R.id.nav_download) {
            startActivity(new Intent(MainActivity.this,DownloadActivity.class));
        } else if (id == R.id.nav_share) {
            nav_share();
        } else if (id == R.id.nav_setting) {
           // startActivity(new Intent(MainActivity.this,SettingActivity.class));
            startActivityForResult(new Intent(MainActivity.this,SettingActivity.class),LOGOUT);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * 点击分享是执行的操作
     */
    private void nav_share(){
        String appUrl="http://zhushou.360.cn/detail/index/soft_id/1807194?recrefer=SE_D_%E5%AD%A6%E5%A0%82%E5%9C%A8%E7%BA%BF";
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT,"f分享");
        intent.putExtra(Intent.EXTRA_TEXT,"hi,推荐你使用软件：在线学习APP"+appUrl);
        startActivity(Intent.createChooser(intent,"分享"));
    }

    private void hideFragments(){
        FragmentTransaction ft=fm.beginTransaction();
        if(discoverFragment!=null){
            ft.hide(discoverFragment);
        }
        if(myCourseFragment!=null){
            ft.hide(myCourseFragment);
        }
        ft.commit();
    }

    private void showFragment(int actionId){

        hideFragments();
        FragmentTransaction ft=fm.beginTransaction();
        switch (actionId){
            case SHOW_MYCOURSE_FRAGMENT:
                if(myCourseFragment==null){
                    myCourseFragment=new MyCourseFragment();
                    ft.add(R.id.fl_container,myCourseFragment);
                }
                toolbarTitle="我的课程";
                ft.show(myCourseFragment);
                break;
            case SHOW_DISCOVER_FRAGMENT:
                if(discoverFragment==null){
                    discoverFragment=new DiscoverFragment();
                    ft.add(R.id.fl_container,discoverFragment);
                }
                toolbarTitle="发现课程";
                ft.show(discoverFragment);
                break;
        }
        onResume();
        ft.commit();

    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(loginSuccessBroadcase);
        super.onDestroy();
    }

    public static final String ACTION_LOGIN_SUCCESS="com.wuqian.myedx.main_LOGIN_SUCCESS";
    private LoginSuccessBroadcase loginSuccessBroadcase=new LoginSuccessBroadcase();
    private class LoginSuccessBroadcase extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            boolean isLogin=SPFUtils.getBoolean(SPFUtils.USER_IS_LOGIN,false);
            if(isLogin){
                ll_login.setVisibility(View.INVISIBLE);
                ll_user_info.setVisibility(View.VISIBLE);
                tv_username.setText(SPFUtils.getString(SPFUtils.TOKEN_USERNAME,""));
            }else{
                ll_login.setVisibility(View.VISIBLE);
                ll_user_info.setVisibility(View.INVISIBLE);
            }
        }
    }

}
