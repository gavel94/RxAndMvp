package com.jiahuaandroid.rxandmvp.utils;

import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jhhuang on 2016/10/14.
 * QQ:781913268
 * Description：封装一些rx的方法
 */
public class RxUtils
{
    private static final String TAG = "RxUtils";

    /**
     * @return 触发线程在子线程
     */
    public static <T> Observable.Transformer<T, T> rxSchedulerHelper()
    {
        return tObservable -> tObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 可自定义触发线程
     */
    public static <T> Observable.Transformer<T, T> rxSchedulerHelper(Scheduler scheduler)
    {
        return tObservable -> tObservable.subscribeOn(scheduler)
                .observeOn(AndroidSchedulers.mainThread());
    }

}
