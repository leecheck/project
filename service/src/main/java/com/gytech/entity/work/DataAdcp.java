package com.gytech.entity.work;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * ADCP数据表
 * </p>
 *
 * @author LQ
 * @since 2020-11-12
 */
@TableName("data_adcp")
public class DataAdcp extends Model<DataAdcp> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 站位ID(绑定metadata_id)
     */
    private Integer adcpStationId;

    /**
     * 观测层深度
     */
    private BigDecimal obsDepth;

    /**
     * 水平流速
     */
    private BigDecimal horizontalVelocity;

    /**
     * 水平流向
     */
    private BigDecimal horizontalDirection;

    /**
     * 垂直流速
     */
    private BigDecimal verticalVelocity;

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

    public Integer getAdcpStationId() {
        return adcpStationId;
    }

    public void setAdcpStationId(Integer adcpStationId) {
        this.adcpStationId = adcpStationId;
    }

    public BigDecimal getObsDepth() {
        return obsDepth;
    }

    public void setObsDepth(BigDecimal obsDepth) {
        this.obsDepth = obsDepth;
    }

    public BigDecimal getHorizontalVelocity() {
        return horizontalVelocity;
    }

    public void setHorizontalVelocity(BigDecimal horizontalVelocity) {
        this.horizontalVelocity = horizontalVelocity;
    }

    public BigDecimal getHorizontalDirection() {
        return horizontalDirection;
    }

    public void setHorizontalDirection(BigDecimal horizontalDirection) {
        this.horizontalDirection = horizontalDirection;
    }

    public BigDecimal getVerticalVelocity() {
        return verticalVelocity;
    }

    public void setVerticalVelocity(BigDecimal verticalVelocity) {
        this.verticalVelocity = verticalVelocity;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "DataAdcp{" +
        "id=" + id +
        ", adcpStationId=" + adcpStationId +
        ", obsDepth=" + obsDepth +
        ", horizontalVelocity=" + horizontalVelocity +
        ", horizontalDirection=" + horizontalDirection +
        ", verticalVelocity=" + verticalVelocity +
        ", remark=" + remark +
        "}";
    }
}
