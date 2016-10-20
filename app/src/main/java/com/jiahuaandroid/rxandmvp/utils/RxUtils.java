package com.jiahuaandroid.rxandmvp.utils;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
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

    public static <T> Observable.Transformer<T, T> rxRetryWithDelay(int maxRetries, int retryDelayMillis)
    {
        return tObservable -> tObservable.retryWhen(new RetryWithDelay(maxRetries, retryDelayMillis));
    }

    static class RetryWithDelay implements Func1<Observable<? extends Throwable>, Observable<?>>
    {
        private static final String TAG = "RetryWithDelay";
        private final int maxRetries;
        private final int retryDelayMillis;
        private int retryCount;

        RetryWithDelay()
        {
            this(3,20);
        }

        RetryWithDelay(int maxRetries, int retryDelayMillis)
        {
            this.maxRetries = maxRetries;
            this.retryDelayMillis = retryDelayMillis;
            this.retryCount = 0;
        }

        @Override
        public Observable<?> call(Observable<? extends Throwable> error)
        {
            return error.flatMap(throwable -> {
                if (++retryCount <= maxRetries)
                {
                    return Observable.timer(retryDelayMillis,
                            TimeUnit.MILLISECONDS);
                }
                return Observable.error(throwable);
            });
        }
    }
}
