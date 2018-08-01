package com.dingmouren.commonlib.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.dingmouren.commonlib.event.NetworkChangeEvent;
import com.dingmouren.commonlib.util.NetworkUtils;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by dingmouren on 2018/8/1.
 * 监听网络状态变更的广播接收器
 */

public class NetworkConnectChangedReceiver extends BroadcastReceiver{

    private static final String TAG = "NetworkChangedReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(TAG,"监听到网路变化");
        /*判断当前网络时候可用以及网络类型*/
        boolean isConnected = NetworkUtils.isConnected();
        NetworkUtils.NetworkType networkType = NetworkUtils.getNetworkType();
        EventBus.getDefault().post(new NetworkChangeEvent(isConnected,networkType));
    }
}
