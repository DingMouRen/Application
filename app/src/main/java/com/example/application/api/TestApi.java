package com.example.application.api;

import com.example.application.bean.DataBean;
import com.example.application.bean.ResponseBody;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2018/3/29.
 */

public interface TestApi {
    @GET("/movies/{page}")
    Observable<ResponseBody<DataBean>> getMoviesPage(@Path("page") Integer page);

    @Multipart
    @POST("/icon")
    Observable<ResponseBody> uploadIcon(@Part MultipartBody.Part img);
}
