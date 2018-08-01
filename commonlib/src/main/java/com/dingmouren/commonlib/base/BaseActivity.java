package com.dingmouren.commonlib.base;

import android.content.Context;
import android.graphics.PixelFormat;
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
import com.dingmouren.commonlib.util.NetworkUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by dingmouren on 2018/3/19.
 * emial: naildingmouren@gmail.com
 */

public abstract class BaseActivity extends AppCompatActivity{



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


}
