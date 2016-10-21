package com.jiahuaandroid.rxandmvp.network;

import rx.functions.Func1;

/**
 * Created by jhhuang on 2016/9/19.
 * QQ:781913268
 * Description： 用于处理DataResponse数据格式的数据
 */
public class ResponseFunc<T> implements Func1<DataResponse<T>, T>
{
    private static final String TAG = "ResponseFunc";

    @Override
    public T call(DataResponse<T> tDataResponse)
    {
        return tDataResponse.getData();
    }
}
