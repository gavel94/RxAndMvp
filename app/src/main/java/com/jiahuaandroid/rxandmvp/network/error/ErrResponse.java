package com.jiahuaandroid.rxandmvp.network.error;

/**
 * Created by jhhuang on 2016/10/20.
 * QQ:781913268
 * Description：错误信息
 */
public class ErrResponse
{
    private static final String TAG = "ErrResponse";
    private String code;
    private String msg;
    private String data;

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public String getData()
    {
        return data;
    }

    public void setData(String data)
    {
        this.data = data;
    }
}
