package com.jiahuaandroid.rxandmvp.model.event;

/**
 * Created by jhhuang on 2016/10/13.
 * QQ:781913268
 * Description：联网状态改变事件
 */
public class NetWorkChangeEvent
{
    private static final String TAG = "NetWorkChangeEvent";

    private String msg;

    public NetWorkChangeEvent()
    {
    }

    public NetWorkChangeEvent(String msg)
    {
        this.msg = msg;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    @Override
    public String toString()
    {
        return "NetWorkChangeEvent{" +
                "msg='" + msg + '\'' +
                '}';
    }
}
