package com.gytech.entity.work;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 海洋气象数据表
 * </p>
 *
 * @author LQ
 * @since 2020-11-12
 */
@TableName("data_meteorology")
public class DataMeteorology extends Model<DataMeteorology> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 海洋气象站位ID
     */
    private Integer meteorologyStationId;

    /**
     * 观测高度
     */
    private BigDecimal obsHeight;

    /**
     * 气压
     */
    private BigDecimal airPressure;

    /**
     * 气温
     */
    private BigDecimal airTemperature;

    /**
     * 风速
     */
    private BigDecimal windVelocity;

    /**
     * 风向
     */
    private BigDecimal windDirection;

    /**
     * 相对湿度
     */
    private BigDecimal humidity;

    /**
     * 降水
     */
    private BigDecimal rainfall;

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

    public Integer getMeteorologyStationId() {
        return meteorologyStationId;
    }

    public void setMeteorologyStationId(Integer meteorologyStationId) {
        this.meteorologyStationId = meteorologyStationId;
    }

    public BigDecimal getObsHeight() {
        return obsHeight;
    }

    public void setObsHeight(BigDecimal obsHeight) {
        this.obsHeight = obsHeight;
    }

    public BigDecimal getAirPressure() {
        return airPressure;
    }

    public void setAirPressure(BigDecimal airPressure) {
        this.airPressure = airPressure;
    }

    public BigDecimal getAirTemperature() {
        return airTemperature;
    }

    public void setAirTemperature(BigDecimal airTemperature) {
        this.airTemperature = airTemperature;
    }

    public BigDecimal getWindVelocity() {
        return windVelocity;
    }

    public void setWindVelocity(BigDecimal windVelocity) {
        this.windVelocity = windVelocity;
    }

    public BigDecimal getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(BigDecimal windDirection) {
        this.windDirection = windDirection;
    }

    public BigDecimal getHumidity() {
        return humidity;
    }

    public void setHumidity(BigDecimal humidity) {
        this.humidity = humidity;
    }

    public BigDecimal getRainfall() {
        return rainfall;
    }

    public void setRainfall(BigDecimal rainfall) {
        this.rainfall = rainfall;
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
        return "DataMeteorology{" +
        "id=" + id +
        ", meteorologyStationId=" + meteorologyStationId +
        ", obsHeight=" + obsHeight +
        ", airPressure=" + airPressure +
        ", airTemperature=" + airTemperature +
        ", windVelocity=" + windVelocity +
        ", windDirection=" + windDirection +
        ", humidity=" + humidity +
        ", rainfall=" + rainfall +
        ", remark=" + remark +
        "}";
    }
}
