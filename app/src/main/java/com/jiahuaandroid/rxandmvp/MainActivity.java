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
import com.tbruyelle.rxpermissions.RxPermissions;

import java.util.List;
import java.util.concurrent.TimeUnit;

import cn.jiahua.DbService;
import cn.jiahua.bean.Contact;
import rx.Subscriber;
import rx.schedulers.Schedulers;

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
    protected void initViews(Bundle savedInstanceState)
    {
        super.initViews(savedInstanceState);
        binding.btnClick.setText(TAG);
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
    protected void initData()
    {
        super.initData();
        Contact contact = new Contact();
        contact.setId("1");
        contact.setRealName("测试");
        DbService.getDaoSession().getContactDao().rx().insertOrReplace(contact)
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Contact>()
                {
                    @Override
                    public void onCompleted()
                    {

                    }

                    @Override
                    public void onError(Throwable e)
                    {

                    }

                    @Override
                    public void onNext(Contact contact)
                    {
                        LogUtil.e(TAG, "onNext : Contact = " + contact.getId() + "----" + contact.getRealName());
                    }
                });
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
                .compose(RxPermissions.getInstance(mContext).ensure(Manifest.permission.READ_EXTERNAL_STORAGE))
                .subscribe(
                        hasPermission -> {
                            if (hasPermission)
                            {
                                mPresenter.action2second(mContext);
                            } else
                            {
                                LogUtil.e(TAG, "initEvent : 没有获取到权限");
                            }
                        },
                        Throwable::printStackTrace,
                        () -> LogUtil.e(TAG, "initEvent : com"));

//        RxView.clicks(binding.btnClick)
//                .throttleFirst(2, TimeUnit.SECONDS)
//                .subscribeOn(AndroidSchedulers.mainThread())
//                .subscribe(aVoid -> mPresenter.action2second(mContext),Throwable::printStackTrace);

    }

    @Override
    public void action2second()
    {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intent);
    }

    @Override
    public void notifyUserList(List<String> strings)
    {

    }

}
