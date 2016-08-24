package com.wuqian.myedx.http;

import com.google.gson.Gson;
import com.wuqian.myedx.BaseApplication;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by wuqian on 2016/6/3.
 */
public class CourseHttp {
    public static final String FILENAME_ALL_COURSE="courses";
    public static String getCourseContent(String fileName){
        try {
            StringBuilder sb=new StringBuilder();
            BufferedReader reader=new BufferedReader(new InputStreamReader(BaseApplication.getContext().getAssets().open(fileName+".json")));
            String line;
            while((line=reader.readLine())!=null){
                sb.append(line);
            }
            reader.close();
            return  sb.toString();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static<T> T getData(Class<T> clazz,String fileName){
        try{
            return  new Gson().fromJson(getCourseContent(fileName),clazz);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
