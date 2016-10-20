package com.jiahuaandroid.rxandmvp.activity.module;


import com.jiahuaandroid.rxandmvp.network.DataResponse;

import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by jhhuang on 2016/5/19.
 * QQ:781913268
 * Description：ApiService
 */
public interface ApiService
{
    String HOST_URL = "http://192.168.0.107/";

    @POST("android/testString")
    Observable<DataResponse<String>> getUserList();
}
