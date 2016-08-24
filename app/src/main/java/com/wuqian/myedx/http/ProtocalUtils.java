package com.wuqian.myedx.http;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.wuqian.myedx.bean.Token;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wuqian on 2016/5/14.
 */
public class ProtocalUtils {

    private static  BaseProtocal protocal=new BaseProtocal();

    public static String getTokenJson(String username,String password){
        protocal.setFileName("token");
        protocal.setUrl("http://edx.dev.cloudkz.cn:81/oauth2/access_token");
        protocal.setMethod("POST");

        Map<String,String> params=new HashMap<String, String>();
        params.put("client_id","0d213f4f2ea348ce0eec");
        params.put("client_secret","08b190f2dce26412179ea584e6c24e28047ab3e0");
        params.put("grant_type","password");
        params.put("username",username);
        params.put("password",password);
        protocal.setParams(params);

        return  protocal.getData();
    }

    public static Token getToken(String username,String password){
        String json=getTokenJson(username,password);
        if(TextUtils.isEmpty(json)){
            return null;
        }
        return  new Gson().fromJson(json,Token.class);
    }

    //Bearer      833bfc2a3e1138a68e382f62f4dbc5768a95ef2e
    public static String getUserInfo(){
        protocal.setFileName("user_info");
        protocal.setUrl("http://edx.dev.cloudkz.cn:81/api/mobile/v0.5/users/wangyu");
        protocal.setMethod("GET");

        Map<String,String> params=new HashMap<String, String>();
        params.put("Authorization","Bearer 4c41e133669bb0eba7780b723ebf983293c62190");
        //params.put("client_secret","08b190f2dce26412179ea584e6c24e28047ab3e0");
       // params.put("grant_type","password");

        protocal.setParams(params);

        return  protocal.getData();
    }


    public static String getUserProfile(){
        protocal.setFileName("user_profile");
        protocal.setUrl("http://edx.dev.cloudkz.cn:81/api/user/v1/accounts/wangyu/image");
        protocal.setMethod("POST");

        Map<String,String> params=new HashMap<String, String>();
        params.put("Authorization","Bearer 4c41e133669bb0eba7780b723ebf983293c62190");
       /* params.put("client_secret","08b190f2dce26412179ea584e6c24e28047ab3e0");
        params.put("grant_type","password");*/

        protocal.setParams(params);

        return  protocal.getData();
    }
}
