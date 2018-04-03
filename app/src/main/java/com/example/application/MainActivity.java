package com.example.application;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.dingmouren.commonlib.http.BaseObserver;
import com.dingmouren.commonlib.http.HttpManager;
import com.dingmouren.commonlib.rxlifecycle.DisposableManager;
import com.dingmouren.commonlib.util.LogUtils;
import com.example.application.api.TestApi;
import com.example.application.bean.Bean;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    @Override
    protected void onPause() {
        super.onPause();
        DisposableManager.clear();
    }

    public void requestHttp(View view){
        Observable.interval(500,TimeUnit.MILLISECONDS)
                .subscribe(new BaseObserver<Long>() {
                    @Override
                    public void onNext(Long aLong) {
                        LogUtils.e( "接收到 " + aLong);
                    }
                });

      /*  HttpManager.getInstance().createService(TestApi.class).getData()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<Bean>() {
                    @Override
                    public void onNext(Bean bean) {
                        LogUtils.e(bean);
                    }
                });*/
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

    public void toSecond(View view){
        startActivity(new Intent(MainActivity.this,SenconActivity.class));
    }
}
