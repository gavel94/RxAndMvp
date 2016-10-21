package com.jiahuaandroid.rxandmvp.core.mvp.interfaces;

import com.trello.rxlifecycle.ActivityEvent;
import com.trello.rxlifecycle.LifecycleTransformer;

/**
 * Created by jhhuang on 2016/8/26.
 * QQ:781913268
 * Description：ActivityView
 */

public interface ActivityView extends MvpView
{

    <T> LifecycleTransformer<T> bindUntilEvent(ActivityEvent event);

    <T> LifecycleTransformer<T> bindToLifecycle();

    void showLoading();

    void hideLoading();

    void toast(String msg);
}
