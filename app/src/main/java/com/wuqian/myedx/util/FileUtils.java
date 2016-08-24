package com.wuqian.myedx.util;

import android.os.Environment;

import com.wuqian.myedx.BaseApplication;

import java.io.File;

/**
 * Created by wuqian on 2016/5/13.
 *
 * 用于获取数据缓存路径
 */
public class FileUtils {

    private static final String JSON_CACHE_DIR="json";
    private static final String ROOT_DIR="MyEdx";


    public static  File getCacheJsonDir(){
        return getDir(JSON_CACHE_DIR);
    }

    public static File getDir(String dir){
        StringBuilder sb=new StringBuilder();
        if(havaSD()){
            sb.append(Environment.getExternalStorageDirectory().getAbsolutePath());
            sb.append(File.separator);
            sb.append(ROOT_DIR);
            sb.append(File.separator);
            sb.append(dir);
        }else{
            sb.append(BaseApplication.getContext().getCacheDir().toString());
            sb.append(File.separator);
            sb.append(dir);
        }
        File file=new File(sb.toString());
        if(!file.exists()){
            file.mkdirs();
        }
        return file;
    }



    /**
     * 判断手机是否安装了SD卡
     * @return
     */
    private static boolean havaSD(){
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            return  true;
        }
        return false;
    }
}
