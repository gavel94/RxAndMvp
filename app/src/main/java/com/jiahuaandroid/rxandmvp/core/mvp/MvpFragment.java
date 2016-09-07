package com.jiahuaandroid.rxandmvp.core.mvp;

import android.os.Bundle;

import com.jiahuaandroid.rxandmvp.core.mvp.interfaces.FragmentView;
import com.trello.rxlifecycle.components.support.RxFragment;

/**
 * Created by jhhuang on 2016/8/26.
 * QQ:781913268
 * Description：MvpFragment
 */
public abstract class MvpFragment<V extends FragmentView, T extends FragmentPresenterImpl<V>> extends RxFragment {
    private static final String TAG = "MvpFragment";
    protected T mPresenter;


    @SuppressWarnings("unchecked")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        mPresenter.attachView((V) this);
    }

    protected abstract T createPresenter();

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
