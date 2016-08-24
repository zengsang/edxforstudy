package com.wuqian.myedx.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;

import com.wuqian.myedx.R;
import com.wuqian.myedx.fragment.FocusCourseFragment;
import com.wuqian.myedx.util.TitleBar;

public class FocusCourseActivity extends AppCompatActivity {

    private FragmentManager fm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_focus_course);
        initTitleBar();
        fm=getSupportFragmentManager();
        fm.beginTransaction().add(R.id.fl_container,new FocusCourseFragment()).commit();
    }


    private void initTitleBar(){
        new TitleBar(this).setTitle("我的关注")
                .setLeftImage(R.mipmap.ic_back)
                .setLeftOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
    }
}
