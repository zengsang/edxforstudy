package com.wuqian.myedx.util;

import android.widget.Toast;

import com.wuqian.myedx.BaseApplication;

/**
 * Created by wuqian on 2016/5/18.
 */
public class ToastUtils {

    private static  Toast toast;
    public static void showToast(String msg){
        if(toast==null){
            toast=Toast.makeText(BaseApplication.getContext(),msg,Toast.LENGTH_SHORT);
        }
        toast.setText(msg);
        toast.show();
    }
}
