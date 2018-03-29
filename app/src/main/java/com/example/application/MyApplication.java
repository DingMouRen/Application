package com.example.application;

import android.app.Application;

import com.dingmouren.commonlib.util.ApplicationUtils;


/**
 * Created by Administrator on 2018/3/29.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationUtils.init(this);
    }
}
