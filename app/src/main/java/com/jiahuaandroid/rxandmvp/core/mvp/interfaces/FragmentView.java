package com.jiahuaandroid.rxandmvp.core.mvp.interfaces;

import com.trello.rxlifecycle.FragmentEvent;
import com.trello.rxlifecycle.LifecycleTransformer;

/**
 * Created by jhhuang on 2016/8/26.
 * QQ:781913268
 * Description：FragmentView
 */

public interface FragmentView extends MvpView {
    <T> LifecycleTransformer<T> bindUntilEvent(FragmentEvent event);

    <T> LifecycleTransformer<T> bindToLifecycle();
}
