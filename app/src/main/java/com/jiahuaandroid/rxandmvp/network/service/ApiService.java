package com.jiahuaandroid.rxandmvp.network.service;


import com.jiahuaandroid.rxandmvp.model.UserListEntity;
import com.jiahuaandroid.rxandmvp.network.DataResponse;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
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
    Observable<DataResponse<String>> testString();

    @FormUrlEncoded
    @POST("auth-web-1.0/user/login_user_list")
    Observable<DataResponse<List<UserListEntity.DataEntity>>> getUserList(@Field("multipleLogin") String multipleLogin);

}
