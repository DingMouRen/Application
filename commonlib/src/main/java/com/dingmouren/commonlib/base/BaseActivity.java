package com.dingmouren.commonlib.base;

import android.content.Context;
import android.content.IntentFilter;
import android.graphics.PixelFormat;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.dingmouren.commonlib.R;
import com.dingmouren.commonlib.event.NetworkChangeEvent;
import com.dingmouren.commonlib.receiver.NetworkConnectChangedReceiver;
import com.dingmouren.commonlib.util.NetworkUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by dingmouren on 2018/3/19.
 * emial: naildingmouren@gmail.com
 */

public abstract class BaseActivity extends AppCompatActivity{

    private static final String TAG = "BaseActivity";

    protected Context mContext;

    protected boolean mCheckNetwork = true;/*默认检查网络状态*/

    View mNetworkStateTipView;

    WindowManager mWindowManager;

    WindowManager.LayoutParams mLayoutParams;

    private NetworkConnectChangedReceiver netWorkChangReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;

        initNetworkStateTipView();/*初始化监听网络状态的提示view*/

        EventBus.getDefault().register(this);

        //注册网络状态监听广播
        netWorkChangReceiver = new NetworkConnectChangedReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(netWorkChangReceiver, filter);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        hasNetwork(NetworkUtils.isConnected());
//        if (mNetworkStateTipView != null && mNetworkStateTipView.getParent() == null){
//            mWindowManager.addView(mNetworkStateTipView,mLayoutParams);
//        }
    }

    private void hasNetwork(boolean connected) {
        if (isCheckNetWork()){
            if (connected){
                if (mNetworkStateTipView != null && mNetworkStateTipView.getParent() != null){
                    mWindowManager.removeView(mNetworkStateTipView);
                }
            }else {
                if (mNetworkStateTipView.getParent() == null){
                    mWindowManager.addView(mNetworkStateTipView,mLayoutParams);
                }
            }
        }
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
//        hasNetwork(event.isConnected());
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

    /**
     * 初始化网络状态变化的提示view
     */
    private void initNetworkStateTipView() {
        LayoutInflater inflater = getLayoutInflater();
        mNetworkStateTipView = inflater.inflate(R.layout.layout_network_state_tip, null); //提示View布局
        mWindowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        mLayoutParams = new WindowManager.LayoutParams();
        mLayoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        mLayoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        mLayoutParams.format = PixelFormat.TRANSLUCENT;
        mLayoutParams.type = WindowManager.LayoutParams.TYPE_TOAST;
        mLayoutParams.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
        //使用非CENTER时，可以通过设置XY的值来改变View的位置
        mLayoutParams.gravity = Gravity.TOP;
        mLayoutParams.windowAnimations =
        mLayoutParams.x = 0;
        mLayoutParams.y = 0;
    }

}
