package com.wuqian.myedx.http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.wuqian.myedx.BaseApplication;

/**
 * Created by wuqian on 2016/5/19.
 */
public class NetManager {
    private Context context;
    public NetManager(Context context){
        this.context=context;
    }

    public static boolean isOpenNetWork(){
        ConnectivityManager connectivityManager= (ConnectivityManager) BaseApplication.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager!=null){
            NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
            if(networkInfo!=null&&networkInfo.isConnected()){
                return true;
            }
        }
        return false;
    }
}
