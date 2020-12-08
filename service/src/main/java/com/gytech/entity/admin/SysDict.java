package com.gytech.entity.admin;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 字典表
 * </p>
 *
 * @author LQ
 * @since 2018-09-19
 */
@TableName("sys_dict")
public class SysDict extends Model<SysDict> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 字典名称
     */
    @TableField("dict_name")
    private String dictName;
    /**
     * 字典代码
     */
    @TableField("dict_code")
    private String dictCode;
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
     * 父节点id
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     * 字典值
     */
    @TableField("dict_value")
    private String dictValue;

    /**
     * 排序
     */
    @TableField("sort")
    private int sort;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "SysDict{" +
        "id=" + id +
        ", dictName=" + dictName +
        ", dictCode=" + dictCode +
        ", createUserId=" + createUserId +
        ", updateUserId=" + updateUserId +
        ", parentId=" + parentId +
        ", dictValue=" + dictValue +
        ", sort=" + sort +
        "}";
    }
}
