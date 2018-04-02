package com.example.application;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.dingmouren.commonlib.http.HttpManager;
import com.dingmouren.commonlib.util.LogUtils;
import com.example.application.api.TestApi;
import com.example.application.bean.Bean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void requestHttp(View view){
        HttpManager.getInstance().createService(TestApi.class).getData()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtils.e("onSubscribe");
                    }

                    @Override
                    public void onNext(Bean bean) {
                        LogUtils.e("onNext");
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("onError");
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.e("onComplete");
                    }
                });
       /* HttpManager.getInstance().createService(TestApi.class).postMethodAddComonParamsTest()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Bean bean) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });*/
    }
}
