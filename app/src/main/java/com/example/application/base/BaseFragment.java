package com.example.application.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by dingmouren
 * email: naildingmouren@gmail.com
 * github: https://github.com/DingMouRen
 */

public abstract class BaseFragment extends Fragment {

    protected View mRootView;

    private Unbinder mUnbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        init();/*初始化视图前的初始化*/
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(getLayoutView(),container,false);

        this.mRootView = rootView;

        mUnbinder = ButterKnife.bind(this,rootView);

        initView();/*初始化视图*/

        initListener();/*初始化监听*/

        initData();/*初始化数据*/

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    protected void init(){}/*初始化视图前的初始化*/

    protected abstract int getLayoutView();/*设置布局*/

    protected abstract void initView();/*初始化视图*/

    protected abstract void initListener();/*初始化监听*/

    protected void initData(){}/*初始化监听*/


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
