package com.jiahuaandroid.rxandmvp;

import com.jiahuaandroid.basetools.utils.LogUtil;

import org.junit.Test;

import rx.Observable;

/**
 * Created by jhhuang on 2016/9/18.
 * QQ:781913268
 * Description：xxx
 */
public class MainActivityTest
{
    private static final String TAG = "MainActivityTest";
    @Test
    public void log() throws Exception
    {
        Observable.just("Lambdas", "Default Method", "Stream API", "Date and Time API")
                .map(String::length)
                .subscribe(i -> LogUtil.e(TAG, "onCreate : " + i));
    }

}