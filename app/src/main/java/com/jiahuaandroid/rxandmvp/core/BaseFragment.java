package com.jiahuaandroid.rxandmvp.core;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiahuaandroid.basetools.utils.CUtils;
import com.jiahuaandroid.rxandmvp.core.mvp.FragmentPresenter;
import com.jiahuaandroid.rxandmvp.core.mvp.MvpFragment;

/**
 * Created by jhhuang on 2016/8/26.
 * QQ:781913268
 * Description：BaseFragment
 */
public abstract class BaseFragment<T extends FragmentPresenter> extends MvpFragment<T>
{
    private static final String TAG = "BaseFragment";
    protected Activity mActivity;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        return loadContentView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
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
    protected void initViews(Bundle savedInstanceState)
    {

    }

    /**
     * initialize the Activity data
     */
    protected void initData()
    {

    }

    /**
     * initialize event
     */
    protected void initEvent()
    {

    }

    @Override
    public void hideLoading()
    {
        try
        {
            ((BaseActivity) mActivity).hideLoading();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void showLoading()
    {
        try
        {
            ((BaseActivity) mActivity).showLoading();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void toast(String msg)
    {
        CUtils.showMsg(msg);
    }

    @Override
    public void toast(int resId)
    {
        CUtils.showMsg(resId);
    }
}
