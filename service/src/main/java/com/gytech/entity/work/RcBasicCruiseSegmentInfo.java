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
 * 共享航次航段信息
 * </p>
 *
 * @author LQ
 * @since 2020-11-12
 */
@TableName("rc_basic_cruise_segment_info")
public class RcBasicCruiseSegmentInfo extends Model<RcBasicCruiseSegmentInfo> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 航段名称
     */
    private String cruiseSegmentName;

    /**
     * 航次ID
     */
    private Integer cruiseId;

    private String surshipInfoId;

    /**
     * 调查海域
     */
    private String invgionAreaId;

    /**
     * 起止时间
     */
    private String startTime;

    /**
     * 时间标准
     */
    private Integer timeStandardId;

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
     * 调查内容
     */
    private String obsInfo;

    /**
     * 站位数
     */
    private Integer standNumber;

    /**
     * 测线数
     */
    private Integer mearLineNumber;

    /**
     * 测线长度
     */
    private Integer mearLineLength;

    /**
     * 断面数
     */
    private Integer obsSectNumber;

    /**
     * 执行首席科学家ID
     */
    private String impChiefId;

    /**
     * 搭载项目数
     */
    private Integer caryItemNumber;

    /**
     * 承担单位
     */
    private Integer execOrgId;

    /**
     * 数据联系人ID
     */
    private String dataContactId;

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
    private String cruiseSegmentRemark;

    /**
     * 是否显示
     */
    private Integer isDisplay;

    /**
     * 图片id
     */
    private String imagesInfoId;

    /**
     * 起始时间
     */
    private LocalDate startDate;

    /**
     * 结束时间
     */
    private LocalDate endDate;

    /**
     * 参加人次(去重)
     */
    private Integer partDictPersonNumber;

    /**
     * 参与人次(不去重)
     */
    private Integer partPersonNumber;

    /**
     * 参与单位数量
     */
    private Integer partUntisNumber;

    /**
     * 航程（海里）
     */
    private String cruiseVoyage;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCruiseSegmentName() {
        return cruiseSegmentName;
    }

    public void setCruiseSegmentName(String cruiseSegmentName) {
        this.cruiseSegmentName = cruiseSegmentName;
    }

    public Integer getCruiseId() {
        return cruiseId;
    }

    public void setCruiseId(Integer cruiseId) {
        this.cruiseId = cruiseId;
    }

    public String getSurshipInfoId() {
        return surshipInfoId;
    }

    public void setSurshipInfoId(String surshipInfoId) {
        this.surshipInfoId = surshipInfoId;
    }

    public String getInvgionAreaId() {
        return invgionAreaId;
    }

    public void setInvgionAreaId(String invgionAreaId) {
        this.invgionAreaId = invgionAreaId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Integer getTimeStandardId() {
        return timeStandardId;
    }

    public void setTimeStandardId(Integer timeStandardId) {
        this.timeStandardId = timeStandardId;
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

    public String getObsInfo() {
        return obsInfo;
    }

    public void setObsInfo(String obsInfo) {
        this.obsInfo = obsInfo;
    }

    public Integer getStandNumber() {
        return standNumber;
    }

    public void setStandNumber(Integer standNumber) {
        this.standNumber = standNumber;
    }

    public Integer getMearLineNumber() {
        return mearLineNumber;
    }

    public void setMearLineNumber(Integer mearLineNumber) {
        this.mearLineNumber = mearLineNumber;
    }

    public Integer getMearLineLength() {
        return mearLineLength;
    }

    public void setMearLineLength(Integer mearLineLength) {
        this.mearLineLength = mearLineLength;
    }

    public Integer getObsSectNumber() {
        return obsSectNumber;
    }

    public void setObsSectNumber(Integer obsSectNumber) {
        this.obsSectNumber = obsSectNumber;
    }

    public String getImpChiefId() {
        return impChiefId;
    }

    public void setImpChiefId(String impChiefId) {
        this.impChiefId = impChiefId;
    }

    public Integer getCaryItemNumber() {
        return caryItemNumber;
    }

    public void setCaryItemNumber(Integer caryItemNumber) {
        this.caryItemNumber = caryItemNumber;
    }

    public Integer getExecOrgId() {
        return execOrgId;
    }

    public void setExecOrgId(Integer execOrgId) {
        this.execOrgId = execOrgId;
    }

    public String getDataContactId() {
        return dataContactId;
    }

    public void setDataContactId(String dataContactId) {
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

    public String getCruiseSegmentRemark() {
        return cruiseSegmentRemark;
    }

    public void setCruiseSegmentRemark(String cruiseSegmentRemark) {
        this.cruiseSegmentRemark = cruiseSegmentRemark;
    }

    public Integer getIsDisplay() {
        return isDisplay;
    }

    public void setIsDisplay(Integer isDisplay) {
        this.isDisplay = isDisplay;
    }

    public String getImagesInfoId() {
        return imagesInfoId;
    }

    public void setImagesInfoId(String imagesInfoId) {
        this.imagesInfoId = imagesInfoId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Integer getPartDictPersonNumber() {
        return partDictPersonNumber;
    }

    public void setPartDictPersonNumber(Integer partDictPersonNumber) {
        this.partDictPersonNumber = partDictPersonNumber;
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

    public String getCruiseVoyage() {
        return cruiseVoyage;
    }

    public void setCruiseVoyage(String cruiseVoyage) {
        this.cruiseVoyage = cruiseVoyage;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "RcBasicCruiseSegmentInfo{" +
        "id=" + id +
        ", cruiseSegmentName=" + cruiseSegmentName +
        ", cruiseId=" + cruiseId +
        ", surshipInfoId=" + surshipInfoId +
        ", invgionAreaId=" + invgionAreaId +
        ", startTime=" + startTime +
        ", timeStandardId=" + timeStandardId +
        ", westLong=" + westLong +
        ", eastLong=" + eastLong +
        ", southLat=" + southLat +
        ", northLat=" + northLat +
        ", obsInfo=" + obsInfo +
        ", standNumber=" + standNumber +
        ", mearLineNumber=" + mearLineNumber +
        ", mearLineLength=" + mearLineLength +
        ", obsSectNumber=" + obsSectNumber +
        ", impChiefId=" + impChiefId +
        ", caryItemNumber=" + caryItemNumber +
        ", execOrgId=" + execOrgId +
        ", dataContactId=" + dataContactId +
        ", retaProgressId=" + retaProgressId +
        ", dataChangeTime=" + dataChangeTime +
        ", dataEditId=" + dataEditId +
        ", editTime=" + editTime +
        ", cruiseSegmentRemark=" + cruiseSegmentRemark +
        ", isDisplay=" + isDisplay +
        ", imagesInfoId=" + imagesInfoId +
        ", startDate=" + startDate +
        ", endDate=" + endDate +
        ", partDictPersonNumber=" + partDictPersonNumber +
        ", partPersonNumber=" + partPersonNumber +
        ", partUntisNumber=" + partUntisNumber +
        ", cruiseVoyage=" + cruiseVoyage +
        "}";
    }
}
