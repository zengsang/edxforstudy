package com.wuqian.myedx.fragment;

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
import com.avos.avoscloud.AVMobilePhoneVerifyCallback;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SignUpCallback;
import com.wuqian.myedx.R;
import com.wuqian.myedx.http.NetManager;
import com.wuqian.myedx.util.ToastUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wuqian on 2016/5/10.
 * 注册界面中的   手机注册页面
 */
public class RegisterPhoneFragment extends Fragment {
    private Button btn_vericode,btn_register;
    private EditText et_phone,et_password,et_vericode;

    private String phoneNum,password,vericode;

    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view=View.inflate(getActivity(), R.layout.frag_phone_register,null);
        initView();
        return view;
    }


    private void initView(){
        btn_vericode= (Button) view.findViewById(R.id.btn_vericode);
        btn_register= (Button) view.findViewById(R.id.btn_register);
        et_phone= (EditText) view.findViewById(R.id.et_phone);
        et_password= (EditText) view.findViewById(R.id.et_password);
        et_vericode= (EditText) view.findViewById(R.id.et_vericode);
        btn_vericode.setText("获取验证码");
        //获取手机短信验证码
        btn_vericode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneNum=getEtString(0);
                if(TextUtils.isEmpty(phoneNum)){
                    ToastUtils.showToast("手机号码不能为空");
                    return;
                }
                if(!isPhoneNumber(phoneNum)){
                   // System.out.println(phoneNum);
                    ToastUtils.showToast("请输入正确的手机号码");
                    return;
                }
                password=getEtString(1);
                if(TextUtils.isEmpty(password)){
                    ToastUtils.showToast("密码不能为空");
                    return;
                }
                if(!NetManager.isOpenNetWork()){
                    ToastUtils.showToast("网络未连接");
                    return;
                }

                AVUser user=new AVUser();
                user.setPassword(password);
                user.setUsername(phoneNum);
                user.put("mobilePhoneNumber",phoneNum);
                handler.sendEmptyMessage(MSG_TIME_COUNTER);
                btn_vericode.setEnabled(false);
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(AVException e) {
                        if(e==null){
                            //成功
                            handler.sendEmptyMessage(MSG_GET_VERICODE_SUCCESS);
                        }else{
                            //失败
                            handler.sendEmptyMessage(MSG_GET_VERICODE_FAILED);
                        }
                    }
                });
            }
        });

        //注册
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vericode=getEtString(2);
                if(!TextUtils.isDigitsOnly(vericode)){
                    ToastUtils.showToast("验证码格式错误");
                    return;
                }
                AVUser.verifyMobilePhoneInBackground(vericode, new AVMobilePhoneVerifyCallback() {
                    @Override
                    public void done(AVException e) {
                        if(e==null){
                            //验证码验证成功
                            handler.sendEmptyMessage(MSG_VERIFIY_CODE_SUCCESS);
                        }else{
                            //验证码错误
                            handler.sendEmptyMessage(MSG_VERIFIY_CODE_FAILED);
                        }
                    }
                });
            }
        });
    }

    private int time=60;

    private  static final int MSG_GET_VERICODE_FAILED=0;//获取短信验证码失败
    private  static final int MSG_GET_VERICODE_SUCCESS=1;

    private static final int MSG_VERIFIY_CODE_FAILED=2;//验证码错误
    private static final int MSG_VERIFIY_CODE_SUCCESS=3;//

    private static final int MSG_TIME_COUNTER=4;//定时更新获取验证码按钮时间指示

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case MSG_GET_VERICODE_FAILED:
                    ToastUtils.showToast("验证码获取失败，请检查手机号码正确性");
                    break;
                case MSG_GET_VERICODE_SUCCESS:
                    ToastUtils.showToast("短信验证码已发送");
                    break;
                case MSG_VERIFIY_CODE_FAILED:
                    ToastUtils.showToast("验证码错误");
                    break;
                case MSG_VERIFIY_CODE_SUCCESS:
                    ToastUtils.showToast("注册成功");
                    getActivity().finish();
                    break;
                case MSG_TIME_COUNTER:
                    if(time>0){
                        btn_vericode.setText("("+time+")"+"重新获取");
                        handler.sendEmptyMessageDelayed(MSG_TIME_COUNTER,1000);
                        time--;
                    }else{
                        btn_vericode.setEnabled(true);
                        btn_vericode.setText("获取验证码");
                    }
                    break;
                default:break;
            }
            super.handleMessage(msg);
        }
    };

    /**
     * 正则表达式：验证手机号
     */
    public static final String REGEX_MOBILE = "^((13[0-9])|(15[^4,\\\\D])|(18[0,5-9]))\\\\d{8}$";
    private boolean isPhoneNumber(String phone){
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(phone);
        return m.matches();
    }

    private String getEtString(int position){
        String result=null;
        if(position==0){
            result=et_phone.getText().toString().trim();
        }else if(position==1){
            result=et_password.getText().toString().trim();
        }else if(position==2){
            result=et_vericode.getText().toString().trim();
        }
        return result;
    }
}

