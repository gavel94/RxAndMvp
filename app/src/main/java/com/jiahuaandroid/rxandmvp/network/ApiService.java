package com.jiahuaandroid.rxandmvp.network;


import com.jiahuaandroid.rxandmvp.model.UserListEntity;

import java.util.List;

import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by jhhuang on 2016/5/19.
 * QQ:781913268
 * Description：ApiService
 */
public interface ApiService {
    String BASE_URL = "http://192.168.1.240:9080/auth-web-1.0/";

    @POST("user/login_user_list")
    Observable<DataResponse<List<UserListEntity.DataEntity>>> getUserList();
}
