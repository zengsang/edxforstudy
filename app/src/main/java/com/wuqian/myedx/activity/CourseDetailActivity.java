package com.wuqian.myedx.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.avos.avoscloud.AVCloudQueryResult;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.CloudQueryCallback;
import com.avos.avoscloud.FindCallback;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.wuqian.myedx.R;
import com.wuqian.myedx.astuetz.PagerSlidingTabStrip;
import com.wuqian.myedx.bean.Course;
import com.wuqian.myedx.fragment.CourseDescFragment;
import com.wuqian.myedx.fragment.CourseListFragment;
import com.wuqian.myedx.http.CourseHttp;
import com.wuqian.myedx.util.SPFUtils;
import com.wuqian.myedx.util.ToastUtils;

import java.util.List;

public class CourseDetailActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView iv_back,iv_collect,iv_course;
    private TextView tv_title;
    private Button btn_join;
    private boolean isLogin=false;

    private ViewPager viewPager;
    private PagerSlidingTabStrip tabs;
    private DisplayMetrics dm;
    private Course course;
    private ImageLoader imageLoader;
    String username;
    int courseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_course_detail);
        String file_name=getIntent().getStringExtra("file_name");
        course= CourseHttp.getData(Course.class,file_name);
        username=SPFUtils.getString(SPFUtils.TOKEN_USERNAME,"无");
        courseId=course.getId();
        imageLoader=ImageLoader.getInstance();
        initView();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_collect:
                if(!isLogin){
                    showNoLoginDialog();
                }else{
                    collectCourse();
                }
                break;
            case R.id.btn_join:
                if(!isLogin){
                    showNoLoginDialog();
                }else{
                    ToastUtils.showToast("该功能还在开发中");
                }
                break;
            default:break;
        }
    }

    String objectId="";
    //收藏课程
    private void collectCourse(){
        if(isCollect){
            //取消收藏
            iv_collect.setImageResource(R.mipmap.ic_lesson_collect_nor);
            try {
               // String username=SPFUtils.getString(SPFUtils.TOKEN_USERNAME,"无");
                //int courseId=course.getId();
                /*AVObject avObject=new AVObject("Collect");
                avObject.put("username",username);
                avObject.put("courseId",courseId);
                avObject.deleteInBackground();*/
                //AVQuery.doCloudQueryInBackground("delete from Collect where username='"+username+"' and courseId='"+courseId+"'",null);
                AVQuery.doCloudQueryInBackground("delete from Collect where objectId='"+objectId+"'", new CloudQueryCallback<AVCloudQueryResult>() {
                    @Override
                    public void done(AVCloudQueryResult avCloudQueryResult, AVException e) {
                        if(e!=null){
                            e.printStackTrace();
                        }
                    }
                });
                ToastUtils.showToast("取消收藏");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            //收藏
            AVObject avObject=new AVObject("Collect");
            avObject.put("username",username);
            avObject.put("courseId",courseId);
            avObject.saveInBackground();
            isCollect();
            iv_collect.setImageResource(R.mipmap.ic_lesson_collect_pressed);
            ToastUtils.showToast("收藏成功");
        }
        isCollect=!isCollect;
    }

    private boolean isCollect=false;
    //是否收藏了课程
    private void  isCollect(){
       // String username=SPFUtils.getString(SPFUtils.TOKEN_USERNAME,"无");
       // int courseId=course.getId();
        AVQuery<AVObject> query=new AVQuery<>("Collect");
        query.whereEqualTo("username",username);
        query.whereEqualTo("courseId",courseId);

        query.findInBackground(new FindCallback<AVObject>() {
           @Override
           public void done(List<AVObject> list, AVException e) {
               if(list!=null&&list.size()>0){
                   isCollect=true;
                   objectId=list.get(0).getObjectId();
                   runOnUiThread(new Runnable() {
                       @Override
                       public void run() {
                           iv_collect.setImageResource(R.mipmap.ic_lesson_collect_pressed);
                       }
                   });
               }
           }
       });
    }



    private void initView(){
        findViewsId();
        isCollect();
        if(course!=null){
            tv_title.setText(course.getName());
            imageLoader.displayImage(course.getImg_course(),iv_course);
        }

        dm=getResources().getDisplayMetrics();
        isLogin= SPFUtils.getBoolean(SPFUtils.USER_IS_LOGIN,false);
        iv_back.setOnClickListener(this);
        iv_collect.setOnClickListener(this);
        btn_join.setOnClickListener(this);

        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        tabs.setViewPager(viewPager);
        setTabsValue();


    }

    private void findViewsId(){
        iv_back= (ImageView) findViewById(R.id.iv_back);
        iv_collect= (ImageView) findViewById(R.id.iv_collect);
        iv_course= (ImageView) findViewById(R.id.iv_course);
        tv_title= (TextView) findViewById(R.id.tv_title);
        btn_join= (Button) findViewById(R.id.btn_join);
        tabs= (PagerSlidingTabStrip) findViewById(R.id.tabs);
        viewPager= (ViewPager) findViewById(R.id.pager);

    }

    private void setTabsValue(){
        //设置tab自动填充满屏幕
        tabs.setShouldExpand(true);
        //设置tab的分割线是透明的
        tabs.setDividerColor(Color.TRANSPARENT);
        //设置tab底部线的高度
        tabs.setUnderlineHeight((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 1, dm));
        // 设置Tab Indicator的高度
        tabs.setIndicatorHeight((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 4, dm));
        // 设置Tab标题文字的大小
        tabs.setTextSize((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 18, dm));
        // 设置Tab Indicator的颜色
        tabs.setIndicatorColor(Color.parseColor("#59cba7"));
        // 设置选中Tab文字的颜色 (这是我自定义的一个方法)
        tabs.setSelectedTextColor(Color.parseColor("#59cba7"));
        // 取消点击Tab时的背景色
        tabs.setTabBackground(0);
    }


    private AlertDialog noLoginDialog;
    private void showNoLoginDialog(){
        if(noLoginDialog==null){
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("你还未登录");
            builder.setCancelable(true);
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.setPositiveButton("登录", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(CourseDetailActivity.this,LoginActivity.class));
                }
            });
            noLoginDialog=builder.create();
        }
        noLoginDialog.show();
    }

    private void dismissNoLoginDialog(){
        if(noLoginDialog!=null){
            noLoginDialog.dismiss();
        }
    }

    private CourseDescFragment descFragment;
    private CourseListFragment listFragment;
    private class MyPagerAdapter extends FragmentPagerAdapter{
        private String[] titles=new String[]{"简介","目录"};

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    if(descFragment==null){
                        descFragment=new CourseDescFragment(course);
                    }
                    return descFragment;
                case 1:
                    if(listFragment==null){
                        listFragment=new CourseListFragment(course);
                    }
                    return listFragment;
            }
            return null;
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }
}
