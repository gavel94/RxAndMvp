package com.jiahuaandroid.rxandmvp;

import android.app.Application;

import com.jiahuaandroid.basetools.utils.CUtils;
import com.jiahuaandroid.basetools.utils.LogUtil;

/**
 * Created by jhhuang on 2016/8/24.
 * QQ:781913268
 * Description：MyApplication
 */
public class MyApplication extends Application
{
    private static final String TAG = "MyApplication";

    @Override
    public void onCreate()
    {
        super.onCreate();
        CUtils.init(this);
        LogUtil.debug(LogUtil.VERBOSE);
    }
}
