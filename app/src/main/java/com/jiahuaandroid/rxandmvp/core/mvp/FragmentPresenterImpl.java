package com.jiahuaandroid.rxandmvp.core.mvp;

import android.support.annotation.NonNull;

import com.jiahuaandroid.rxandmvp.core.mvp.interfaces.FragmentView;
import com.trello.rxlifecycle.FragmentEvent;
import com.trello.rxlifecycle.LifecycleTransformer;

/**
 * Created by jhhuang on 2016/8/26.
 * QQ:781913268
 * Description：FragmentPresenterImpl
 */
public class FragmentPresenterImpl<V extends FragmentView> extends PresenterImpl<V> {
    private static final String TAG = "FragmentPresenterImpl";

    public final <T> LifecycleTransformer<T> bindUntilEvent(@NonNull FragmentEvent event) {
        return getMvpView().bindUntilEvent(event);
    }

    public final <T> LifecycleTransformer<T> bindToLifecycle() {
        return getMvpView().bindToLifecycle();
    }
}
