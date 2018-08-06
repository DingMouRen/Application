package com.example.application.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dingmouren.commonlib.base.BaseActivity;
import com.dingmouren.commonlib.http.BaseObserver;
import com.dingmouren.commonlib.http.HttpManager;
import com.dingmouren.commonlib.rxlifecycle.DisposableManager;
import com.dingmouren.commonlib.util.LogUtils;
import com.example.application.R;
import com.example.application.api.TestApi;
import com.example.application.bean.DataBean;
import com.example.application.bean.ResponseBody;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";
    private LinearLayout  mContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContainer = findViewById(R.id.linear);
        checkPermission();
        requestHttp();

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadIcon();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        DisposableManager.clear();
    }

    public void requestHttp( ){


        HttpManager.getInstance().createService(TestApi.class).getMoviesPage(0)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<ResponseBody<DataBean>>() {
                    @Override
                    public void onNext(ResponseBody<DataBean> dataBeanResponseBody) {
                        if (dataBeanResponseBody.getCode() == 1){
                            List<DataBean> list = (List<DataBean>) dataBeanResponseBody.getData();
                            for (DataBean dataBean : list){
                                ImageView imageView = new ImageView(MainActivity.this);
                                LinearLayout.LayoutParams layoutParams =  new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                                layoutParams.setMargins(0,16,0,0);
                                imageView.setLayoutParams(layoutParams);
                                Glide.with(MainActivity.this).load(dataBean.getImage()).into(imageView);
                                mContainer.addView(imageView);
                            }
                        }
                    }
                });

    }

    public void uploadIcon(){
        InputStream ins=getResources().openRawResource(R.raw.icon);
        File file = new File(Environment.getExternalStorageDirectory()+"/icon.png");

        try {
            if (!file.exists()) file.createNewFile();
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"),file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("img",file.getName(),requestBody);
        HttpManager.getInstance().createService(TestApi.class).uploadIcon(body)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<ResponseBody>() {
                    @Override
                    public void onNext(ResponseBody s) {
                        LogUtils.eTag(TAG,s);
                    }
                });
    }
    private void checkPermission() {
        //检查权限（NEED_PERMISSION）是否被授权 PackageManager.PERMISSION_GRANTED表示同意授权
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //用户已经拒绝过一次，再次弹出权限申请对话框需要给用户一个解释
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission
                    .WRITE_EXTERNAL_STORAGE)) {
                Toast.makeText(this, "请开通相关权限，否则无法正常使用本应用！", Toast.LENGTH_SHORT).show();
            }
            //申请权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);

        } else {
            Toast.makeText(this, "授权成功！", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "checkPermission: 已经授权！");
        }
    }
}
