package com.gytech.LocalEntity;


import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by LQ on 2018/5/10.
 */
public class CronRes extends Res {

    public static final String TYPE_MONTH = "TYPE_MONTH";
    public static final String TYPE_DAY = "TYPE_DAY";

    private Date startTime;
    private Date outRangeTime;
    private String desc;
    private String type;
    private List nextTime;

    public CronRes(){
        this.setSuccess(false);
    }

    public static String DEAL_LXYW = "请将错误信息提供给联系运维人员";



    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getOutRangeTime() {
        return outRangeTime;
    }

    public void setOutRangeTime(Date outRangeTime) {
        this.outRangeTime = outRangeTime;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List getNextTime() {
        return nextTime;
    }

    public void setNextTime(List nextTime) {
        this.nextTime = nextTime;
    }
}
