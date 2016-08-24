package com.wuqian.myedx.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.RadioGroup;

import com.wuqian.myedx.R;
import com.wuqian.myedx.fragment.RegisterEmailFragment;
import com.wuqian.myedx.fragment.RegisterPhoneFragment;
import com.wuqian.myedx.util.TitleBar;

/**
 * 注册结界面
 */
public class RegisterActivity extends AppCompatActivity {
    private static final int SHOW_PHONE_FRAGMENT=1;
    private static final int SHOW_EMAIL_FRAGMENT=2;
    private FragmentManager fm;
    private RegisterPhoneFragment phoneFragment;
    private RegisterEmailFragment emailFragment;

    private RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register);
        initTitleBar();
        fm=getSupportFragmentManager();
        initView();

    }

    private void initView(){
        showFragment(SHOW_PHONE_FRAGMENT);
        radioGroup= (RadioGroup) findViewById(R.id.rg_register);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_phone:
                        showFragment(SHOW_PHONE_FRAGMENT);
                        break;
                    case R.id.rb_email:
                        showFragment(SHOW_EMAIL_FRAGMENT);
                        break;
                }
            }
        });
    }

    private void initTitleBar(){
        new TitleBar(this).setTitle("注册")
                .setLeftImage(R.mipmap.ic_back)
                .setLeftOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                })
                .setRightTitle("登录")
                .setRightOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                        finish();
                    }
                });
    }

    private void showFragment(int actionId){
        hideFragments();
        FragmentTransaction ft=fm.beginTransaction();
        switch (actionId){
            case SHOW_PHONE_FRAGMENT:
                if(phoneFragment==null){
                    phoneFragment=new RegisterPhoneFragment();
                    ft.add(R.id.fl_container,phoneFragment);
                }
                ft.show(phoneFragment);
                break;
            case SHOW_EMAIL_FRAGMENT:
                if(emailFragment==null){
                    emailFragment=new RegisterEmailFragment();
                    ft.add(R.id.fl_container,emailFragment);
                }
                ft.show(emailFragment);
                break;
        }
        ft.commit();
    }

    private void hideFragments(){
        FragmentTransaction ft=fm.beginTransaction();
        if(phoneFragment!=null){
            ft.hide(phoneFragment);
        }
        if(emailFragment!=null){
            ft.hide(emailFragment);
        }
        ft.commit();
    }
}
