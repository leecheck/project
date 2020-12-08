package com.gytech.entity.work;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * CTD数据表
 * </p>
 *
 * @author LQ
 * @since 2020-11-12
 */
@TableName("data_ctd")
public class DataCtd extends Model<DataCtd> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 航段id
     */
    private Integer cruiseSegmentId;

    /**
     * 基础站位name
     */
    private String cruiseStanceName;

    /**
     * 观测标识
     */
    private String obsFlag;

    /**
     * 观测层深度
     */
    private BigDecimal obsDepth;

    /**
     * 压力
     */
    private BigDecimal pressure;

    /**
     * 水温
     */
    private BigDecimal temperature;

    /**
     * 电导率
     */
    private BigDecimal conductivity;

    /**
     * 盐度
     */
    private BigDecimal salinity;

    /**
     * 浊度
     */
    private BigDecimal turbidity;

    /**
     * 声速
     */
    private BigDecimal soundVelocity;

    /**
     * 备注
     */
    private String remark;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getObsFlag() {
        return obsFlag;
    }

    public void setObsFlag(String obsFlag) {
        this.obsFlag = obsFlag;
    }

    public BigDecimal getObsDepth() {
        return obsDepth;
    }

    public void setObsDepth(BigDecimal obsDepth) {
        this.obsDepth = obsDepth;
    }

    public BigDecimal getPressure() {
        return pressure;
    }

    public void setPressure(BigDecimal pressure) {
        this.pressure = pressure;
    }

    public BigDecimal getTemperature() {
        return temperature;
    }

    public void setTemperature(BigDecimal temperature) {
        this.temperature = temperature;
    }

    public BigDecimal getConductivity() {
        return conductivity;
    }

    public void setConductivity(BigDecimal conductivity) {
        this.conductivity = conductivity;
    }

    public BigDecimal getSalinity() {
        return salinity;
    }

    public void setSalinity(BigDecimal salinity) {
        this.salinity = salinity;
    }

    public BigDecimal getTurbidity() {
        return turbidity;
    }

    public void setTurbidity(BigDecimal turbidity) {
        this.turbidity = turbidity;
    }

    public BigDecimal getSoundVelocity() {
        return soundVelocity;
    }

    public void setSoundVelocity(BigDecimal soundVelocity) {
        this.soundVelocity = soundVelocity;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getCruiseSegmentId() {
        return cruiseSegmentId;
    }

    public void setCruiseSegmentId(Integer cruiseSegmentId) {
        this.cruiseSegmentId = cruiseSegmentId;
    }

    public String getCruiseStanceName() {
        return cruiseStanceName;
    }

    public void setCruiseStanceName(String cruiseStanceName) {
        this.cruiseStanceName = cruiseStanceName;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "DataCtd{" +
        "id=" + id +
        ", obsFlag=" + obsFlag +
        ", obsDepth=" + obsDepth +
        ", pressure=" + pressure +
        ", temperature=" + temperature +
        ", conductivity=" + conductivity +
        ", salinity=" + salinity +
        ", turbidity=" + turbidity +
        ", soundVelocity=" + soundVelocity +
        ", remark=" + remark +
        "}";
    }
}
