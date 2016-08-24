package com.wuqian.myedx.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.RequestPasswordResetCallback;
import com.wuqian.myedx.R;
import com.wuqian.myedx.http.NetManager;
import com.wuqian.myedx.util.TitleBar;
import com.wuqian.myedx.util.ToastUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindPasswordActivity extends AppCompatActivity {

    private EditText et_email;
    private Button btn_reset;
    private boolean sendSuccess=false;
    private boolean isSend=false;//是否发送了请求
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_find_password);
        initTitleBar();
        initView();
    }

    private void initView(){
        et_email= (EditText) findViewById(R.id.et_email);
        btn_reset= (Button) findViewById(R.id.btn_reset);
        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email=et_email.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    ToastUtils.showToast("请输入绑定账号的邮箱");
                    return;
                }


                Pattern pattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
                Matcher matcher = pattern.matcher(email);
                if(!matcher.matches()){
                    ToastUtils.showToast("邮箱格式错误");
                    return;
                }
                if(!NetManager.isOpenNetWork()){
                    ToastUtils.showToast("网络未连接");
                    return;
                }
                if(isSend){//发送过了
                    return;
                }
                isSend=true;

                showProgressDialog();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        checkEmail();
                    }
                }).start();
            }
        });

    }

    String email;
    private void checkEmail(){
        AVQuery<AVObject> query=new AVQuery<>("_User");
        query.whereEqualTo("email",email);
        try {
            if(query.getFirst()==null){
                handler.sendEmptyMessage(NO_ACCOUNT);
                return;
            }
        } catch (AVException e) {
            e.printStackTrace();
        }

        AVUser.requestPasswordResetInBackground(email, new RequestPasswordResetCallback() {
            @Override
            public void done(AVException e) {
                if(e==null){
                    handler.sendEmptyMessage(MSG_SUCCESS);
                }else{
                    handler.sendEmptyMessage(MSG_FAILED);
                }
            }
        });
    }
    private static final int MSG_SUCCESS=1;
    private static final int MSG_FAILED=2;
    private static final int NO_ACCOUNT=3;//邮箱未注册
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            isSend=false;
            dismissProgressDialog();
            if(msg.what==MSG_SUCCESS){
                ToastUtils.showToast("邮件已发送");
                finish();
            }else if(msg.what==MSG_FAILED){
                ToastUtils.showToast("连接超时");
            }else if(msg.what==NO_ACCOUNT){
                ToastUtils.showToast("该邮箱未注册账号");
            }
            super.handleMessage(msg);
        }
    };

    private void initTitleBar(){
        new TitleBar(this)
                .setTitle("重置密码")
                .setLeftImage(R.mipmap.ic_back)
                .setLeftOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
    }

    private ProgressDialog progressDialog;
    private void showProgressDialog(){
        progressDialog=ProgressDialog.show(this,null,"邮件发送中");
        progressDialog.setCancelable(true);
    }

    private void dismissProgressDialog(){
        if(progressDialog!=null){
            progressDialog.dismiss();
        }
    }
}