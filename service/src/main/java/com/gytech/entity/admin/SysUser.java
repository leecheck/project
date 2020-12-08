package com.gytech.entity.admin;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author LQ
 * @since 2018-09-19
 */
@TableName("sys_user")
public class SysUser extends Model<SysUser> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 用户名(登录名称)
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 用户头像
     */
    private String avatar;
    /**
     * 用户单位
     */
    @TableField("organ_id")
    private Long organId;
    /**
     * 用户名称(中文)
     */
    @TableField("display_name")
    private String displayName;
    /**
     * 上次使用的密码
     */
    @TableField("last_password")
    private String lastPassword;
    /**
     * 创建人
     */
    @TableField("create_user_id")
    private Long createUserId;
    /**
     * 修改人
     */
    @TableField("update_user_id")
    private Long updateUserId;
    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;
    /**
     * 修改时间
     */
    @TableField(value = "update_time")
    private Date updateTime;
    /**
     * 账户状态(1:激活,0:冻结)
     */
    @TableField("is_enabled")
    private Integer isEnabled;
    /**
     * 备注
     */
    private String remark;
    /**
     * 单位名称
     */
    @TableField("organ_name")
    private String organName;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 性别 male female
     */
    private String sex;
    /**
     * 照片 文件id
     */
    private String pictures;
    /**
     * 职务 职称
     */
    private String position;
    /**
     * 身份证
     */
    private String idcard;
    /**
     * 任职时间
     */
    @TableField("assume_time")
    private String assumeTime;
    /**
     * 出生日期
     */
    private String birth;
    /**
     * 工作时间
     */
    @TableField("work_time")
    private String workTime;
    /**
     * 学历
     */
    private String education;
    /**
     * 专业
     */
    private String major;
    /**
     * 毕业院校
     */
    private String school;
    /**
     * 政治面貌
     */
    @TableField("political_status")
    private String politicalStatus;
    private String birthplace;
    /**
     * 到站时间
     */
    private String intime;
    /**
     * 离站时间
     */
    private String outtime;
    /**
     * 证书编号
     */
    private String certifacate;

    /**
     * 政治面貌
     */
    @TableField("is_duty")
    private Integer isDuty;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Long getOrganId() {
        return organId;
    }

    public void setOrganId(Long organId) {
        this.organId = organId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getLastPassword() {
        return lastPassword;
    }

    public void setLastPassword(String lastPassword) {
        this.lastPassword = lastPassword;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(Integer isEnabled) {
        this.isEnabled = isEnabled;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOrganName() {
        return organName;
    }

    public void setOrganName(String organName) {
        this.organName = organName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getAssumeTime() {
        return assumeTime;
    }

    public void setAssumeTime(String assumeTime) {
        this.assumeTime = assumeTime;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getPoliticalStatus() {
        return politicalStatus;
    }

    public void setPoliticalStatus(String politicalStatus) {
        this.politicalStatus = politicalStatus;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public String getIntime() {
        return intime;
    }

    public void setIntime(String intime) {
        this.intime = intime;
    }

    public String getOuttime() {
        return outtime;
    }

    public void setOuttime(String outtime) {
        this.outtime = outtime;
    }

    public String getCertifacate() {
        return certifacate;
    }

    public void setCertifacate(String certifacate) {
        this.certifacate = certifacate;
    }

    public Integer getIsDuty() {
        return isDuty;
    }

    public void setIsDuty(Integer isDuty) {
        this.isDuty = isDuty;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", avatar='" + avatar + '\'' +
                ", organId=" + organId +
                ", displayName='" + displayName + '\'' +
                ", lastPassword='" + lastPassword + '\'' +
                ", createUserId=" + createUserId +
                ", updateUserId=" + updateUserId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", isEnabled=" + isEnabled +
                ", remark='" + remark + '\'' +
                ", organName='" + organName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", sex='" + sex + '\'' +
                ", pictures='" + pictures + '\'' +
                ", position='" + position + '\'' +
                ", idcard='" + idcard + '\'' +
                ", assumeTime='" + assumeTime + '\'' +
                ", birth='" + birth + '\'' +
                ", workTime='" + workTime + '\'' +
                ", education='" + education + '\'' +
                ", major='" + major + '\'' +
                ", school='" + school + '\'' +
                ", politicalStatus='" + politicalStatus + '\'' +
                ", birthplace='" + birthplace + '\'' +
                ", intime='" + intime + '\'' +
                ", outtime='" + outtime + '\'' +
                ", certifacate='" + certifacate + '\'' +
                ", isDuty=" + isDuty +
                '}';
    }
}
