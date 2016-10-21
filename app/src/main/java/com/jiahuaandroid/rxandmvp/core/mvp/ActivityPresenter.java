package com.jiahuaandroid.rxandmvp.core.mvp;

import android.support.annotation.NonNull;

import com.jiahuaandroid.rxandmvp.core.mvp.interfaces.ActivityView;
import com.jiahuaandroid.rxandmvp.network.RetryWithDelay;
import com.trello.rxlifecycle.ActivityEvent;
import com.trello.rxlifecycle.LifecycleTransformer;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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

    /**
     * 前置工作让事件处理在分线程
     * @param <T>
     * @return
     */
    public <T> Observable.Transformer<T, T> callbackOnIOThread()
    {
        return tObservable -> tObservable.subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay())
                .compose(bindToLifecycle());
    }

    /**
     * 回到主线程准备回调到界面
     * @param <T>
     * @return
     */
    public <T> Observable.Transformer<T, T> verifyOnMainThread()
    {
        return tObservable -> tObservable.filter(t -> isViewAttached())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public abstract class NetSubseriber<T> extends Subscriber<T>
    {

        @Override
        public void onStart()
        {
            super.onStart();
            getMvpView().showLoading();
        }

        @Override
        public void onCompleted()
        {
            getMvpView().hideLoading();
        }

        @Override
        public void onError(Throwable e)
        {
            getMvpView().hideLoading();
            // TODO: 2016/10/21 处理约定异常
            e.printStackTrace();
        }

    }

}
