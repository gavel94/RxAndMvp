package com.jiahuaandroid.rxandmvp.network;


import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by jhhuang on 2016/10/19.
 * QQ:781913268
 * Description：RetryWithDelay
 */
public class RetryWithDelay implements Func1<Observable<? extends Throwable>, Observable<?>>
{
    private static final String TAG = "RetryWithDelay";
    private final int maxRetries;
    private final int retryDelayMillis;
    private int retryCount;

    public RetryWithDelay(int maxRetries, int retryDelayMillis)
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
