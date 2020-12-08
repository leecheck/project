package com.gytech.work.entity;

/**
 * Created by LQ on 2019/10/16.
 * com.gytech.work.entity
 */
public class Key {

    /**
     * 输入 token
     * 输出data “用户XX成功建立连接”
     */
    public final static String BASE_USERTOKEN = "BASE_USERTOKEN"; //配置socket用户信息

    /**
     * 输入 target：oid
     * 输出data stationduty
     */
    public final static String DUTY_ORG_INFO = "DUTY_ORG_INFO"; //查询站点值班事务

    /**
     * 输入 target：duty_record_id data：userName
     * 输出data stationduty
     */
    public final static String DUTY_ORG_CHANGE = "DUTY_ORG_CHANGE"; //交班

    /**
     * 输入 target：duty_record_id
     * 输出data stationduty
     */
    public final static String DUTY_ORG_OFF = "DUTY_ORG_OFF"; //下班

    /**
     * 输入 target：oid
     * 输出data stationduty
     */
    public final static String DUTY_ORG_ON = "DUTY_ORG_ON"; //上班

    /**
     * 输入 target：oid data:log
     * 输出data stationduty
     */
    public final static String DUTY_LOG = "DUTY_LOG"; //值班日志

}
