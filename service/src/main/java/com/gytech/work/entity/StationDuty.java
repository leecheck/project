package com.gytech.work.entity;

import com.gytech.Security.entity.UserInfo;
import com.gytech.entity.admin.SysOrganization;

import java.util.Date;

/**
 * Created by LQ on 2019/9/25.
 * com.gytech.work.entity
 */
public class StationDuty {

    public StationDuty(UserInfo user, SysOrganization org) {
        if (user == null){
            this.isOnDuty = false;
        }
        this.user = user;
        this.org = org;
        this.turnOn = new Date();
        this.turnOff = calcOffTime();
    }

    public static Date calcOffTime(){
        return new Date();
    }

    private boolean isOnDuty;

    private UserInfo user;

    private SysOrganization org;

    private String DutyRecordId;

    private Date turnOn;

    private Date turnOff;

    public boolean isOnDuty() {
        return isOnDuty;
    }

    public void setOnDuty(boolean onDuty) {
        isOnDuty = onDuty;
    }

    public String getDutyRecordId() {
        return DutyRecordId;
    }

    public void setDutyRecordId(String dutyRecordId) {
        DutyRecordId = dutyRecordId;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }

    public SysOrganization getOrg() {
        return org;
    }

    public void setOrg(SysOrganization org) {
        this.org = org;
    }

    public Date getTurnOn() {
        return turnOn;
    }

    public void setTurnOn(Date turnOn) {
        this.turnOn = turnOn;
    }

    public Date getTurnOff() {
        return turnOff;
    }

    public void setTurnOff(Date turnOff) {
        this.turnOff = turnOff;
    }

    public Boolean handOff(){
        this.user = null;
        this.isOnDuty = false;
        this.turnOff = null;
        this.turnOn = null;
        return true;
    }

    public Boolean handOn(UserInfo userInfo){
        Date date = new Date();
        this.user = userInfo;
        this.isOnDuty = true;
        this.turnOff = date;
        this.turnOn = date;
        return true;
    }

    public Boolean handOver(UserInfo user){
        //todo 计算倒班时间
        Date date = new Date();
        if (date.getTime()>this.turnOff.getTime()){
            this.user = user;
            this.turnOn = date;
            this.turnOff = date;
            return true;
        }else {
            return false;
        }
    }


}
