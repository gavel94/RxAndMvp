package com.jiahuaandroid.rxandmvp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.jiahuaandroid.basetools.utils.LogUtil;
import com.jiahuaandroid.rxandmvp.model.event.NetWorkChangeEvent;
import com.jiahuaandroid.rxandmvp.component.RxBus;

/**
 * Created by jhhuang on 2016/10/13.
 * QQ:781913268
 * Description：网络改变广播监听
 */
public class NetWorkChangeReceiver extends BroadcastReceiver
{
    private static final String TAG = "NetWorkChangeReceiver";
    private static boolean isConnecting;

    @Override
    public void onReceive(Context context, Intent intent)
    {
        String action = intent.getAction();

        if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION))
        {
            NetworkInfo networkInfo = intent.getParcelableExtra("networkInfo");
            if (networkInfo.isConnectedOrConnecting())
            {
                if (!isConnecting)
                {
                    LogUtil.e(TAG, "onReceive : 联网正常 = " + networkInfo.getTypeName());
                    // TODO: 2016/10/13  联网正常操作
                    RxBus.getDefault().post(new NetWorkChangeEvent("联网正常"));
                }
                isConnecting = true;

            } else
            {
                if (isConnecting)
                {
                    LogUtil.e(TAG, "onReceive : 联网失败 = " + networkInfo.getTypeName());
                    // TODO: 2016/10/13 联网失败操作
                    RxBus.getDefault().post(new NetWorkChangeEvent("联网失败"));
                }

                isConnecting = false;
            }

        }

    }
}
