package com.jiahuaandroid.rxandmvp;

import android.Manifest;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.jakewharton.rxbinding.view.RxView;
import com.jiahuaandroid.basetools.utils.CUtils;
import com.jiahuaandroid.basetools.utils.LogUtil;
import com.jiahuaandroid.rxandmvp.activity.SecondActivity;
import com.jiahuaandroid.rxandmvp.activity.presenter.MainPresenterImpl;
import com.jiahuaandroid.rxandmvp.activity.view.MainViewImpl;
import com.jiahuaandroid.rxandmvp.component.RxBus;
import com.jiahuaandroid.rxandmvp.core.BaseActivity;
import com.jiahuaandroid.rxandmvp.databinding.ActivityMainBinding;
import com.jiahuaandroid.rxandmvp.model.event.NetWorkChangeEvent;
import com.jiahuaandroid.rxandmvp.utils.RxUtils;
import com.tbruyelle.rxpermissions.Permission;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.util.concurrent.TimeUnit;

public class MainActivity extends BaseActivity<MainPresenterImpl> implements MainViewImpl
{
    private static final String TAG = "MainActivity";
    private ActivityMainBinding binding;
    private String[] permissions = {
            Manifest.permission.READ_EXTERNAL_STORAGE
            , Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    protected MainPresenterImpl createPresenter()
    {
        return new MainPresenterImpl();
    }

    @Override
    protected void loadContentView()
    {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        RxBus.getDefault().toObserverable(NetWorkChangeEvent.class)
                .compose(bindToLifecycle())
                .compose(RxUtils.rxSchedulerHelper())
                .subscribe(
                        event -> {
                            LogUtil.e(TAG, "onCreate : " + event);
                            CUtils.showMsg(event.getMsg());
                        }
                );
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }

    @Override
    protected void initEvent()
    {
        super.initEvent();
        RxView.clicks(binding.btnClick)
                .throttleFirst(2, TimeUnit.SECONDS)
                .delay(2, TimeUnit.SECONDS)
                .compose(RxPermissions.getInstance(mContext).ensureEach(permissions))
                .buffer(permissions.length)
                .map(permissions -> {
                    boolean hasPermission = true;
                    for (Permission permission : permissions)
                    {
                        if (!permission.granted)
                        {
                            hasPermission = false;
                            break;
                        }
                    }
                    return hasPermission;
                })
                .subscribe(
                        hasPermission -> {
                            if (hasPermission)
                            {
                                mPresenter.action2second();
                            } else
                            {
                                LogUtil.e(TAG, "initEvent : 没有获取到权限");
                            }
                        },
                        Throwable::printStackTrace,
                        () -> LogUtil.e(TAG, "initEvent : com"));

    }

    @Override
    public void action2second()
    {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intent);
    }

}
