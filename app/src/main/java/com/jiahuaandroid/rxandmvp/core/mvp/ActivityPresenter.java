package com.jiahuaandroid.rxandmvp.core.mvp;

import android.support.annotation.NonNull;

import com.jiahuaandroid.rxandmvp.core.mvp.interfaces.ActivityView;
import com.trello.rxlifecycle.ActivityEvent;
import com.trello.rxlifecycle.LifecycleTransformer;

/**
 * Created by jhhuang on 2016/8/26.
 * QQ:781913268
 * Description：ActivityPresenter
 */
public class ActivityPresenter<V extends ActivityView> extends BasePresenter<V>
{
    private static final String TAG = "ActivityPresenter";

    public final <T> LifecycleTransformer<T> bindUntilEvent(@NonNull ActivityEvent event)
    {
        return getMvpView().bindUntilEvent(event);
    }

    public final <T> LifecycleTransformer<T> bindToLifecycle()
    {
        return getMvpView().bindToLifecycle();
    }
}
