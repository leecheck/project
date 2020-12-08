package com.gytech.entity.work;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.models.auth.In;

import java.time.LocalTime;
import java.io.Serializable;

/**
 * <p>
 * 海洋气象站位信息
 * </p>
 *
 * @author LQ
 * @since 2020-11-12
 */
@TableName("station_meteorology")
public class StationMeteorology extends Model<StationMeteorology> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 航段ID
     */
    private Integer cruiseSegmentId;

    /**
     * 元数据ID
     */
    private Integer metadataId;

    public Integer getRelateId() {
        return relateId;
    }

    public void setRelateId(Integer relateId) {
        this.relateId = relateId;
    }

    private Integer relateId;

    /**
     * 断面ID
     */
    private Integer sectionId;

    /**
     * 时区
     */
    private String timeZone;

    /**
     * 观测日期
     */
    private LocalDate obsDate;

    /**
     * 观测时间
     */
    private LocalTime obsTime;

    /**
     * 经度
     */
    private BigDecimal stationLon;

    /**
     * 纬度
     */
    private BigDecimal stationLat;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否显示
     */
    private Integer isDisplay;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCruiseSegmentId() {
        return cruiseSegmentId;
    }

    public void setCruiseSegmentId(Integer cruiseSegmentId) {
        this.cruiseSegmentId = cruiseSegmentId;
    }

    public Integer getMetadataId() {
        return metadataId;
    }

    public void setMetadataId(Integer metadataId) {
        this.metadataId = metadataId;
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public LocalDate getObsDate() {
        return obsDate;
    }

    public void setObsDate(LocalDate obsDate) {
        this.obsDate = obsDate;
    }

    public LocalTime getObsTime() {
        return obsTime;
    }

    public void setObsTime(LocalTime obsTime) {
        this.obsTime = obsTime;
    }

    public BigDecimal getStationLon() {
        return stationLon;
    }

    public void setStationLon(BigDecimal stationLon) {
        this.stationLon = stationLon;
    }

    public BigDecimal getStationLat() {
        return stationLat;
    }

    public void setStationLat(BigDecimal stationLat) {
        this.stationLat = stationLat;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getIsDisplay() {
        return isDisplay;
    }

    public void setIsDisplay(Integer isDisplay) {
        this.isDisplay = isDisplay;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "StationMeteorology{" +
        "id=" + id +
        ", cruiseSegmentId=" + cruiseSegmentId +
        ", metadataId=" + metadataId +
        ", sectionId=" + sectionId +
        ", timeZone=" + timeZone +
        ", obsDate=" + obsDate +
        ", obsTime=" + obsTime +
        ", stationLon=" + stationLon +
        ", stationLat=" + stationLat +
        ", remark=" + remark +
        ", isDisplay=" + isDisplay +
        "}";
    }
}
