package com.leagoo.ffmpegvedioplayer;

import android.app.Application;

import com.leagoo.ffmpegvedioplayer.media.Repository;

/**
 * Created by cangck on 2017/9/26.
 */

public class AppApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Repository.createInstance(this);
    }
}
