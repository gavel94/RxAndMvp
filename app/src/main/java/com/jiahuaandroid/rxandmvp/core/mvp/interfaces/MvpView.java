package com.jiahuaandroid.rxandmvp.core.mvp.interfaces;

/**
 * Created by jhhuang on 2016/8/26.
 * QQ:781913268
 * Description：Base interface that any class that wants to act as a View in the MVP (Model View Presenter)
 */

public interface MvpView
{

    void showLoading();

    void hideLoading();

    void toast(String msg);

    void toast(int resId);

}
