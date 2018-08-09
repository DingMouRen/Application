package com.dingmouren.commonlib.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;

import com.dingmouren.commonlib.R;

/**
 * Created by dingmouren
 * email: naildingmouren@gmail.com
 * github: https://github.com/DingMouRen
 * 加载对话框
 */

public class LoadingDialog extends Dialog {
    public LoadingDialog(@NonNull Context context) {
        super(context,R.style.StyleLoadingDialog);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_loading,null);
        setContentView(view);

        setCanceledOnTouchOutside(false);
    }
}
