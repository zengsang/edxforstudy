package com.wuqian.myedx.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.avos.avoscloud.RequestEmailVerifyCallback;
import com.wuqian.myedx.R;
import com.wuqian.myedx.http.NetManager;
import com.wuqian.myedx.util.SPFUtils;
import com.wuqian.myedx.util.TitleBar;
import com.wuqian.myedx.util.ToastUtils;

public class UserInfoActivity extends AppCompatActivity {

    private TextView tv_name,tv_email,tv_date;
    private RelativeLayout rl_email;
    String mEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_user_info);
        initTitleBar();
        initView();
        updateUserInfo();
    }


    private void initView(){
        rl_email= (RelativeLayout) findViewById(R.id.rl_email);
        tv_name= (TextView) findViewById(R.id.tv_username);
        tv_email= (TextView) findViewById(R.id.tv_email);
        tv_date= (TextView) findViewById(R.id.tv_date);
        boolean emailVerified=SPFUtils.getBoolean(SPFUtils.KEY_EMAILVERIFIED,false);
        String email=SPFUtils.getString(SPFUtils.KEY_EMAIL,null);
        tv_name.setText(SPFUtils.getString(SPFUtils.TOKEN_USERNAME,"获取失败"));
        tv_date.setText(SPFUtils.getString(SPFUtils.KEY_CREATE_DATE,"获取失败"));

        if(TextUtils.isEmpty(email)){
            tv_email.setText("未绑定邮箱");
        }else{
            if(emailVerified){
                tv_email.setText(email+"(已验证)");
            }else{
                tv_email.setText(email+"(未验证)");
            }
        }

    }

    private void initTitleBar(){
        new TitleBar(this).setTitle("个人信息")
                .setLeftImage(R.mipmap.ic_back)
                .setLeftOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
    }


    private void updateUserInfo(){
        if(!NetManager.isOpenNetWork()){
            return;
        }
        final String username=SPFUtils.getString(SPFUtils.TOKEN_USERNAME,null);
        final String password=SPFUtils.getString(SPFUtils.TOKEN_PASSWORD,null);
        AVUser avUser=new AVUser();
        avUser.logInInBackground(username, password, new LogInCallback<AVUser>() {
            @Override
            public void done(AVUser avUser, AVException e) {
                if(e==null&&avUser!=null){
                    //登录成功
                    final boolean emailVerified=avUser.getBoolean("emailVerified");//邮件是否验证
                    SPFUtils.putBoolean(SPFUtils.KEY_EMAILVERIFIED,emailVerified);
                    mEmail=SPFUtils.getString(SPFUtils.KEY_EMAIL,null);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                          if(!TextUtils.isEmpty(mEmail)){
                              if(emailVerified){
                                  tv_email.setText(mEmail+"(已验证)");
                              }else{
                                  tv_email.setText(mEmail+"(未验证)");
                                  rl_email.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          sendMessage();
                                      }
                                  });
                              }
                          }
                        }
                    });
                }else{
                    //登录失败
                }
             }
        });
    }
    //发送邮箱验证信息
    private void sendMessage(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("验证邮箱");
        builder.setMessage("要验证你的邮箱吗？验证后可以用来找回登录密码");
        builder.setCancelable(false);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("发送", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AVUser.requestEmailVerfiyInBackground(mEmail, new RequestEmailVerifyCallback() {
                    @Override
                    public void done(AVException e) {
                        if(e==null){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ToastUtils.showToast("验证邮件已发送");
                                }
                            });
                        }
                    }
                });
                dialog.dismiss();
            }
        });
        builder.show();
    }



}
