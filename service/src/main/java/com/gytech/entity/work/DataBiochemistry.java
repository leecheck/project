package com.gytech.entity.work;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 海洋生物化学数据表
 * </p>
 *
 * @author LQ
 * @since 2020-11-12
 */
@TableName("data_biochemistry")
public class DataBiochemistry extends Model<DataBiochemistry> {

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
     * 观测层深度
     */
    private BigDecimal obsDepth;

    /**
     * 水温
     */
    private BigDecimal temperature;

    /**
     * 盐度
     */
    private BigDecimal salinity;

    /**
     * 溶解氧
     */
    private BigDecimal dissolvedOxygen;

    /**
     * pH
     */
    private BigDecimal ph;

    /**
     * NH4-N氨氮
     */
    private BigDecimal nh4N;

    /**
     * NO3-N硝酸盐
     */
    private BigDecimal no3N;

    /**
     * SiO4硅酸盐
     */
    private BigDecimal sio4;

    /**
     * NO2-N亚硝酸盐
     */
    private BigDecimal no2N;

    /**
     * PO4活性磷酸盐
     */
    private BigDecimal po4;

    /**
     * 叶绿素a
     */
    private BigDecimal chlorophylA;

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


    public BigDecimal getObsDepth() {
        return obsDepth;
    }

    public void setObsDepth(BigDecimal obsDepth) {
        this.obsDepth = obsDepth;
    }

    public BigDecimal getTemperature() {
        return temperature;
    }

    public void setTemperature(BigDecimal temperature) {
        this.temperature = temperature;
    }

    public BigDecimal getSalinity() {
        return salinity;
    }

    public void setSalinity(BigDecimal salinity) {
        this.salinity = salinity;
    }

    public BigDecimal getDissolvedOxygen() {
        return dissolvedOxygen;
    }

    public void setDissolvedOxygen(BigDecimal dissolvedOxygen) {
        this.dissolvedOxygen = dissolvedOxygen;
    }

    public BigDecimal getPh() {
        return ph;
    }

    public void setPh(BigDecimal ph) {
        this.ph = ph;
    }



    public BigDecimal getSio4() {
        return sio4;
    }

    public void setSio4(BigDecimal sio4) {
        this.sio4 = sio4;
    }



    public BigDecimal getPo4() {
        return po4;
    }

    public BigDecimal getNh4N() {
        return nh4N;
    }

    public void setNh4N(BigDecimal nh4N) {
        this.nh4N = nh4N;
    }

    public BigDecimal getNo3N() {
        return no3N;
    }

    public void setNo3N(BigDecimal no3N) {
        this.no3N = no3N;
    }

    public BigDecimal getNo2N() {
        return no2N;
    }

    public void setNo2N(BigDecimal no2N) {
        this.no2N = no2N;
    }

    public void setPo4(BigDecimal po4) {
        this.po4 = po4;
    }

    public BigDecimal getChlorophylA() {
        return chlorophylA;
    }

    public void setChlorophylA(BigDecimal chlorophylA) {
        this.chlorophylA = chlorophylA;
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
        return "DataBiochemistry{" +
        "id=" + id +
        ", obsDepth=" + obsDepth +
        ", temperature=" + temperature +
        ", salinity=" + salinity +
        ", dissolvedOxygen=" + dissolvedOxygen +
        ", ph=" + ph +
        ", po4=" + po4 +
        ", chlorophylA=" + chlorophylA +
        ", remark=" + remark +
        "}";
    }
}
