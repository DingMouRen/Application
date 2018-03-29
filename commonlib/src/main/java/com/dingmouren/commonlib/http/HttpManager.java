package com.dingmouren.commonlib.http;


import android.util.Log;

import com.dingmouren.commonlib.util.ApplicationUtils;
import com.dingmouren.commonlib.util.LogUtils;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2018/3/29.
 * 网络请求管理类
 */

public class HttpManager {

    private volatile static HttpManager sHttpManager = null;//网络请求管理类实例

    private static final int READ_TIME_OUT = 3;//读取超时时间

    private static final int CONNECT_TIME_OUT = 3;//连接超时时间

    private static final int WRITE_TIME_OUT = 3;//写的超时时间

    /*日志拦截器*/
    private static HttpLoggingInterceptor sHttpLogInterceptor = new HttpLoggingInterceptor(message ->
            showHttpLog(message)).setLevel(HttpLoggingInterceptor.Level.BODY);

    /*OkHttp的构建者对象*/
    private static OkHttpClient.Builder sOkHttpBuilder = new OkHttpClient.Builder()
            .addInterceptor(sHttpLogInterceptor);

    /*Retrofit的构建者对象*/
    private static Retrofit.Builder sRetrofitBuilder = new Retrofit.Builder()
            .baseUrl("http://gank.io/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create());


    private HttpManager() {}//私有化构造函数

    /**
     * 单例模式获取实例对象
     * @return
     */
    public static HttpManager getInstance(){
        if (sHttpManager == null){
            synchronized (HttpManager.class){
                if (sHttpManager == null) sHttpManager = new HttpManager();
            }
        }
        return sHttpManager;
    }

    /**
     * 创建Api的service
     * @param serviceClass
     * @param <S>
     * @return
     */
    public static <S> S createService(Class<S> serviceClass){
        OkHttpClient okHttpClient = sOkHttpBuilder.build();
        Retrofit retrofit = sRetrofitBuilder.client(okHttpClient).build();
        return retrofit.create(serviceClass);
    }



    /**
     * 显示网络日志
     * @param message
     */
    private static void showHttpLog(String message) {

        if (ApplicationUtils.isDebug()){
           LogUtils.json(message);
        }
    }
}
