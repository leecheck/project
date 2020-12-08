package com.gytech.entity.admin;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 日志表
 * </p>
 *
 * @author LQ
 * @since 2018-09-19
 */
@TableName("sys_run_log")
public class SysRunLog extends Model<SysRunLog> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 用户名称(显示名)
     */
    @TableField("user_name")
    private String userName;
    /**
     * 存储异常栈信息,或者运行的sql) json
     */
    @TableField("action_log")
    private String actionLog;
    /**
     * 操作ip地址
     */
    @TableField("action_ip")
    private String actionIp;
    /**
     * 操作描述
     */
    @TableField("action_desc")
    private String actionDesc;
    /**
     * 动作开始时间
     */
    @TableField("action_start")
    private Date actionStart;
    /**
     * 动作结束时间
     */
    @TableField("action_end")
    private Date actionEnd;
    /**
     * 总执行时间(微秒)
     */
    @TableField("action_count")
    private Long actionCount;
    /**
     * 操作类
     */
    @TableField("action_class")
    private String actionClass;
    /**
     * 操作方法
     */
    @TableField("action_method")
    private String actionMethod;
    /**
     * 方法参数
     */
    @TableField("action_args")
    private String actionArgs;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;


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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getActionLog() {
        return actionLog;
    }

    public void setActionLog(String actionLog) {
        this.actionLog = actionLog;
    }

    public String getActionIp() {
        return actionIp;
    }

    public void setActionIp(String actionIp) {
        this.actionIp = actionIp;
    }

    public String getActionDesc() {
        return actionDesc;
    }

    public void setActionDesc(String actionDesc) {
        this.actionDesc = actionDesc;
    }

    public Date getActionStart() {
        return actionStart;
    }

    public void setActionStart(Date actionStart) {
        this.actionStart = actionStart;
    }

    public Date getActionEnd() {
        return actionEnd;
    }

    public void setActionEnd(Date actionEnd) {
        this.actionEnd = actionEnd;
    }

    public Long getActionCount() {
        return actionCount;
    }

    public void setActionCount(Long actionCount) {
        this.actionCount = actionCount;
    }

    public String getActionClass() {
        return actionClass;
    }

    public void setActionClass(String actionClass) {
        this.actionClass = actionClass;
    }

    public String getActionMethod() {
        return actionMethod;
    }

    public void setActionMethod(String actionMethod) {
        this.actionMethod = actionMethod;
    }

    public String getActionArgs() {
        return actionArgs;
    }

    public void setActionArgs(String actionArgs) {
        this.actionArgs = actionArgs;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SysRunLog{" +
        "id=" + id +
        ", userId=" + userId +
        ", userName=" + userName +
        ", actionLog=" + actionLog +
        ", actionIp=" + actionIp +
        ", actionDesc=" + actionDesc +
        ", actionStart=" + actionStart +
        ", actionEnd=" + actionEnd +
        ", actionCount=" + actionCount +
        ", actionClass=" + actionClass +
        ", actionMethod=" + actionMethod +
        ", actionArgs=" + actionArgs +
        ", createTime=" + createTime +
        "}";
    }
}
