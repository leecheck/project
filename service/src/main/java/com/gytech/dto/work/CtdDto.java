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

/**
 * <p>
 * CTD数据表
 * </p>
 *
 * @author LQ
 * @since 2020-11-12
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CtdDto {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 站位ID
     */
    private Integer cruiseStanceId;

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


}
