package com.jiahuaandroid.rxandmvp.activity.presenter;

import android.content.Context;

import com.jiahuaandroid.basetools.utils.LogUtil;
import com.jiahuaandroid.rxandmvp.activity.view.SecondViewImpl;
import com.jiahuaandroid.rxandmvp.core.mvp.ActivityPresenter;
import com.jiahuaandroid.rxandmvp.network.AppClient;
import com.jiahuaandroid.rxandmvp.network.ResponseFunc;
import com.jiahuaandroid.rxandmvp.network.ThrowableAction1;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
        AppClient.getInstance().create().getUserList()
                .subscribeOn(Schedulers.io())
                .compose(bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new ResponseFunc<>())
                .subscribe(
                        dataEntities -> LogUtil.e(TAG, "loadUserList : " + dataEntities.size()),
                        new ThrowableAction1()
                );
//        AppClient.getInstance().create().getUserList()
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
