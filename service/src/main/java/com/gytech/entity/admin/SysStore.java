package com.gytech.entity.admin;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author LQ
 * @since 2018-12-03
 */
@TableName("sys_store")
public class SysStore extends Model<SysStore> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * ip
     */
    @TableField("ip")
    private String ip;
    /**
     * port
     */
    @TableField("port")
    private String port;
    /**
     * 用户名
     */
    @TableField("instance_user")
    private String instanceUser;
    /**
     * 数据库密码
     */
    @TableField("instance_pass")
    private String instancePass;
    /**
     * 数据库类型
     */
    @TableField("instance_type")
    private String instanceType;

    /**
     * 数据库名称
     */
    @TableField("database_name")
    private String databaseName;

    /**
     * 定时corn
     */
    @TableField("corn")
    private String corn;

    /**
     * 定时corn
     */
    @TableField("active_time")
    private Integer activeTime;

    /**
     * 备注
     */
    private String remark;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInstanceUser() {
        return instanceUser;
    }

    public void setInstanceUser(String instanceUser) {
        this.instanceUser = instanceUser;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getInstancePass() {
        return instancePass;
    }

    public void setInstancePass(String instancePass) {
        this.instancePass = instancePass;
    }

    public String getInstanceType() {
        return instanceType;
    }

    public void setInstanceType(String instanceType) {
        this.instanceType = instanceType;
    }

    public String getCorn() {
        return corn;
    }

    public void setCorn(String corn) {
        this.corn = corn;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Integer activeTime) {
        this.activeTime = activeTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SysStore{" +
        "id=" + id +
        ", instanceType=" + instanceType +
        ", databaseName=" + databaseName +
        ", remark=" + remark +
        "}";
    }
}
