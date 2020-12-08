package com.gytech.dto.work;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * <p>
 * 海洋气象数据
 * 海洋气象站位+气象观测数据
 * </p>
 *
 * @author LQ
 * @since 2020-11-12
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MeteorologyDto {

    private static final long serialVersionUID=1L;

    //站位id
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
     * 是否显示
     */
    private Integer isDisplay;

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
}
