package com.jiahuaandroid.rxandmvp.network;

/**
 * Created by jhhuang on 2016/9/19.
 * QQ:781913268
 * Description：返回的实体类泛型
 */
public class DataResponse<T>
{
    private String code;
    private String msg;
    private T data;

    public String getCode()
    {
        return code;
    }

    public String getMsg()
    {
        return msg;
    }

    public T getData()
    {
        return data;
    }
}
