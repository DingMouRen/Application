package com.dingmouren.lib;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2018/3/13.
 */

public class AppApplication extends Application {
    public static Context sAppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sAppContext = this;
    }
}
