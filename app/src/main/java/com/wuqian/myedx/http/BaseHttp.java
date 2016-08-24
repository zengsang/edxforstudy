package com.wuqian.myedx.http;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.wuqian.myedx.util.FileUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Set;

/**
 * Created by wuqian on 2016/5/17.
 */
public abstract class BaseHttp {
    public static final String TAG="BaseHttp";

    public static final String BASE_URL="http://edx.dev.cloudkz.cn:81";

    private static final int ONE_MINUTE=1000*60;
    private static final int DATA_OUT_TIME=ONE_MINUTE*10;

    public static final int PARAM_TYPE_BODY=0;
    public static final int PARAM_TYPE_HEADERS=1;

    /**
     * 参数类型
     * PARAM_TYPE_BODY：body
     * PARAM_TYPE_HEADERS：headers
     * @return
     */
    protected abstract int getParamType();

    protected  boolean isForverSave(){
        return  false;
    }

    protected  abstract Map<String,String> getParams();

    /**
     * 将数据缓存到该文件名的文件中
     * @return
     */
    protected abstract String getFileName();

    /**
     * 数据访问地址
     * @return
     */
    protected abstract String getUrl();

    protected abstract String getMethod();

    public<T> T getData(Class<T> clazz){
       /* String data=getData();
        if(data!=null){
            return new Gson().fromJson(data,clazz);
        }
        return null;*/
        try{
            return new Gson().fromJson(getData(),clazz);
        }catch (Exception e){
            return null;
        }
    }

    public String getData(){
        String data=loadFromCache();
      //  String data=null;
        if(data==null){
            data=loadFromServer();
            //saveToCache(data);
            save2Cache(data);
        }
        return data;
    }


    private String loadFromServer(){
        if(isForverSave()){
            return  HomeCourseHttp.data;
        }

        URL url=null;
        HttpURLConnection connection=null;
        try{
            url=new URL(getUrl());
            connection= (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);
            connection.setRequestMethod(getMethod());
            Map<String,String> params=getParams();
            switch (getParamType()){
                case PARAM_TYPE_BODY:
                    if(params!=null){
                        StringBuilder sb=new StringBuilder();
                        Set<String> sets=params.keySet();
                        boolean isNeed=false;
                        for(String key:sets){
                            if(isNeed){
                                sb.append("&");
                            }
                            sb.append(key).append("=").append(params.get(key));
                            isNeed=true;
                        }
                        connection.getOutputStream().write(sb.toString().getBytes());
                    }
                    break;
                case PARAM_TYPE_HEADERS:
                    if(params!=null){
                        Set<String> keySet=params.keySet();
                        for(String key:keySet){
                            connection.addRequestProperty(key,params.get(key));
                        }
                    }
                    break;
                default:break;
            }
            Log.i(TAG,"response cade:"+connection.getResponseCode());
            InputStream in=connection.getInputStream();
            BufferedReader reader=new BufferedReader(new InputStreamReader(in));
            StringWriter sw=new StringWriter();
            String line;
            while ((line=reader.readLine())!=null){
                sw.write(line);
            }
            reader.close();
            sw.close();
            return sw.toString();
        }catch (UnknownHostException e){
            connectTimeOut=true;
            return null;
        }
        catch (Exception e){
            Log.e(TAG,"从服务器获取数据失败");
            e.printStackTrace();
            return null;
        }finally {
            if(connection!=null){
                connection.disconnect();
            }
        }

    }

    private boolean connectTimeOut=false;
    public boolean isConnectTimeOut(){
        return  connectTimeOut;
    }

    private String loadFromCache(){
        File file= FileUtils.getCacheJsonDir();
        File savePath=new File(file,getFileName());
        try{
            BufferedReader reader=new BufferedReader(new FileReader(savePath));
            long outData=Long.parseLong(reader.readLine());
            if(!isForverSave()&&System.currentTimeMillis()>outData){
                return null;
            }
            StringWriter sw=new StringWriter();
            String line;
            while ((line=reader.readLine())!=null){
                sw.write(line);
            }
            reader.close();
            sw.close();
            Log.i(TAG,"从缓存中读取数据成功");
            return  sw.toString();
        }catch (Exception e){
            Log.i(TAG,"从缓存中读取数据失败");
            e.printStackTrace();
            return null;
        }
    }

    private void saveToCache(String data){
        File file= FileUtils.getCacheJsonDir();
        File savePath=new File(file,getFileName());
        if(TextUtils.isEmpty(data)){
            return;
        }
        try{
            BufferedWriter writer=new BufferedWriter(new FileWriter(savePath));
            String outData=System.currentTimeMillis()+DATA_OUT_TIME+"";
            writer.write(outData);
            writer.newLine();
            writer.write(data);
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void save2Cache(final String data){
        new Thread(new Runnable() {
            @Override
            public void run() {
                saveToCache(data);
            }
        }).start();
    }

}
