package com.dingmouren.lib.http;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Administrator on 2018/3/29.
 * 网络请求管理类
 */

public class HttpManager {

    private volatile static HttpManager sHttpManager = new HttpManager();//网络请求管理类实例

    private static final int READ_TIME_OUT = 3;//读取超时时间

    private static final int CONNECT_TIME_OUT = 3;//连接超时时间

    private static final int WRITE_TIME_OUT = 3;//写的超时时间

    private static HttpLoggingInterceptor sHttpLogIntercept = new HttpLoggingInterceptor(message ->
            showHttpLog(message)).setLevel(HttpLoggingInterceptor.Level.BODY);


    private HttpManager() {}//私有化构造函数


    /**
     * 显示网络日志
     * @param message
     */
    private static void showHttpLog(String message) {
    }
}
