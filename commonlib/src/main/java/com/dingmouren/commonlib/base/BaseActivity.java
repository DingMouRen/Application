package com.dingmouren.commonlib.base;

import android.Manifest;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.dingmouren.commonlib.R;
import com.dingmouren.commonlib.event.NetworkChangeEvent;
import com.dingmouren.commonlib.receiver.NetworkConnectChangedReceiver;
import com.dingmouren.commonlib.util.NetworkUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import dialog.NetStateChangedDialog;

/**
 * Created by dingmouren on 2018/3/19.
 * emial: naildingmouren@gmail.com
 */

public abstract class BaseActivity extends AppCompatActivity{

    private static final String TAG = "BaseActivity";

    protected Context mContext;

    protected boolean mCheckNetwork = true;/*默认检查网络状态*/

    private NetworkConnectChangedReceiver netWorkChangReceiver;/*网络状态变化的广播接收器*/

    private NetStateChangedDialog netStateChangedDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;

        EventBus.getDefault().register(this);

        netStateChangedDialog = new NetStateChangedDialog(this);

        //注册网络状态监听广播
        netWorkChangReceiver = new NetworkConnectChangedReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(netWorkChangReceiver, filter);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    @Override
    public void finish() {
        super.finish();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(netWorkChangReceiver);
        EventBus.getDefault().unregister(this);
    }

    /**
     * 网络状态发生变化时的处理
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNetworkChangeEvent(NetworkChangeEvent event){
        Log.i(TAG,"接收到NetworkChangeEvent");
        if (!event.isConnected())netStateChangedDialog.show();
    }

    /**
     * 设置是否要检查网络状态变化
     * @param checkNetWork
     */
    public void setCheckNetWork(boolean checkNetWork) {
        mCheckNetwork = checkNetWork;
    }

    /**
     * 返回当前是否要检查网络状态变化
     * @return
     */
    public boolean isCheckNetWork() {
        return mCheckNetwork;
    }

}
