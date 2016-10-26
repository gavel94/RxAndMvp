package com.jiahuaandroid.rxandmvp.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.jakewharton.rxbinding.view.RxView;
import com.jiahuaandroid.basetools.utils.LogUtil;
import com.jiahuaandroid.rxandmvp.R;
import com.jiahuaandroid.rxandmvp.activity.presenter.SecondPresenterImpl;
import com.jiahuaandroid.rxandmvp.activity.view.SecondViewImpl;
import com.jiahuaandroid.rxandmvp.core.BaseActivity;
import com.jiahuaandroid.rxandmvp.databinding.ActivityMainBinding;

import java.util.List;
import java.util.concurrent.TimeUnit;

import cn.jiahua.DbService;
import cn.jiahua.bean.Contact;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jhhuang on 2016/8/24.
 * QQ:781913268
 * Description：xxx
 */
public class SecondActivity extends BaseActivity<SecondPresenterImpl> implements SecondViewImpl
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
    protected void initViews(Bundle savedInstanceState)
    {
        super.initViews(savedInstanceState);
        binding.btnClick.setText(TAG);
    }

    @Override
    protected void initData()
    {
        super.initData();
        DbService.getDaoSession().getContactDao().rx()
                .loadAll()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Contact>>()
                {
                    @Override
                    public void onCompleted()
                    {

                    }

                    @Override
                    public void onError(Throwable e)
                    {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(List<Contact> contacts)
                    {
                        LogUtil.e(TAG,"onNext : contacts = "+contacts.size());
                    }
                });
        DbService.getDaoSession().getContactDao().rx().load("1")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(contact -> contact != null)
                .subscribe(new Subscriber<Contact>()
                {
                    @Override
                    public void onCompleted()
                    {

                    }

                    @Override
                    public void onError(Throwable e)
                    {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Contact contact)
                    {
                        toast(contact.getRealName());
                        LogUtil.e(TAG,"onNext : Contact = " +contact.getId()+"----"+contact.getRealName());
                    }
                });

    }

    @Override
    protected void initEvent()
    {
        super.initEvent();
        RxView.clicks(binding.btnClick)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(
                        aVoid -> {
                            mPresenter.loadUserList(mContext);
                        });
    }
}
