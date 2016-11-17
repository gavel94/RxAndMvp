package com.jiahuaandroid.rxandmvp.core.mvp;

import android.support.annotation.NonNull;

import com.jiahuaandroid.rxandmvp.core.mvp.interfaces.FragmentView;
import com.jiahuaandroid.rxandmvp.network.ex.ResultException;
import com.jiahuaandroid.rxandmvp.network.ex.ToastException;
import com.trello.rxlifecycle.FragmentEvent;
import com.trello.rxlifecycle.LifecycleTransformer;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * Created by jhhuang on 2016/8/26.
 * QQ:781913268
 * Description：FragmentPresenter
 */
public class FragmentPresenter<V extends FragmentView> extends BasePresenter<V>
{
    private static final String TAG = "FragmentPresenter";

    public final <T> LifecycleTransformer<T> bindUntilEvent(@NonNull FragmentEvent event)
    {
        return getMvpView().bindUntilEvent(event);
    }

    public final <T> LifecycleTransformer<T> bindToLifecycle()
    {
        return getMvpView().bindToLifecycle();
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
