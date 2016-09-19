package com.jiahuaandroid.rxandmvp.network.ex;

/**
 * Created by jhhuang on 2016/9/19.
 * QQ:781913268
 * Description：联网约定异常
 */
public class NetException extends RuntimeException
{
    private static final String TAG = "NetException";

    public NetException(String detailMessage)
    {
        super(detailMessage);
    }

    public NetException()
    {
        super();
    }

    public NetException(Throwable throwable)
    {
        super(throwable);
    }

    public NetException(String detailMessage, Throwable throwable)
    {
        super(detailMessage, throwable);
    }
}
