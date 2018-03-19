package com.dingmouren.lib.manager;

import com.dingmouren.lib.base.BaseActivity;

import java.util.Stack;

/**
 * Created by dingmouren on 2018/3/19.
 * emial: naildingmouren@gmail.com
 * Activity管理类
 */

public class ActivityManager {

    public static Stack<BaseActivity> mActivitys = new Stack<>();

    /**
     * 添加activity
     * @param activity
     */
    public static void addActivity(BaseActivity activity){
        if (null != activity) mActivitys.add(activity);
    }

    /**
     * 删除activity
     * @param activity
     */
    public static void removeActivity(BaseActivity activity){
        if (null != null && mActivitys.contains(activity)) mActivitys.remove(activity);
    }

    /**
     * 强制退出
     */
    public static void exitApp(){
        for (BaseActivity activity : mActivitys) activity.finish();
    }

}
