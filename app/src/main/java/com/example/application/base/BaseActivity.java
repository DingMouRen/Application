package com.example.application.base;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.dingmouren.commonlib.dialog.LoadingDialog;
import com.dingmouren.commonlib.event.NetworkChangeEvent;
import com.dingmouren.commonlib.receiver.NetworkConnectChangedReceiver;
import com.dingmouren.commonlib.util.NetworkUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import com.dingmouren.commonlib.dialog.NetStateChangedDialog;

import butterknife.ButterKnife;

/**
 * Created by dingmouren on 2018/3/19.
 * emial: naildingmouren@gmail.com
 */

public abstract class BaseActivity extends AppCompatActivity{

    private static final String TAG = "BaseActivity";

    protected Context mContext;

    protected boolean mCheckNetwork = true;/*默认检查网络状态*/

    protected boolean mNetConnected;/*网络连接的状态，true表示有网络，flase表示无网络连接*/

    private NetworkConnectChangedReceiver mNetWorkChangReceiver;/*网络状态变化的广播接收器*/

    private NetStateChangedDialog mNetStateChangedDialog;/*网络状态变化的提示对话框*/

    private LoadingDialog mLoadingDialog;/*加载进度对话框*/

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();/*初始化视图前的初始化*/

        setContentView(getLayoutView());

        initView();/*初始化视图*/

        initListener();/*初始化监听*/

        initData();/*初始化数据*/

        mContext = this;

        ButterKnife.bind(this);

        EventBus.getDefault().register(this);

        mNetStateChangedDialog = new NetStateChangedDialog(this);/*网络状态变化的提示对话框*/

        mLoadingDialog = new LoadingDialog(this);/*加载进度对话框*/

        //注册网络状态监听广播
        mNetWorkChangReceiver = new NetworkConnectChangedReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mNetWorkChangReceiver, filter);
    }

    protected void init(){}/*初始化视图前的初始化*/

    protected abstract int getLayoutView();/*设置布局*/

    protected abstract void initView();/*初始化视图*/

    protected abstract void initListener();/*初始化监听*/

    protected void initData(){}/*初始化监听*/

    @Override
    protected void onResume() {
        super.onResume();
        netStateChangedUI(NetworkUtils.isConnected());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mNetWorkChangReceiver);
        EventBus.getDefault().unregister(this);
    }


    /**
     * 设置是否要检查网络状态变化
     * @param checkNetWork
     */
    protected void setCheckNetWork(boolean checkNetWork) {
        mCheckNetwork = checkNetWork;
    }

    /**
     * 展示加载对话框
     */
    protected void showLoadingDialog(){
        mLoadingDialog.show();
    }

    /**
     * 隐藏加载对话框
     */
    protected void dismissLoadingDialog(){
        mLoadingDialog.dismiss();
    }

    /**
     * 网络状态发生变化时的处理
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNetworkChangeEvent(NetworkChangeEvent event){

        Log.i(TAG,"网络发生变化:"+event.toString());

        mNetConnected = event.isConnected();

        netStateChangedUI(event.isConnected());

    }

    /**
     * 根据网络状态显示或者隐藏提示对话框
     * @param isConnected
     */
    private void netStateChangedUI(boolean isConnected){
        if (mCheckNetwork) {
            if (isConnected) {
                if (null != mNetStateChangedDialog)mNetStateChangedDialog.dismiss();
            }else {
                if (null != mNetStateChangedDialog)mNetStateChangedDialog.show();
            }
        }
    }



}
