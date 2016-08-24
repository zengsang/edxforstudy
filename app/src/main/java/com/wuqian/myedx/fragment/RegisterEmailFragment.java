package com.wuqian.myedx.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SignUpCallback;
import com.wuqian.myedx.R;
import com.wuqian.myedx.activity.LoginActivity;
import com.wuqian.myedx.http.NetManager;
import com.wuqian.myedx.util.ToastUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wuqian on 2016/5/10.
 * 注册界面中的   邮箱注册页面
 */
public class RegisterEmailFragment extends Fragment{
    private EditText et_username,et_email,et_password;
    private Button btn_register;
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=View.inflate(getActivity(), R.layout.frag_email_register,null);
        initView();
        return view;
    }

    private void initView(){
        et_username= (EditText) view.findViewById(R.id.et_username);
        et_email= (EditText) view.findViewById(R.id.et_email);
        et_password= (EditText) view.findViewById(R.id.et_password);
        btn_register= (Button) view.findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkInfo();
            }
        });
    }

    private void checkInfo(){
        String username=getEtString(0);
        String email=getEtString(1);
        String password=getEtString(2);
        if(TextUtils.isEmpty(username)){
            ToastUtils.showToast("用户名不能为空");
            return;
        }
        if(TextUtils.isEmpty(email)){
            ToastUtils.showToast("邮箱不能为空");
            return;
        }
        if(TextUtils.isEmpty(password)){
            ToastUtils.showToast("密码不能为空");
            return;
        }
        Pattern pattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
        Matcher matcher = pattern.matcher(email);
        if(!matcher.matches()){
            ToastUtils.showToast("邮箱格式错误");
            return;
        }

        if(!NetManager.isOpenNetWork()){
            ToastUtils.showToast("网络没有连接");
            return;
        }
        showProgressDialog();
        AVUser user=new AVUser();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(AVException e) {
                if(e==null){
                    //注册成功
                    handler.sendEmptyMessage(MSG_SUCCESS);
                }else{
                    int responseCode=e.getCode();
                    if(responseCode==202){
                        //用户名已被注册
                        handler.sendEmptyMessage(MSG_USERNAME_REGISTERED);
                    }else if(responseCode==203){
                        //改邮件已被注册
                        handler.sendEmptyMessage(MSG_EMAIL_REGISTERED);
                    }
                }
            }
        });
    }
    private static final int  MSG_USERNAME_REGISTERED=0;
    private static final int  MSG_EMAIL_REGISTERED=1;
    private static final int  MSG_SUCCESS=2;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case MSG_USERNAME_REGISTERED:
                    dismissProgressDialog();
                    ToastUtils.showToast("注册失败，该用户名已被注册");
                    break;
                case MSG_EMAIL_REGISTERED:
                    dismissProgressDialog();
                    ToastUtils.showToast("注册失败，该邮箱已被注册");
                    break;
                case MSG_SUCCESS:
                    dismissProgressDialog();
                    ToastUtils.showToast("注册成功，邮箱验证已发送");
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                    getActivity().finish();
                    break;
                default:break;
            }
            super.handleMessage(msg);
        }
    };

    private String getEtString(int id){
        String result=null;
        if(id==0){
            result=et_username.getText().toString().trim();
        }else if(id==1){
            result=et_email.getText().toString().trim();
        }else if(id==2){
            result=et_password.getText().toString().trim();
        }
        return result;
    }

    private ProgressDialog progressDialog;
    private void showProgressDialog(){
        progressDialog=ProgressDialog.show(getActivity(),null,"正在注册...");
    }
    private void dismissProgressDialog(){
        if(progressDialog!=null){
            progressDialog.dismiss();
        }
    }


}
