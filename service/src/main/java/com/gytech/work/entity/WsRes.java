package com.gytech.work.entity;


import java.util.HashMap;

/**
 * Created by LQ on 2018/5/10.
 */
public class WsRes {

    /**
     * 消息类型
     */
    private String type;
    /**
     * 消息对象
     */
    private String target;
    private String reason;
    private Boolean success;
    private String message;
    private String dealwith;
    private String progress;
    private Object data;

    public WsRes(){
        this.success = false;//默认处理失败
        this.reason = "";
        this.dealwith = "";
        this.progress ="";
        this.data = new HashMap();
    }

    public static String DEAL_LXYW = "请将错误信息提供给联系运维人员";

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean succcess) {
        this.success = succcess;
    }

    public String getDealwith() {
        return dealwith;
    }

    public void setDealwith(String dealwith) {
        this.dealwith = dealwith;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public WsRes success(){
        this.success = true;
        return this;
    }
    public WsRes reason(String reason){
        this.reason += reason;
        return this;
    }

    public WsRes isSuccess(boolean success){
        this.success = success;
        return this;
    }

    public WsRes dealwith(String dealwith){
        this.dealwith += dealwith;
        return this;
    }

    public WsRes progress(String progress){
        this.progress += progress;
        return this;
    }


    public WsRes data(Object data){
        this.data = data;
        return this;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public WsRes type(String resType) {
        this.type = resType;
        return this;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
