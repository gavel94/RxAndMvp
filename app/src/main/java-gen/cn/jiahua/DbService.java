package cn.jiahua;

import android.app.Application;
import android.content.Context;

import cn.jiahua.bean.DaoMaster;
import cn.jiahua.bean.DaoSession;


/**
 * Created by jhhuang on 2016/10/26.
 * QQ:781913268
 * Description：数据库操作类
 */
public class DbService
{
    private static final String TAG = "DbService";
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;
    private static Context mContext;

    /**
     * 初始化一个Application供全局使用
     *
     * @param application Application 对象
     */
    public static void init(Application application) {
        mContext = application;
    }

    /**
     * 取得DaoMaster
     *
     * @return DaoMaster
     */
    private static DaoMaster getDaoMaster() {
        if (daoMaster == null) {
            DaoMaster.OpenHelper helper = new THDevOpenHelper(mContext, "app_db", null);
            daoMaster = new DaoMaster(helper.getWritableDatabase());
        }
        return daoMaster;
    }

    /**
     * 取得DaoSession
     *
     * @return DaoSession
     */
    public static DaoSession getDaoSession() {
        if (daoSession == null) {
            if (daoMaster == null) {
                daoMaster = getDaoMaster();
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }
}
