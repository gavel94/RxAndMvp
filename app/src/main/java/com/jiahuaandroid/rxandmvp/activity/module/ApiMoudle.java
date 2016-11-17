package com.jiahuaandroid.rxandmvp.activity.module;

import com.jiahuaandroid.rxandmvp.model.UserListEntity;
import com.jiahuaandroid.rxandmvp.network.ClientManager;
import com.jiahuaandroid.rxandmvp.network.DataResponse;
import com.jiahuaandroid.rxandmvp.network.service.ApiService;

import java.util.List;

import rx.Observable;

/**
 * Created by jhhuang on 2016/11/17.
 * QQ:781913268
 * Description：xxx
 */
public class ApiMoudle
{
    private static final String TAG = "ApiMoudle";

    public Observable<DataResponse<String>> testString()
    {
        return ClientManager.getClient(ApiService.HOST_URL).create(ApiService.class)
                .testString();
    }

    public Observable<DataResponse<List<UserListEntity.DataEntity>>> getUserList(String server,String multipleLogin)
    {
        return ClientManager.getClient(server).create(ApiService.class)
                .getUserList(multipleLogin);
    }
}
