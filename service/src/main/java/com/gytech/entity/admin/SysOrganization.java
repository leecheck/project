package com.gytech.entity.admin;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 行政区划
 * </p>
 *
 * @author LQ
 * @since 2018-09-19
 */
@TableName("sys_organization")
public class SysOrganization extends Model<SysOrganization> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 单位代码
     */
    @TableField("organ_code")
    private String organCode;
    /**
     * 单位名称
     */
    @TableField("organ_name")
    private String organName;
    /**
     * 所属区域id
     */
    @TableField("area_id")
    private Long areaId;
    /**
     * 所属区域代码
     */
    @TableField("area_code")
    private String areaCode;
    /**
     * 所属区域名称
     */
    @TableField("area_name")
    private String areaName;
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
    /**
     * 备注
     */
    private String remark;
    /**
     * 是否删除
     */
    private Integer del;
    /**
     * 单位类型
     */
    private String otype;
    /**
     * 经度
     */
    private BigDecimal lon;
    /**
     * 纬度
     */
    private BigDecimal lat;
    /**
     * 启用时间
     */
    @TableField("start_time")
    private String startTime;
    /**
     * 停用时间
     */
    @TableField("end_time")
    private String endTime;
    /**
     * 地址
     */
    private String address;
    /**
     * 邮编
     */
    private String postcode;
    private String email;
    private String fax;
    private String tel;
    /**
     * 单位简介
     */
    @TableField("brief_org")
    private String briefOrg;
    /**
     * 职责
     */
    @TableField("duty_org")
    private String dutyOrg;
    /**
     * 人员职责
     */
    @TableField("brief_user")
    private String briefUser;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrganCode() {
        return organCode;
    }

    public void setOrganCode(String organCode) {
        this.organCode = organCode;
    }

    public String getOrganName() {
        return organName;
    }

    public void setOrganName(String organName) {
        this.organName = organName;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }

    public String getOtype() {
        return otype;
    }

    public void setOtype(String otype) {
        this.otype = otype;
    }

    public BigDecimal getLon() {
        return lon;
    }

    public void setLon(BigDecimal lon) {
        this.lon = lon;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getBriefOrg() {
        return briefOrg;
    }

    public void setBriefOrg(String briefOrg) {
        this.briefOrg = briefOrg;
    }

    public String getDutyOrg() {
        return dutyOrg;
    }

    public void setDutyOrg(String dutyOrg) {
        this.dutyOrg = dutyOrg;
    }

    public String getBriefUser() {
        return briefUser;
    }

    public void setBriefUser(String briefUser) {
        this.briefUser = briefUser;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SysOrganization{" +
        "id=" + id +
        ", organCode=" + organCode +
        ", organName=" + organName +
        ", areaId=" + areaId +
        ", areaCode=" + areaCode +
        ", areaName=" + areaName +
        ", sort=" + sort +
        ", parentId=" + parentId +
        ", createUserId=" + createUserId +
        ", updateUserId=" + updateUserId +
        "}";
    }
}
