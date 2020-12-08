package com.gytech.entity.admin;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * <p>
 * 行政区划
 * </p>
 *
 * @author LQ
 * @since 2018-09-19
 */
@TableName("sys_area")
public class SysArea extends Model<SysArea> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 区域代码
     */
    @TableField("area_code")
    private String areaCode;
    /**
     * 区域名称
     */
    @TableField("area_name")
    private String areaName;
    /**
     * 区域类型
     */
    @TableField("area_type")
    private String areaType;
    /**
     * 排序字段
     */
    private Long sort;
    /**
     * 父节点id
     */
    @TableField("parent_id")
    private Long parentId;
    /**
     * 创建人
     */
    @TableField("create_user_id")
    private Long createUserId;
    /**
     * 修改人
     */
    @TableField("update_user_id")
    private Long updateUserId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaType() {
        return areaType;
    }

    public void setAreaType(String areaType) {
        this.areaType = areaType;
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SysArea{" +
        "id=" + id +
        ", areaCode=" + areaCode +
        ", areaName=" + areaName +
        ", areaType=" + areaType +
        ", sort=" + sort +
        ", parentId=" + parentId +
        ", createUserId=" + createUserId +
        ", updateUserId=" + updateUserId +
        "}";
    }
}
