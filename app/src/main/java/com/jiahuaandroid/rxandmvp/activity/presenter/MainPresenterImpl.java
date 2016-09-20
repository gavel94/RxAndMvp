package com.jiahuaandroid.rxandmvp.activity.presenter;

import com.jiahuaandroid.rxandmvp.activity.view.MainViewImpl;
import com.jiahuaandroid.rxandmvp.core.mvp.ActivityPresenter;

/**
 * Created by jhhuang on 2016/9/18.
 * QQ:781913268
 * Description：MainPresenterImpl
 */
public class MainPresenterImpl extends ActivityPresenter<MainViewImpl>
{
    private static final String TAG = "MainPresenterImpl";

    public void action2second()
    {
        if(isViewAttached()) {
            getMvpView().action2second();
        }
    }
}
