package com.wuqian.myedx.util;

import android.graphics.Bitmap;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.wuqian.myedx.R;

/**
 * Created by wuqian on 2016/5/18.
 */
public class ImageOptHelper {

    public static DisplayImageOptions getImgOptions() {
        DisplayImageOptions imgOptions = new DisplayImageOptions.Builder()
                .cacheOnDisc()
                .cacheInMemory()
                .bitmapConfig(Bitmap.Config.RGB_565)
                .showStubImage(R.mipmap.no_login)
                .showImageForEmptyUri(R.mipmap.no_login)
                .showImageOnFail(R.mipmap.no_login)
                .build();
        return imgOptions;
    }

    public static DisplayImageOptions getAvatarOptions() {
        DisplayImageOptions	avatarOptions = new DisplayImageOptions.Builder()
                .cacheOnDisc()
                .cacheInMemory()
                .bitmapConfig(Bitmap.Config.RGB_565)
                .showStubImage(R.mipmap.no_login)
                .showImageForEmptyUri(R.mipmap.no_login)
                .showImageOnFail(R.mipmap.no_login)
                .displayer(new RoundedBitmapDisplayer(999))
                .build();
        return avatarOptions;
    }

    public static DisplayImageOptions getCornerOptions(int cornerRadiusPixels) {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheOnDisc()
                .cacheInMemory()
                .bitmapConfig(Bitmap.Config.RGB_565)
                .showStubImage(R.mipmap.no_login)
                .showImageForEmptyUri(R.mipmap.no_login)
                .showImageOnFail(R.mipmap.no_login)
                .displayer(new RoundedBitmapDisplayer(cornerRadiusPixels)).build();
        return options;
    }
}
