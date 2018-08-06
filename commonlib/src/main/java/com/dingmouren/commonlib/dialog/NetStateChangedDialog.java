package com.dingmouren.commonlib.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.dingmouren.commonlib.R;

/**
 * Created by dingmouren on 2018/8/2.
 */

public class NetStateChangedDialog extends Dialog {
    public NetStateChangedDialog(@NonNull Context context) {
        super(context, R.style.StyleNetChangedDialog);
        View contentView = LayoutInflater.from(context).inflate(R.layout.dialog_network_state_tip,null);

        initView(contentView);
        setContentView(contentView);

        setCanceledOnTouchOutside(false);

        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.gravity = Gravity.TOP;
        layoutParams.windowAnimations = R.style.StyleNetChangedDialog_Animation;
        getWindow().setAttributes(layoutParams);
        getWindow().setDimAmount(0f);/*使用时设置窗口后面的暗淡量*/
    }

    private void initView(View contentView) {

    }


}
