package com.jiahuaandroid.rxandmvp.network;

import com.jiahuaandroid.basetools.utils.LogUtil;

import rx.functions.Action1;

/**
 * Created by jhhuang on 2016/9/19.
 * QQ:781913268
 * Description：异常处理类，处理来自服务器约定的异常
 */
public class ThrowableAction1 implements Action1<Throwable>
{
    private static final String TAG = "ThrowableAction1";

    @Override
    public void call(Throwable throwable)
    {
        LogUtil.e(TAG, "call : throwable = " + throwable.getMessage());
        LogUtil.e(TAG, "call : Thread = " + Thread.currentThread().getName());
        throwable.printStackTrace();
    }
}
