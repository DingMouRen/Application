package com.example.application.api;

import com.example.application.bean.Bean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2018/3/29.
 */

public interface TestApi {
    @Headers("Cache-Control:public ,max-age=3600")//设置缓存有效期是一个小时
    @GET("data/福利/10/1")
    Observable<Bean> getData();

    @POST("data/福利/10/1")
    Observable<Bean> postMethodAddComonParamsTest();
}
