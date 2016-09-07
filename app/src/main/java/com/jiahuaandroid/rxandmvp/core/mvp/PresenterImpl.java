package com.jiahuaandroid.rxandmvp.core.mvp;

import android.content.Context;

import com.jiahuaandroid.basetools.utils.LogUtil;
import com.jiahuaandroid.rxandmvp.core.mvp.interfaces.MvpView;
import com.jiahuaandroid.rxandmvp.core.mvp.interfaces.Presenter;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by jhhuang on 2016/8/26.
 * QQ:781913268
 * Description：PresenterImpl
 */
public class PresenterImpl<V extends MvpView> implements Presenter<V> {
    private static final String TAG = "PresenterImpl";
    private Reference<V> mMvpView;

    @Override
    public void attachView(V mvpView) {
        this.mMvpView = new WeakReference<>(mvpView);
        LogUtil.i(TAG,"attachView : "+getClass().getName());
    }

    @Override
    public void detachView() {
        if (this.mMvpView != null) {
            this.mMvpView.clear();
            this.mMvpView = null;
        }
    }

    /**
     *
     * @return 是否已经关联
     */
    public boolean isViewAttached() {
        return mMvpView != null && mMvpView.get() != null;
    }

    /**
     *
     * @return 获取接口
     */
    public V getMvpView() {
        return mMvpView.get();
    }

    /**
     * 检查是否关联并抛出自定义异常
     */
    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }


    private static class MvpViewNotAttachedException extends RuntimeException {
        MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }
}
