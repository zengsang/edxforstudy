package com.wuqian.myedx.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.wuqian.myedx.R;
import com.wuqian.myedx.http.NetManager;
import com.wuqian.myedx.util.SPFUtils;
import com.wuqian.myedx.util.TitleBar;
import com.wuqian.myedx.util.ToastUtils;

import java.util.Date;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText et_username,et_password;
    private TextView tv_forget;
    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        initTitleBar();
        initViews();
    }

    private void initViews(){
        et_username= (EditText) findViewById(R.id.et_username);
        et_password= (EditText) findViewById(R.id.et_password);
        tv_forget= (TextView) findViewById(R.id.tv_forget);
        btn_login= (Button) findViewById(R.id.btn_login);
        tv_forget.setOnClickListener(this);
        btn_login.setOnClickListener(this);
    }

    private void initTitleBar(){
        new TitleBar(this).setTitle("登录")
                .setLeftImage(R.mipmap.ic_back)
                .setLeftOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                })
                .setRightTitle("注册")
                .setRightOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                        finish();
                    }
                });
    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.tv_forget){
           startActivity(new Intent(LoginActivity.this,FindPasswordActivity.class));
        }else if(v.getId()==R.id.btn_login){
            final String username=getEtString(0);
            final String password=getEtString(1);
            if (TextUtils.isEmpty(username)) {
                ToastUtils.showToast("用户名不能为空");
                return;
            }
            if(TextUtils.isEmpty(password)){
                ToastUtils.showToast("密码不能为空");
                return;
            }
            if(!NetManager.isOpenNetWork()){
                ToastUtils.showToast("网络未连接");
                return;
            }
            showProgressDialog();
            AVUser user=new AVUser();
            user.logInInBackground(username, password, new LogInCallback<AVUser>() {
                @Override
                public void done(AVUser avUser, AVException e) {
                    if(e==null&&avUser!=null){
                        //登录成功
                        SPFUtils.putString(SPFUtils.TOKEN_USERNAME,username);
                        SPFUtils.putString(SPFUtils.TOKEN_PASSWORD,password);
                        String email=avUser.getEmail();
                        Date create_date=avUser.getCreatedAt();
                        boolean emailVerified=avUser.getBoolean("emailVerified");//邮件是否验证
                        SPFUtils.putString(SPFUtils.KEY_EMAIL,email);
                        SPFUtils.putString(SPFUtils.KEY_CREATE_DATE,create_date.toLocaleString());
                        SPFUtils.putBoolean(SPFUtils.USER_IS_LOGIN,true);
                        SPFUtils.putBoolean(SPFUtils.KEY_EMAILVERIFIED,emailVerified);
                        handler.sendEmptyMessage(MSG_SUCCESS);
                    }else{
                       //登录失败
                        handler.sendEmptyMessage(MSG_FAILED);
                    }
                }
            });

        }

    }

    private static final int MSG_SUCCESS=0;
    private static final int MSG_FAILED=1;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case MSG_SUCCESS:
                    dismissProgressDialog();
                    ToastUtils.showToast("登录成功");
                    //setResult(Activity.RESULT_OK);
                    sendBroadcast(new Intent(MainActivity.ACTION_LOGIN_SUCCESS));
                    finish();
                    break;
                case MSG_FAILED:
                    dismissProgressDialog();
                    ToastUtils.showToast("登录失败，用户名与密码不匹配");
                    break;
            }
            super.handleMessage(msg);
        }
    };


    private String getEtString(int id){
        String result=null;
        if(id==0){
           result=et_username.getText().toString().trim();
        }else if(id==1){
            result=et_password.getText().toString().trim();
        }
        return result;
    }

    private ProgressDialog progressDialog;
    private void showProgressDialog(){
        progressDialog=ProgressDialog.show(this,null,"正在登录...");
    }
    private void dismissProgressDialog(){
        if(progressDialog!=null){
            progressDialog.dismiss();
        }
    }
}
