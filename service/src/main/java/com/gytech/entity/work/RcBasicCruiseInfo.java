package com.gytech.entity.work;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 共享航次信息
 * </p>
 *
 * @author LQ
 * @since 2020-11-12
 */
@TableName("rc_basic_cruise_info")
public class RcBasicCruiseInfo extends Model<RcBasicCruiseInfo> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 航次名称
     */
    private String cruiseName;

    /**
     * 航次编号
     */
    private String cruiseDentifr;

    /**
     * 航次项目ID
     */
    private Integer projectInfoId;

    /**
     * 调查海域
     */
    private String invgionAreaId;

    /**
     * 调查季节
     */
    private String invgionSeason;

    /**
     * 调查执行时间
     */
    private String invgionExecTime;

    /**
     * 调查总天数
     */
    @TableField("Invgion_sum_days")
    private String invgionSumDays;

    /**
     * 经度1西边界
     */
    private String westLong;

    /**
     * 经度2东边界
     */
    private String eastLong;

    /**
     * 纬度1南边界
     */
    private String southLat;

    /**
     * 纬度2北边界
     */
    private String northLat;

    /**
     * 站位数
     */
    private Integer standNumber;

    /**
     * 观测断面数(条)
     */
    private Integer obsSectNumber;

    /**
     * 航段数
     */
    private Integer flighNumber;

    private String totalVoyage;

    /**
     * 首席科学家ID
     */
    private String caryItemNumber;

    /**
     * 搭载项目数
     */
    private Integer chiefId;

    /**
     * 参与人次
     */
    private Integer partPersonNumber;

    /**
     * 参与单位数量
     */
    private Integer partUntisNumber;

    /**
     * 承担单位
     */
    private Integer execOrgId;

    /**
     * 数据联系人ID
     */
    private Integer dataContactId;

    /**
     * 数据汇交进展
     */
    private Integer retaProgressId;

    /**
     * 进展变化时间
     */
    private LocalDate dataChangeTime;

    /**
     * 编辑人ID
     */
    private Integer dataEditId;

    /**
     * 编辑时间
     */
    private LocalDateTime editTime;

    /**
     * 备注
     */
    private String cruiseRemark;

    /**
     * 是否显示
     */
    private Integer isDisplay;

    /**
     * 审核id
     */
    private Integer examProgressId;

    private String chiefExecutiveScientistId;

    /**
     *  项目负责人ID
     */
    private Integer projectLeadId;

    /**
     * 图片id
     */
    private Integer imagesInfoId;

    /**
     * 参加人次(去重)
     */
    private Integer partDictPersonNumber;

    /**
     * 起始时间
     */
    private LocalDate startTime;

    /**
     * 结束时间
     */
    private LocalDate endTime;

    /**
     * 航次报告撰写人
     */
    private String cruiseReportId;

    /**
     * 数据报告撰写人
     */
    private String dataReportId;

    /**
     * 数据文档id
     */
    private String dataFileId;

    /**
     * 预留1
     */
    @TableField("spareValue1")
    private String spareValue1;

    /**
     * 预留2
     */
    @TableField("spareValue2")
    private String spareValue2;

    /**
     * 预留3
     */
    @TableField("spareValue3")
    private String spareValue3;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCruiseName() {
        return cruiseName;
    }

    public void setCruiseName(String cruiseName) {
        this.cruiseName = cruiseName;
    }

    public String getCruiseDentifr() {
        return cruiseDentifr;
    }

    public void setCruiseDentifr(String cruiseDentifr) {
        this.cruiseDentifr = cruiseDentifr;
    }

    public Integer getProjectInfoId() {
        return projectInfoId;
    }

    public void setProjectInfoId(Integer projectInfoId) {
        this.projectInfoId = projectInfoId;
    }

    public String getInvgionAreaId() {
        return invgionAreaId;
    }

    public void setInvgionAreaId(String invgionAreaId) {
        this.invgionAreaId = invgionAreaId;
    }

    public String getInvgionSeason() {
        return invgionSeason;
    }

    public void setInvgionSeason(String invgionSeason) {
        this.invgionSeason = invgionSeason;
    }

    public String getInvgionExecTime() {
        return invgionExecTime;
    }

    public void setInvgionExecTime(String invgionExecTime) {
        this.invgionExecTime = invgionExecTime;
    }

    public String getInvgionSumDays() {
        return invgionSumDays;
    }

    public void setInvgionSumDays(String invgionSumDays) {
        this.invgionSumDays = invgionSumDays;
    }

    public String getWestLong() {
        return westLong;
    }

    public void setWestLong(String westLong) {
        this.westLong = westLong;
    }

    public String getEastLong() {
        return eastLong;
    }

    public void setEastLong(String eastLong) {
        this.eastLong = eastLong;
    }

    public String getSouthLat() {
        return southLat;
    }

    public void setSouthLat(String southLat) {
        this.southLat = southLat;
    }

    public String getNorthLat() {
        return northLat;
    }

    public void setNorthLat(String northLat) {
        this.northLat = northLat;
    }

    public Integer getStandNumber() {
        return standNumber;
    }

    public void setStandNumber(Integer standNumber) {
        this.standNumber = standNumber;
    }

    public Integer getObsSectNumber() {
        return obsSectNumber;
    }

    public void setObsSectNumber(Integer obsSectNumber) {
        this.obsSectNumber = obsSectNumber;
    }

    public Integer getFlighNumber() {
        return flighNumber;
    }

    public void setFlighNumber(Integer flighNumber) {
        this.flighNumber = flighNumber;
    }

    public String getTotalVoyage() {
        return totalVoyage;
    }

    public void setTotalVoyage(String totalVoyage) {
        this.totalVoyage = totalVoyage;
    }

    public String getCaryItemNumber() {
        return caryItemNumber;
    }

    public void setCaryItemNumber(String caryItemNumber) {
        this.caryItemNumber = caryItemNumber;
    }

    public Integer getChiefId() {
        return chiefId;
    }

    public void setChiefId(Integer chiefId) {
        this.chiefId = chiefId;
    }

    public Integer getPartPersonNumber() {
        return partPersonNumber;
    }

    public void setPartPersonNumber(Integer partPersonNumber) {
        this.partPersonNumber = partPersonNumber;
    }

    public Integer getPartUntisNumber() {
        return partUntisNumber;
    }

    public void setPartUntisNumber(Integer partUntisNumber) {
        this.partUntisNumber = partUntisNumber;
    }

    public Integer getExecOrgId() {
        return execOrgId;
    }

    public void setExecOrgId(Integer execOrgId) {
        this.execOrgId = execOrgId;
    }

    public Integer getDataContactId() {
        return dataContactId;
    }

    public void setDataContactId(Integer dataContactId) {
        this.dataContactId = dataContactId;
    }

    public Integer getRetaProgressId() {
        return retaProgressId;
    }

    public void setRetaProgressId(Integer retaProgressId) {
        this.retaProgressId = retaProgressId;
    }

    public LocalDate getDataChangeTime() {
        return dataChangeTime;
    }

    public void setDataChangeTime(LocalDate dataChangeTime) {
        this.dataChangeTime = dataChangeTime;
    }

    public Integer getDataEditId() {
        return dataEditId;
    }

    public void setDataEditId(Integer dataEditId) {
        this.dataEditId = dataEditId;
    }

    public LocalDateTime getEditTime() {
        return editTime;
    }

    public void setEditTime(LocalDateTime editTime) {
        this.editTime = editTime;
    }

    public String getCruiseRemark() {
        return cruiseRemark;
    }

    public void setCruiseRemark(String cruiseRemark) {
        this.cruiseRemark = cruiseRemark;
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

    public String getChiefExecutiveScientistId() {
        return chiefExecutiveScientistId;
    }

    public void setChiefExecutiveScientistId(String chiefExecutiveScientistId) {
        this.chiefExecutiveScientistId = chiefExecutiveScientistId;
    }

    public Integer getProjectLeadId() {
        return projectLeadId;
    }

    public void setProjectLeadId(Integer projectLeadId) {
        this.projectLeadId = projectLeadId;
    }

    public Integer getImagesInfoId() {
        return imagesInfoId;
    }

    public void setImagesInfoId(Integer imagesInfoId) {
        this.imagesInfoId = imagesInfoId;
    }

    public Integer getPartDictPersonNumber() {
        return partDictPersonNumber;
    }

    public void setPartDictPersonNumber(Integer partDictPersonNumber) {
        this.partDictPersonNumber = partDictPersonNumber;
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

    public String getCruiseReportId() {
        return cruiseReportId;
    }

    public void setCruiseReportId(String cruiseReportId) {
        this.cruiseReportId = cruiseReportId;
    }

    public String getDataReportId() {
        return dataReportId;
    }

    public void setDataReportId(String dataReportId) {
        this.dataReportId = dataReportId;
    }

    public String getDataFileId() {
        return dataFileId;
    }

    public void setDataFileId(String dataFileId) {
        this.dataFileId = dataFileId;
    }

    public String getSpareValue1() {
        return spareValue1;
    }

    public void setSpareValue1(String spareValue1) {
        this.spareValue1 = spareValue1;
    }

    public String getSpareValue2() {
        return spareValue2;
    }

    public void setSpareValue2(String spareValue2) {
        this.spareValue2 = spareValue2;
    }

    public String getSpareValue3() {
        return spareValue3;
    }

    public void setSpareValue3(String spareValue3) {
        this.spareValue3 = spareValue3;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "RcBasicCruiseInfo{" +
        "id=" + id +
        ", cruiseName=" + cruiseName +
        ", cruiseDentifr=" + cruiseDentifr +
        ", projectInfoId=" + projectInfoId +
        ", invgionAreaId=" + invgionAreaId +
        ", invgionSeason=" + invgionSeason +
        ", invgionExecTime=" + invgionExecTime +
        ", invgionSumDays=" + invgionSumDays +
        ", westLong=" + westLong +
        ", eastLong=" + eastLong +
        ", southLat=" + southLat +
        ", northLat=" + northLat +
        ", standNumber=" + standNumber +
        ", obsSectNumber=" + obsSectNumber +
        ", flighNumber=" + flighNumber +
        ", totalVoyage=" + totalVoyage +
        ", caryItemNumber=" + caryItemNumber +
        ", chiefId=" + chiefId +
        ", partPersonNumber=" + partPersonNumber +
        ", partUntisNumber=" + partUntisNumber +
        ", execOrgId=" + execOrgId +
        ", dataContactId=" + dataContactId +
        ", retaProgressId=" + retaProgressId +
        ", dataChangeTime=" + dataChangeTime +
        ", dataEditId=" + dataEditId +
        ", editTime=" + editTime +
        ", cruiseRemark=" + cruiseRemark +
        ", isDisplay=" + isDisplay +
        ", examProgressId=" + examProgressId +
        ", chiefExecutiveScientistId=" + chiefExecutiveScientistId +
        ", projectLeadId=" + projectLeadId +
        ", imagesInfoId=" + imagesInfoId +
        ", partDictPersonNumber=" + partDictPersonNumber +
        ", startTime=" + startTime +
        ", endTime=" + endTime +
        ", cruiseReportId=" + cruiseReportId +
        ", dataReportId=" + dataReportId +
        ", dataFileId=" + dataFileId +
        ", spareValue1=" + spareValue1 +
        ", spareValue2=" + spareValue2 +
        ", spareValue3=" + spareValue3 +
        "}";
    }
}
