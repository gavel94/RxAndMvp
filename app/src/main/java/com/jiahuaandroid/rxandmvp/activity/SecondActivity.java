package com.jiahuaandroid.rxandmvp.activity;

import android.databinding.DataBindingUtil;

import com.jakewharton.rxbinding.view.RxView;
import com.jiahuaandroid.rxandmvp.R;
import com.jiahuaandroid.rxandmvp.activity.presenter.SecondPresenterImpl;
import com.jiahuaandroid.rxandmvp.activity.view.SecondViewImpl;
import com.jiahuaandroid.rxandmvp.core.BaseActivity;
import com.jiahuaandroid.rxandmvp.databinding.ActivityMainBinding;

import java.util.concurrent.TimeUnit;

/**
 * Created by jhhuang on 2016/8/24.
 * QQ:781913268
 * Description：xxx
 */
public class SecondActivity extends BaseActivity<SecondViewImpl, SecondPresenterImpl> implements SecondViewImpl
{
    private static final String TAG = "SecondActivity";
    private ActivityMainBinding binding;

    @Override
    protected SecondPresenterImpl createPresenter()
    {
        return new SecondPresenterImpl();
    }

    @Override
    protected void loadContentView()
    {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    @Override
    protected void initEvent()
    {
        super.initEvent();
        RxView.clicks(binding.btnClick)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> mPresenter.loadUserList(mContext));
    }
}
