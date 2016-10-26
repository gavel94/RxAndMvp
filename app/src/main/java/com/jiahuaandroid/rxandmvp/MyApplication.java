package com.jiahuaandroid.rxandmvp;

import android.app.Application;
import android.content.Context;

import com.jiahuaandroid.basetools.utils.CUtils;
import com.jiahuaandroid.basetools.utils.LogUtil;
import com.tbruyelle.rxpermissions.RxPermissions;

import cn.jiahua.DbService;
import cn.jiahua.THDevOpenHelper;
import cn.jiahua.bean.DaoMaster;
import cn.jiahua.bean.DaoSession;

/**
 * Created by jhhuang on 2016/8/24.
 * QQ:781913268
 * Description：MyApplication
 */
public class MyApplication extends Application
{
    private static final String TAG = "MyApplication";
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;

    @Override
    public void onCreate()
    {
        super.onCreate();
        CUtils.init(this);
        DbService.init(this);
        LogUtil.debug(LogUtil.VERBOSE);
        RxPermissions.getInstance(this).setLogging(BuildConfig.DEBUG);
    }

    /**
     * 取得DaoMaster
     *
     * @param context 上下文
     * @return DaoMaster
     */
    public static DaoMaster getDaoMaster(Context context) {
        if (daoMaster == null) {
            DaoMaster.OpenHelper helper = new THDevOpenHelper(context, "app_db", null);
            daoMaster = new DaoMaster(helper.getWritableDatabase());
        }
        return daoMaster;
    }

    /**
     * 取得DaoSession
     *
     * @param context 上下文
     * @return DaoSession
     */
    public static DaoSession getDaoSession(Context context) {
        if (daoSession == null) {
            if (daoMaster == null) {
                daoMaster = getDaoMaster(context);
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }

}
