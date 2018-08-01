package com.dingmouren.commonlib.base;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.dingmouren.commonlib.R;
import com.dingmouren.commonlib.event.NetworkChangeEvent;
import com.dingmouren.commonlib.util.NetworkUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by dingmouren on 2018/3/19.
 * emial: naildingmouren@gmail.com
 */

public abstract class BaseActivity extends AppCompatActivity{

    protected Context mContext;

    protected boolean mCheckNetwork = true;/*默认检查网络状态*/

    View mNetworkStateTipView;

    WindowManager mWindowManager;

    WindowManager.LayoutParams mLayoutParams;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;

        initNetworkStateTipView();/*初始化监听网络状态的提示view*/

        EventBus.getDefault().register(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        hasNetwork(NetworkUtils.isConnected());
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
        if (null != mNetworkStateTipView && mNetworkStateTipView.getParent() != null){
            mWindowManager.removeView(mNetworkStateTipView);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 网络状态发生变化时的处理
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNetworkChangeEvent(NetworkChangeEvent event){
        hasNetwork(event.isConnected());
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
        mLayoutParams = new WindowManager.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 56,mContext.getResources().getDisplayMetrics()),
                WindowManager.LayoutParams.TYPE_APPLICATION,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                PixelFormat.TRANSLUCENT);
        //使用非CENTER时，可以通过设置XY的值来改变View的位置
        mLayoutParams.gravity = Gravity.CENTER;
        mLayoutParams.x = 0;
        mLayoutParams.y = 0;
    }

}
