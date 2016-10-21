package com.jiahuaandroid.rxandmvp.activity.presenter;

import android.content.Context;

import com.jiahuaandroid.basetools.utils.LogUtil;
import com.jiahuaandroid.rxandmvp.activity.module.ApiService;
import com.jiahuaandroid.rxandmvp.activity.view.SecondViewImpl;
import com.jiahuaandroid.rxandmvp.core.mvp.ActivityPresenter;
import com.jiahuaandroid.rxandmvp.network.ClientManager;
import com.jiahuaandroid.rxandmvp.network.ResponseFunc;
import com.jiahuaandroid.rxandmvp.network.RetryWithDelay;
import com.jiahuaandroid.rxandmvp.utils.RxUtils;

import rx.Subscriber;
import rx.functions.Action0;

/**
 * Created by jhhuang on 2016/9/18.
 * QQ:781913268
 * Description：SecondPresenterImpl
 */
public class SecondPresenterImpl extends ActivityPresenter<SecondViewImpl>
{
    private static final String TAG = "SecondPresenterImpl";

    public void loadUserList(Context mContext)
    {
        ClientManager.getClient(ApiService.HOST_URL).create(ApiService.class).testString()
//                .subscribeOn(Schedulers.io())
                .compose(bindToLifecycle())
//                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxUtils.rxSchedulerHelper())
                .retryWhen(new RetryWithDelay(3, 100))
                .doOnCompleted(new Action0()
                {
                    @Override
                    public void call()
                    {
                        LogUtil.e(TAG, "doOnCompleted : thread = " + Thread.currentThread().getName());
                    }
                })
                .doOnError(error -> {
                    LogUtil.e(TAG, "doOnError : thread = " + Thread.currentThread().getName());
                    error.printStackTrace();
                })
                .map(new ResponseFunc<>())
//                .subscribe(
//                        str -> LogUtil.e(TAG, "str : " + str),
//                        new ThrowableAction1()
//                );
                .subscribe(new Subscriber<String>()
                {
                    @Override
                    public void onCompleted()
                    {
                        LogUtil.e(TAG, "onCompleted :thread = " + Thread.currentThread().getName());
                    }

                    @Override
                    public void onError(Throwable e)
                    {
                        LogUtil.e(TAG, "onError : thread = " + Thread.currentThread().getName());
                    }

                    @Override
                    public void onNext(String s)
                    {
                        LogUtil.e(TAG, "onNext : thread = " + Thread.currentThread().getName());
                    }
                });
//        AppClient.getInstance().create().testString()
//                .compose(bindToLifecycle())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnSubscribe(this::show)
//                .map(new ResponseFunc<>())
//                .subscribe(new Subscriber<List<UserListEntity.DataEntity>>()
//                {
//                    @Override
//                    public void onStart()
//                    {
//                        super.onStart();
//                        LogUtil.e(TAG, "onStart : " + Thread.currentThread().getName());
//                    }
//
//                    @Override
//                    public void onCompleted()
//                    {
//                        LogUtil.e(TAG, "onCompleted : " + Thread.currentThread().getName());
//                    }
//
//                    @Override
//                    public void onError(Throwable e)
//                    {
//                        e.printStackTrace();
//                        LogUtil.e(TAG, "onError : " + Thread.currentThread().getName());
//                    }
//
//                    @Override
//                    public void onNext(List<UserListEntity.DataEntity> data)
//                    {
//                        LogUtil.e(TAG,"onNext : "+data.size());
//                    }
//
//                });
    }

    private void show()
    {
        LogUtil.e(TAG, "show : " + Thread.currentThread().getName());
    }
}
