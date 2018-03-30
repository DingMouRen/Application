package com.dingmouren.commonlib.http.interceptor;

import com.dingmouren.commonlib.util.DeviceUtils;
import com.dingmouren.commonlib.util.LogUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * Created by Administrator on 2018/3/30.
 *  添加公共请求参数的拦截器
 *  使用addInterceptor（）这个方法
 */

public class CommonParamsInterceptor implements Interceptor {

    private static final String PHONT = "phone";
    private static final String TOKEN = "token";

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();//获取到Request对象

        String method = request.method();//获取到请求方式

        Map<String,String> commonParamsMap = initCommonParams();//公共参数

        Request newRequest = null;
        switch (method){//根据请求方式的不同选择相应的处理
            case "GET":
                newRequest = getMethodDispose(request,commonParamsMap);
                break;

            case "POST":
                newRequest = postMethodDispose(request,commonParamsMap);
                break;
        }

        LogUtils.e( newRequest.url().toString());
        return chain.proceed(newRequest);
    }

    /**
     * 初始化公共请求参数
     * @return
     */
    private Map<String,String> initCommonParams() {
        Map<String,String> map = new HashMap<>();
        map.put(PHONT, "android");
        map.put(TOKEN, "token");
        return map;
    }

    /**
     * GET请求方式下添加公共请求参数(在末尾添加公共请求参数)
     * http://gank.io/api/data/%E7%A6%8F%E5%88%A9/10/1?phone=android&token=token
     * @param request
     * @param commonParamsMap 公共请求参数
     */
    private Request getMethodDispose(Request request,Map<String,String> commonParamsMap) {

        HttpUrl.Builder builder = request.url().newBuilder();

        Iterator iterator = commonParamsMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String,String> entry = (Map.Entry<String,String>) iterator.next();
            builder.addQueryParameter(entry.getKey(),entry.getValue());
        }

        HttpUrl httpUrl = builder.build();
        return request.newBuilder().url(httpUrl).build();

    }

    /**
     * POST请求方式下添加公共请求参数(有问题)
     * @param request
     * @param commonParamsMap 公共请求参数
     */
    private Request postMethodDispose(Request request,Map<String,String> commonParamsMap) {

        FormBody.Builder newBodyBuilder = new FormBody.Builder();

        Iterator iterator = commonParamsMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String,String> entry = (Map.Entry<String,String>) iterator.next();
            newBodyBuilder.add(entry.getKey(),entry.getValue());
        }

        FormBody oldBody = (FormBody) request.body();

        for (int i = 0; i < oldBody.size(); i++) {
            newBodyBuilder.add(oldBody.encodedName(i),oldBody.encodedValue(i));
        }

        FormBody newBody = newBodyBuilder.build();

        return request.newBuilder().post(newBody).build();
    }

}
