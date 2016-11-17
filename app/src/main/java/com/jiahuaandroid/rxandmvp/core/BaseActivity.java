package com.jiahuaandroid.rxandmvp.core;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.jiahuaandroid.basetools.utils.ActivityCollector;
import com.jiahuaandroid.basetools.utils.CUtils;
import com.jiahuaandroid.basetools.utils.LogUtil;
import com.jiahuaandroid.basetools.utils.StatusBarUtils;
import com.jiahuaandroid.rxandmvp.core.mvp.ActivityPresenter;
import com.jiahuaandroid.rxandmvp.core.mvp.MvpActivity;

/**
 * Created by jhhuang on 2016/8/26.
 * QQ:781913268
 * Description：BaseActivity
 */
public abstract class BaseActivity<T extends ActivityPresenter> extends MvpActivity<T>
{
    private static final String TAG = "BaseActivity";
    protected Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        LogUtil.i(TAG, "onCreate : " + getClass().getName());
        mContext = this;
        ActivityCollector.addActivity(this);
        loadContentView();
        this.initViews(savedInstanceState);
        this.initData();
        this.initEvent();
    }

    @Override
    protected void onNewIntent(Intent intent)
    {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override
    public void setContentView(int layoutResID)
    {
        super.setContentView(layoutResID);
        this.setStatusBar();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        LogUtil.i(getClass().getName(), "onDestroy : ");
        ActivityCollector.removeActivity(this);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        LogUtil.i(getClass().getName(), "onStart : ");
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        LogUtil.i(getClass().getName(), "onResume : ");
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        LogUtil.i(getClass().getName(), "onPause : ");
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        LogUtil.i(getClass().getName(), "onStop : ");
    }

    /**
     * 加载视图 setContentView()
     */
    protected abstract void loadContentView();

    /**
     * initialize the view in the layout
     *
     * @param savedInstanceState 状态信息
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

    protected void setStatusBar()
    {
        if (!ifFullScreen())
        {
            StatusBarUtils.setColor(this, Color.parseColor("#47B3FE"));
        }
    }

    /**
     * 判断当前activity是否为全屏
     *
     * @return true？全屏：不是全屏
     */
    protected boolean ifFullScreen()
    {
        WindowManager.LayoutParams attrs = getWindow().getAttributes();
        return ((attrs.flags & WindowManager.LayoutParams.FLAG_FULLSCREEN) == WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    /**
     * @param to 窗口透明度 1f表示正常。0表示全黑
     */
    protected void backgroundAlpha(float to)
    {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = to;
        getWindow().setAttributes(lp);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }

    /**
     * @param v 隐藏输入法
     */
    protected void hideKeyboard(View v)
    {
        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive())
        {
            imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
        }
    }

    @Override
    public void hideLoading()
    {
        // TODO: 2016/10/21 显示通用联网加载提示
    }

    @Override
    public void showLoading()
    {
        // TODO: 2016/10/21 隐藏通用联网加载提示
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
