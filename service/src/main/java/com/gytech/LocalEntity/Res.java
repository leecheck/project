package com.gytech.LocalEntity;


import java.util.HashMap;

/**
 * Created by LQ on 2018/5/10.
 */
public class Res {

    private String reason;
    private Boolean success;
    private String message;
    private String dealwith;
    private String progress;
    private Object data;

    public Res(){
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

    public Boolean error(){
        return !success;
    }

    public void setSuccess(Boolean succcess) {
        this.success = succcess;
    }

    public Res returnSuccess(Boolean succcess) {
        this.success = succcess;
        return this;
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

    public Res success(){
        this.success = true;
        return this;
    }
    public Res reason(String reason){
        this.reason += reason;
        return this;
    }

    public Res dealwith(String dealwith){
        this.dealwith += dealwith;
        return this;
    }

    public Res progress(String progress){
        this.progress += progress;
        return this;
    }

    public Res data(Object data){
        this.data = data;
        return this;
    }

    public String getMessage() {
        return this.reason;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
