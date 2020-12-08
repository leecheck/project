package com.gytech.entity.admin;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2019-06-13
 */
@TableName("sys_sms")
public class SysSms extends Model<SysSms> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * sms名称
     */
    @TableField("sms_name")
    private String smsName;
    /**
     * sms模板（云上复制过来）
     */
    @TableField("sms_template")
    private String smsTemplate;
    /**
     * sms说明
     */
    @TableField("sms_note")
    private String smsNote;
    /**
     * sms发送名单（userids）
     */
    @TableField("sms_user")
    private String smsUser;
    /**
     * sms签名
     */
    @TableField("sms_singname")
    private String smsSingname;
    /**
     * smsCode 跟云上对应
     */
    @TableField("sms_code")
    private String smsCode;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSmsName() {
        return smsName;
    }

    public void setSmsName(String smsName) {
        this.smsName = smsName;
    }

    public String getSmsTemplate() {
        return smsTemplate;
    }

    public void setSmsTemplate(String smsTemplate) {
        this.smsTemplate = smsTemplate;
    }

    public String getSmsNote() {
        return smsNote;
    }

    public void setSmsNote(String smsNote) {
        this.smsNote = smsNote;
    }

    public String getSmsUser() {
        return smsUser;
    }

    public void setSmsUser(String smsUser) {
        this.smsUser = smsUser;
    }

    public String getSmsSingname() {
        return smsSingname;
    }

    public void setSmsSingname(String smsSingname) {
        this.smsSingname = smsSingname;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SysSms{" +
        "id=" + id +
        ", smsName=" + smsName +
        ", smsTemplate=" + smsTemplate +
        ", smsNote=" + smsNote +
        ", smsUser=" + smsUser +
        ", smsSingname=" + smsSingname +
        ", smsCode=" + smsCode +
        "}";
    }
}
