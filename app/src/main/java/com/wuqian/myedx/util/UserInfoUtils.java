package com.wuqian.myedx.util;

import com.wuqian.myedx.bean.Token;
import com.wuqian.myedx.bean.UserInfo;
import com.wuqian.myedx.http.TokenHttp;
import com.wuqian.myedx.http.UserInfoHttp;

/**
 * Created by wuqian on 2016/5/18.
 */
public class UserInfoUtils {
    //String ursename="1837960253@qq.com";String password="iforgetit";


    public static String getToken(){
        long dataOut=Long.parseLong(SPFUtils.getString(SPFUtils.TOKEN_OUT_DATE,0+""));
        if(System.currentTimeMillis()>dataOut){
            String username=SPFUtils.getString(SPFUtils.TOKEN_USERNAME,null);
            String password=SPFUtils.getString(SPFUtils.TOKEN_PASSWORD,null);
            Token token=new TokenHttp(username,password).getData(Token.class);
            if(token!=null){
                return token.getAccess_token();
            }else{
                return null;
            }
        }
        return SPFUtils.getString(SPFUtils.TOKEN,null);
    }

    public static UserInfo getUserInfo(){
        String token=getToken();
        if(token==null){
            return null;
        }

        UserInfoHttp infoHttp=new UserInfoHttp(token);
        UserInfo userInfo=infoHttp.getData(UserInfo.class);
        if(userInfo==null){
            return null;
        }
        return userInfo;
    }

}
