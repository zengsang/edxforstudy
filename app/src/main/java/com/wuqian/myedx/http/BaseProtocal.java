package com.wuqian.myedx.http;

import android.text.TextUtils;
import android.util.Log;

import com.wuqian.myedx.util.FileUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Set;

/**
 * Created by wuqian on 2016/5/13.
 */
public  class BaseProtocal {
    private String TAG="BaseProtocal";
    private static final int Data_OutTime=1000*60*5;

    public String getData(){
      /*  String data=loadFromCache();
        if(data==null){
            data=loadFromServer();
            saveToCache(data);
        }*/
        return loadFromServer();
    }

    private String loadFromServer(){
        URL url=null;
        HttpURLConnection connection = null;
        try{
            url=new URL(getUrl());

            connection= (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(8000);
            connection.setReadTimeout(8000);
            connection.setRequestMethod(method);
           // connection.setDoInput(true);
            if(!TextUtils.isEmpty(getParams())){
                connection.setDoOutput(true);
                DataOutputStream out=new DataOutputStream(connection.getOutputStream());
                // .write(getParams().getBytes());
                out.writeBytes(getParams());
                out.flush();
                //out.close();
                Log.e(TAG,"!TextUtils.isEmpty(getParams())"+getParams());
            }
            System.out.println("BaseProtocal stateCode="+connection.getResponseCode());
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
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            connection.disconnect();
        }

    }

    /**
     * 从缓存中读取数据
     * @return
     */
    private String loadFromCache(){
        File file= FileUtils.getCacheJsonDir();
        File savePath=new File(file,getFileName());
        try{
            BufferedReader reader=new BufferedReader(new FileReader(savePath));
            long outData=Long.parseLong(reader.readLine());
            if(System.currentTimeMillis()>outData){
                return null;
            }
            StringWriter sw=new StringWriter();
            String line;
            while ((line=reader.readLine())!=null){
                sw.write(line);
            }
            reader.close();
            sw.close();
            return  sw.toString();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将数据缓存起来
     * @param data
     */
    private void saveToCache(String data){
        File file= FileUtils.getCacheJsonDir();
        File savePath=new File(file,getFileName());
        if(TextUtils.isEmpty(data)){
            return;
        }
        try{
            BufferedWriter writer=new BufferedWriter(new FileWriter(savePath));
            String outData=System.currentTimeMillis()+Data_OutTime+"";
            writer.write(outData);
            writer.newLine();
            writer.write(data);
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public  String getParams(){
        return params;
    }

    public void setParams(Map<String,String> params){
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
        this.params=sb.toString();
    }
    private String params;
    private String url;
    private String fileName;
    private String method;

    public void setMethod(String method){
        this.method=method;
    }

    public String getMethod(){
        return method;
    }

    public void setUrl(String url){
        this.url=url;
    }
    public String getUrl(){
        return url;
    }

    public void setFileName(String fileName){
        this.fileName=fileName;
    }

    public String getFileName(){
        return fileName;
    }

}
