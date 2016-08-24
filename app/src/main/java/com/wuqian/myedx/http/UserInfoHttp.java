package com.wuqian.myedx.http;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wuqian on 2016/5/18.
 */
public class UserInfoHttp extends BaseHttp {

    private String token;
    public UserInfoHttp(String token){
        this.token=token;
    }

    @Override
    protected int getParamType() {
        return BaseHttp.PARAM_TYPE_HEADERS;
    }

    @Override
    protected Map<String, String> getParams() {
        Map<String,String> params=new HashMap<String, String>();
        params.put("Content-Type","application/merge-patch+json");
        params.put("Authorization","Bearer "+token);
        return params;
    }

    @Override
    protected String getFileName() {
        return "user_info";
    }

    @Override
    protected String getUrl() {
        return "http://edx.dev.cloudkz.cn:81/api/user/v1/accounts/wangyu";
    }

    @Override
    protected String getMethod() {
        return "PATCH";
    }
}
