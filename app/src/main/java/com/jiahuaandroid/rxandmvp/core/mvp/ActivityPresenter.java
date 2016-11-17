package com.jiahuaandroid.rxandmvp.core.mvp;

import android.support.annotation.NonNull;

import com.jiahuaandroid.rxandmvp.core.mvp.interfaces.ActivityView;
import com.jiahuaandroid.rxandmvp.network.RetryWithDelay;
import com.jiahuaandroid.rxandmvp.network.ex.ResultException;
import com.jiahuaandroid.rxandmvp.network.ex.ToastException;
import com.trello.rxlifecycle.ActivityEvent;
import com.trello.rxlifecycle.LifecycleTransformer;

import retrofit2.adapter.rxjava.HttpException;
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
            if (e instanceof ToastException)
            {
                getMvpView().toast(e.getMessage());
            } else if (e instanceof ResultException)
            {
                onResultError(((ResultException) e));
            } else if (e instanceof HttpException)
            {
                onHttpError(((HttpException) e));
            } else
            {
                onOtherError(e);
            }
            e.printStackTrace();
        }

        public void onOtherError(Throwable e)
        {
            getMvpView().toast("未知错误");
        }

        public void onResultError(ResultException e)
        {
            getMvpView().toast(e.getMessage());
        }

        public void onHttpError(HttpException e)
        {
            getMvpView().toast("网络错误");
        }

    }

}
