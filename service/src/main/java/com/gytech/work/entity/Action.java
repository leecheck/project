package com.gytech.work.entity;

import java.util.Date;

/**
 * 站点动态
 * Created by LQ on 2019/9/25.
 * com.gytech.work.entity
 */
public class Action {

    /**
     * 时间
     */
    private Date time;

    /**
     * 事件
     */
    private String thing;

    /**
     * 相关人员
     */
    private String user;

    /**
     * 相关对象
     */
    private String object;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getThing() {
        return thing;
    }

    public void setThing(String thing) {
        this.thing = thing;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }
}
