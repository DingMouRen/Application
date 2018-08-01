package com.dingmouren.commonlib.event;
import com.dingmouren.commonlib.util.NetworkUtils;
import com.dingmouren.commonlib.util.NetworkUtils.NetworkType;
/**
 * Created by dingmouren on 2018/8/1.
 *
 * mNetworkType
 * <p>
 *     {@code <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />}
 * </p>
 * <ul>
 *     <li>{@link NetworkUtils.NetworkType#NETWORK_WIFI}</li>
 *     <li>{@link NetworkUtils.NetworkType#NETWORK_2G}</li>
 *     <li>{@link NetworkUtils.NetworkType#NETWORK_3G}</li>
 *     <li>{@link NetworkUtils.NetworkType#NETWORK_4G}</li>
 *     <li>{@link NetworkUtils.NetworkType#NETWORK_UNKNOWN}</li>
 *     <li>{@link NetworkUtils.NetworkType#NETWORK_NO}</li>
 * </ul>
 */

public class NetworkChangeEvent {
    private boolean mIsConnected;
    private NetworkType mNetworkType;
    public NetworkChangeEvent(boolean isConnected,NetworkType networkType){
        this.mIsConnected = isConnected;
        this.mNetworkType = networkType;
    }

    public boolean isConnected() {
        return mIsConnected;
    }

    public void setIsConnected(boolean mIsConnected) {
        this.mIsConnected = mIsConnected;
    }

    public NetworkType getNetworkType() {
        return mNetworkType;
    }

    public void setNetworkType(NetworkType mNetworkType) {
        this.mNetworkType = mNetworkType;
    }
}
