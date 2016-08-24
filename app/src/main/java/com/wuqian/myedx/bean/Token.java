package com.wuqian.myedx.bean;

/**
 * Created by wuqian on 2016/5/13.
 *
 * 登录成功后返回的token
 *http://edx.dev.cloudkz.cn:81/oauth2/access_token
 * 参数
 * client_id        0d213f4f2ea348ce0eec
 * client_secret    08b190f2dce26412179ea584e6c24e28047ab3e0
 * grant_type       password
 * username         1837960253@qq.com
 * password         iforgetit
 */
public class Token {
    private String access_token;
    private String token_type;
    private String expires_in;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    @Override
    public String toString() {
        return "Token{" +
                "access_token='" + access_token + '\'' +
                ", token_type='" + token_type + '\'' +
                ", expires_in='" + expires_in + '\'' +
                '}';
    }
}
