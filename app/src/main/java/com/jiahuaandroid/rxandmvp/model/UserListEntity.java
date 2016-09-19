package com.jiahuaandroid.rxandmvp.model;

import java.util.List;

/**
 * Created by jhhuang on 2016/7/26.
 * QQ:781913268
 * Description：UserListEntity 登录界面用户列表实体类
 */

public class UserListEntity
{

    /**
     * code : 2000
     * msg : 成功
     * data : [{"realNameNo":"admin-0210"},{"realNameNo":"jkkjjk-0255"},{"realNameNo":"sdfsd-0340"},{"realNameNo":"wangzhepan-0001"},{"realNameNo":"wukjs-0254"},{"realNameNo":"冷休息休息-0427"},{"realNameNo":"水电费水电费水电费-0339"}]
     */

    private int code;
    private String msg;
    /**
     * realNameNo : admin-0210
     */

    private List<DataEntity> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public static class DataEntity {
        private String realNameNo;

        public String getRealNameNo() {
            return realNameNo;
        }

        public void setRealNameNo(String realNameNo) {
            this.realNameNo = realNameNo;
        }
    }
}
