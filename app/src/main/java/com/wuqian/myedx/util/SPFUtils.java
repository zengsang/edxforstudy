package com.wuqian.myedx.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.wuqian.myedx.BaseApplication;

/**
 * Created by wuqian on 2016/5/18.
 */
public class SPFUtils {

    public static final String TOKEN="token";
    public static final String TOKEN_OUT_DATE="token_out_date";
    public static final String TOKEN_USERNAME="token_username";
    public static final String TOKEN_NAME="token_name";
    public static final String TOKEN_PASSWORD="token_password";
    public static final String USER_IS_LOGIN="user_is_login";
    public static final String KEY_EMAIL="email";
    public static final String KEY_CREATE_DATE="create_date";
    public static final String KEY_EMAILVERIFIED="emailVerified";

    public static void logout2CleanData(){
        putString(TOKEN,null);
        putString(TOKEN_USERNAME,null);
        putString(TOKEN_NAME,null);
        putString(TOKEN_PASSWORD,null);
        putBoolean(USER_IS_LOGIN,false);
    }



    private static SharedPreferences spf= BaseApplication.getContext().getSharedPreferences("config", Context.MODE_PRIVATE);

    public static void putString(String key,String value){
        spf.edit().putString(key,value).commit();
    }

    public static String getString(String key,String defValue){
        return spf.getString(key,defValue);
    }

    public static void putBoolean(String key,boolean value){
        spf.edit().putBoolean(key,value).commit();
    }

    public static boolean getBoolean(String key,boolean defValue){
        return spf.getBoolean(key,defValue);
    }
}
