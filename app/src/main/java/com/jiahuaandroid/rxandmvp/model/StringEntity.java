package com.jiahuaandroid.rxandmvp.model;

/**
 * Created by jhhuang on 2016/10/20.
 * QQ:781913268
 * Description：xxx
 */
public class StringEntity
{
    private static final String TAG = "StringEntity";


    /**
     * code : 2000
     * msg : 测试文本
     * data : 成功返回时是消息数据列表，失败时是异常消息文本
     */

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
