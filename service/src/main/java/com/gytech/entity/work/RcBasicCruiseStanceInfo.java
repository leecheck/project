package com.gytech.entity.work;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 站位信息
 * </p>
 *
 * @author LQ
 * @since 2020-11-12
 */
@TableName("rc_basic_cruise_stance_info")
public class RcBasicCruiseStanceInfo extends Model<RcBasicCruiseStanceInfo> {

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

    /**
     * 站名
     */
    private String cruiseStanceName;

    /**
     * 站位图ID
     */
    private Integer imagesInfoId;

    /**
     * 水深
     */
    private Double waterDept;

    /**
     * 到站时间
     */
    private LocalDateTime arrStationTime;

    /**
     * 离站时间
     */
    private LocalDateTime outStationTime;

    /**
     * 时间标准
     */
    private Integer timeStandardId;

    /**
     * 经度
     */
    private BigDecimal stanceLong;

    /**
     * 纬度
     */
    private BigDecimal stanceLat;

    /**
     * 观测/取样方式
     */
    private Integer obsampMethodId;

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
    private String cruiseStanceRemark;

    /**
     * 是否显示
     */
    private Integer isDisplay;

    /**
     * 执行项目
     */
    private String implProjects;


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

    public String getCruiseStanceName() {
        return cruiseStanceName;
    }

    public void setCruiseStanceName(String cruiseStanceName) {
        this.cruiseStanceName = cruiseStanceName;
    }

    public Integer getImagesInfoId() {
        return imagesInfoId;
    }

    public void setImagesInfoId(Integer imagesInfoId) {
        this.imagesInfoId = imagesInfoId;
    }

    public Double getWaterDept() {
        return waterDept;
    }

    public void setWaterDept(Double waterDept) {
        this.waterDept = waterDept;
    }

    public LocalDateTime getArrStationTime() {
        return arrStationTime;
    }

    public void setArrStationTime(LocalDateTime arrStationTime) {
        this.arrStationTime = arrStationTime;
    }

    public LocalDateTime getOutStationTime() {
        return outStationTime;
    }

    public void setOutStationTime(LocalDateTime outStationTime) {
        this.outStationTime = outStationTime;
    }

    public Integer getTimeStandardId() {
        return timeStandardId;
    }

    public void setTimeStandardId(Integer timeStandardId) {
        this.timeStandardId = timeStandardId;
    }

    public BigDecimal getStanceLong() {
        return stanceLong;
    }

    public void setStanceLong(BigDecimal stanceLong) {
        this.stanceLong = stanceLong;
    }

    public BigDecimal getStanceLat() {
        return stanceLat;
    }

    public void setStanceLat(BigDecimal stanceLat) {
        this.stanceLat = stanceLat;
    }

    public Integer getObsampMethodId() {
        return obsampMethodId;
    }

    public void setObsampMethodId(Integer obsampMethodId) {
        this.obsampMethodId = obsampMethodId;
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

    public String getCruiseStanceRemark() {
        return cruiseStanceRemark;
    }

    public void setCruiseStanceRemark(String cruiseStanceRemark) {
        this.cruiseStanceRemark = cruiseStanceRemark;
    }

    public Integer getIsDisplay() {
        return isDisplay;
    }

    public void setIsDisplay(Integer isDisplay) {
        this.isDisplay = isDisplay;
    }

    public String getImplProjects() {
        return implProjects;
    }

    public void setImplProjects(String implProjects) {
        this.implProjects = implProjects;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "RcBasicCruiseStanceInfo{" +
        "id=" + id +
        ", cruiseSegmentId=" + cruiseSegmentId +
        ", metadataId=" + metadataId +
        ", cruiseStanceName=" + cruiseStanceName +
        ", imagesInfoId=" + imagesInfoId +
        ", waterDept=" + waterDept +
        ", arrStationTime=" + arrStationTime +
        ", outStationTime=" + outStationTime +
        ", timeStandardId=" + timeStandardId +
        ", stanceLong=" + stanceLong +
        ", stanceLat=" + stanceLat +
        ", obsampMethodId=" + obsampMethodId +
        ", dataEditId=" + dataEditId +
        ", editTime=" + editTime +
        ", cruiseStanceRemark=" + cruiseStanceRemark +
        ", isDisplay=" + isDisplay +
        ", implProjects=" + implProjects +
        "}";
    }
}
