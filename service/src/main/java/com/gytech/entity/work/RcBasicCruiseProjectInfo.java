package com.gytech.entity.work;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 共享航次项目信息
 * </p>
 *
 * @author LQ
 * @since 2020-11-12
 */
@TableName("rc_basic_cruise_project_info")
public class RcBasicCruiseProjectInfo extends Model<RcBasicCruiseProjectInfo> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 项目批准号
     */
    private String projectCode;

    /**
     * 项目来源
     */
    private String projectSource;

    /**
     * 项目类型
     */
    private String projectType;

    /**
     * 调查海域
     */
    private String invgionAreaId;

    /**
     * 起始时间
     */
    private LocalDate startTime;

    /**
     * 终止时间
     */
    private LocalDate endTime;

    /**
     * 调查内容
     */
    private String obsInfo;

    /**
     * 科学目标
     */
    private String scienObjs;

    /**
     * 项目经费
     */
    private Double projectFund;

    /**
     * 承担单位
     */
    private Integer projectCompanysId;

    /**
     * 项目负责人ID
     */
    private Integer projectLeadId;

    /**
     * 编辑人ID
     */
    private Integer projectEditId;

    /**
     * 编辑时间
     */
    private LocalDateTime editTime;

    /**
     * 数据汇交进展
     */
    private Integer retaProgressId;

    /**
     * 进展变化时间
     */
    private LocalDate retaChangeTime;

    /**
     * 备注
     */
    private String projectRemark;

    /**
     * 是否显示
     */
    private Integer isDisplay;

    /**
     * 审核id
     */
    private Integer examProgressId;

    /**
     * 首席科学家id
     */
    private String chiefScientistId;

    /**
     * 调查终止时间
     */
    private LocalDate investEndTime;

    /**
     * 调查起始时间
     */
    private String investStartTime;

    /**
     * 执行首席科学家id
     */
    private String chiefExecutiveScientistId;

    /**
     * 提交状态
     */
    private String submitStatus;

    /**
     * 提交时间
     */
    private String submitTime;

    /**
     * 执行年
     */
    private String implYear;

    /**
     * 科考船id
     */
    private String surshipId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectSource() {
        return projectSource;
    }

    public void setProjectSource(String projectSource) {
        this.projectSource = projectSource;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getInvgionAreaId() {
        return invgionAreaId;
    }

    public void setInvgionAreaId(String invgionAreaId) {
        this.invgionAreaId = invgionAreaId;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
    }

    public String getObsInfo() {
        return obsInfo;
    }

    public void setObsInfo(String obsInfo) {
        this.obsInfo = obsInfo;
    }

    public String getScienObjs() {
        return scienObjs;
    }

    public void setScienObjs(String scienObjs) {
        this.scienObjs = scienObjs;
    }

    public Double getProjectFund() {
        return projectFund;
    }

    public void setProjectFund(Double projectFund) {
        this.projectFund = projectFund;
    }

    public Integer getProjectCompanysId() {
        return projectCompanysId;
    }

    public void setProjectCompanysId(Integer projectCompanysId) {
        this.projectCompanysId = projectCompanysId;
    }

    public Integer getProjectLeadId() {
        return projectLeadId;
    }

    public void setProjectLeadId(Integer projectLeadId) {
        this.projectLeadId = projectLeadId;
    }

    public Integer getProjectEditId() {
        return projectEditId;
    }

    public void setProjectEditId(Integer projectEditId) {
        this.projectEditId = projectEditId;
    }

    public LocalDateTime getEditTime() {
        return editTime;
    }

    public void setEditTime(LocalDateTime editTime) {
        this.editTime = editTime;
    }

    public Integer getRetaProgressId() {
        return retaProgressId;
    }

    public void setRetaProgressId(Integer retaProgressId) {
        this.retaProgressId = retaProgressId;
    }

    public LocalDate getRetaChangeTime() {
        return retaChangeTime;
    }

    public void setRetaChangeTime(LocalDate retaChangeTime) {
        this.retaChangeTime = retaChangeTime;
    }

    public String getProjectRemark() {
        return projectRemark;
    }

    public void setProjectRemark(String projectRemark) {
        this.projectRemark = projectRemark;
    }

    public Integer getIsDisplay() {
        return isDisplay;
    }

    public void setIsDisplay(Integer isDisplay) {
        this.isDisplay = isDisplay;
    }

    public Integer getExamProgressId() {
        return examProgressId;
    }

    public void setExamProgressId(Integer examProgressId) {
        this.examProgressId = examProgressId;
    }

    public String getChiefScientistId() {
        return chiefScientistId;
    }

    public void setChiefScientistId(String chiefScientistId) {
        this.chiefScientistId = chiefScientistId;
    }

    public LocalDate getInvestEndTime() {
        return investEndTime;
    }

    public void setInvestEndTime(LocalDate investEndTime) {
        this.investEndTime = investEndTime;
    }

    public String getInvestStartTime() {
        return investStartTime;
    }

    public void setInvestStartTime(String investStartTime) {
        this.investStartTime = investStartTime;
    }

    public String getChiefExecutiveScientistId() {
        return chiefExecutiveScientistId;
    }

    public void setChiefExecutiveScientistId(String chiefExecutiveScientistId) {
        this.chiefExecutiveScientistId = chiefExecutiveScientistId;
    }

    public String getSubmitStatus() {
        return submitStatus;
    }

    public void setSubmitStatus(String submitStatus) {
        this.submitStatus = submitStatus;
    }

    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }

    public String getImplYear() {
        return implYear;
    }

    public void setImplYear(String implYear) {
        this.implYear = implYear;
    }

    public String getSurshipId() {
        return surshipId;
    }

    public void setSurshipId(String surshipId) {
        this.surshipId = surshipId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "RcBasicCruiseProjectInfo{" +
        "id=" + id +
        ", projectName=" + projectName +
        ", projectCode=" + projectCode +
        ", projectSource=" + projectSource +
        ", projectType=" + projectType +
        ", invgionAreaId=" + invgionAreaId +
        ", startTime=" + startTime +
        ", endTime=" + endTime +
        ", obsInfo=" + obsInfo +
        ", scienObjs=" + scienObjs +
        ", projectFund=" + projectFund +
        ", projectCompanysId=" + projectCompanysId +
        ", projectLeadId=" + projectLeadId +
        ", projectEditId=" + projectEditId +
        ", editTime=" + editTime +
        ", retaProgressId=" + retaProgressId +
        ", retaChangeTime=" + retaChangeTime +
        ", projectRemark=" + projectRemark +
        ", isDisplay=" + isDisplay +
        ", examProgressId=" + examProgressId +
        ", chiefScientistId=" + chiefScientistId +
        ", investEndTime=" + investEndTime +
        ", investStartTime=" + investStartTime +
        ", chiefExecutiveScientistId=" + chiefExecutiveScientistId +
        ", submitStatus=" + submitStatus +
        ", submitTime=" + submitTime +
        ", implYear=" + implYear +
        ", surshipId=" + surshipId +
        "}";
    }
}
