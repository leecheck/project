package com.gytech.Security.ex;

/**
 * Created by LQ on 2018/9/11.
 * Security.ex
 */
public class LessPowerEx extends Exception {

    public final static String TIP = "无法完成操作，缺少对应的权限：";

    public LessPowerEx(String msg, Throwable t) {
        super(msg, t);
    }

    public LessPowerEx(String msg) {
        super(msg);
    }

}