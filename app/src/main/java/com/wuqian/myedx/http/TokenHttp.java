package com.wuqian.myedx.http;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wuqian on 2016/5/17.
 */
public class TokenHttp extends BaseHttp {
    private String username;
    private String password;
    public TokenHttp(String username,String password){
        this.username=username;
        this.password=password;
    }

    @Override
    protected int getParamType() {
        return BaseHttp.PARAM_TYPE_BODY;
    }

    @Override
    protected Map<String, String> getParams() {
        Map<String,String> params=new HashMap<String, String>();
        params.put("client_id","0d213f4f2ea348ce0eec");
        params.put("client_secret","08b190f2dce26412179ea584e6c24e28047ab3e0");
        params.put("grant_type","password");
        params.put("username",username);
        params.put("password",password);
        return params;
    }

    @Override
    protected String getFileName() {
        return "token";
    }

    @Override
    protected String getUrl() {
        return "http://edx.dev.cloudkz.cn:81/oauth2/access_token";
    }

    @Override
    protected String getMethod() {
        return "POST";
    }
}
