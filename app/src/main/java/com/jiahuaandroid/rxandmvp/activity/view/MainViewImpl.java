package com.jiahuaandroid.rxandmvp.activity.view;

import com.jiahuaandroid.rxandmvp.core.mvp.interfaces.ActivityView;

import java.util.List;

/**
 * Created by jhhuang on 2016/9/18.
 * QQ:781913268
 * Description：MainViewImpl
 */
public interface MainViewImpl extends ActivityView
{

    void action2second();

    void notifyUserList(List<String> strings);
}
