package com.wuqian.myedx.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.wuqian.myedx.R;
import com.wuqian.myedx.util.SPFUtils;
import com.wuqian.myedx.util.TitleBar;
import com.wuqian.myedx.util.ToastUtils;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initTitleBar();
        initView();
    }

    private void initTitleBar(){
        new TitleBar(this).setTitle("设置")
                .setLeftImage(R.mipmap.ic_back)
                .setLeftOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
    }

    private void initView(){
        btn_logout= (Button) findViewById(R.id.btn_logout);
        boolean isLogin= SPFUtils.getBoolean(SPFUtils.USER_IS_LOGIN,false);
        if(isLogin){
            btn_logout.setVisibility(View.VISIBLE);
            btn_logout.setOnClickListener(this);
        }else{
            btn_logout.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_logout:
                SPFUtils.logout2CleanData();
                btn_logout.setVisibility(View.GONE);
                setResult(Activity.RESULT_OK);
                ToastUtils.showToast("退出成功");
                break;
            default:
                break;
        }
    }
}
