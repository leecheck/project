package com.gytech.entity.admin;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.FieldStrategy;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 字典项表
 * </p>
 *
 * @author LQ
 * @since 2018-09-19
 */
@TableName("sys_log")
public class SysLog extends Model<SysLog> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 记录用户id
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 日志类型
     */
    @TableField("log_type")
    private String logType;
    /**
     * 创建时间
     */
    @TableField(value="create_time",fill = FieldFill.INSERT)
    private Date createTime;

    @TableField("over_time")
    private Date overTime;

    @TableField
    private String detail;

    public Date getOverTime() {
        return overTime;
    }

    public void setOverTime(Date overTime) {
        this.overTime = overTime;
    }

    /**
     * 事件
     */
    private String event;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SysLog{" +
        "id=" + id +
        ", userId=" + userId +
        ", logType=" + logType +
        ", createTime=" + createTime +
        ", event=" + event +
                ", overTime=" + overTime +
        "}";
    }
    public SysLog(){}
    /***
     * 带参构造
     *
     */
    public SysLog(Long userId,String logType,String logStr){
        this.userId=userId;
        this.logType=logType;
        this.createTime=new Date();
        this.overTime=new Date();
        this.event=logStr;
    }
}
