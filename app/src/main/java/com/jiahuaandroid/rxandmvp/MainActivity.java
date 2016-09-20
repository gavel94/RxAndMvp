package com.jiahuaandroid.rxandmvp;

import android.content.Intent;
import android.databinding.DataBindingUtil;

import com.jakewharton.rxbinding.view.RxView;
import com.jiahuaandroid.rxandmvp.activity.SecondActivity;
import com.jiahuaandroid.rxandmvp.activity.presenter.MainPresenterImpl;
import com.jiahuaandroid.rxandmvp.activity.view.MainViewImpl;
import com.jiahuaandroid.rxandmvp.core.BaseActivity;
import com.jiahuaandroid.rxandmvp.databinding.ActivityMainBinding;

import java.util.concurrent.TimeUnit;

public class MainActivity extends BaseActivity<MainPresenterImpl> implements MainViewImpl
{
    private static final String TAG = "MainActivity";
    private ActivityMainBinding binding;

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
    protected void initEvent()
    {
        super.initEvent();
        RxView.clicks(binding.btnClick)
                .throttleFirst(2, TimeUnit.SECONDS)
                .delay(2, TimeUnit.SECONDS)
                .subscribe(v -> mPresenter.action2second());

    }

    @Override
    public void action2second()
    {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intent);
    }

}
