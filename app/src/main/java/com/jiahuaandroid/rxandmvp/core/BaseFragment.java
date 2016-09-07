package com.jiahuaandroid.rxandmvp.core;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiahuaandroid.rxandmvp.core.mvp.FragmentPresenterImpl;
import com.jiahuaandroid.rxandmvp.core.mvp.MvpFragment;
import com.jiahuaandroid.rxandmvp.core.mvp.interfaces.FragmentView;

/**
 * Created by jhhuang on 2016/8/26.
 * QQ:781913268
 * Description：BaseFragment
 */
public abstract class BaseFragment<V extends FragmentView, T extends FragmentPresenterImpl<V>> extends MvpFragment<V, T> {
    private static final String TAG = "BaseFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return loadContentView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.initViews(savedInstanceState);
        this.initData();
        this.initEvent();
    }

    /**
     * 加载视图
     */
    protected abstract View loadContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    /**
     * initialize the view in the layout
     *
     * @param savedInstanceState
     */
    protected void initViews(Bundle savedInstanceState) {

    }

    /**
     * initialize the Activity data
     */
    protected void initData() {

    }

    /**
     * initialize event
     */
    protected void initEvent() {

    }
}
