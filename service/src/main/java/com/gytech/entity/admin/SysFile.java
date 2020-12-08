package com.gytech.entity.admin;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author LQ
 * @since 2018-09-19
 */
@TableName("sys_file")
public class SysFile extends Model<SysFile> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 文件名
     */
    private String name;
    /**
     * 文件大小，字节
     */
    private Long size;
    /**
     * 文件类型
     */
    private String type;
    /**
     * 文件路径
     */
    private String path;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 创建人
     */
    @TableField("create_user_id")
    private String createUserId;
    /**
     * 创建时间
     */
    @TableField("create_at")
    private Date createAt;
    /**
     * 0正常1删除
     */
    @TableField("is_delete")
    private Integer isDelete;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SysFile{" +
        "id=" + id +
        ", name=" + name +
        ", size=" + size +
        ", type=" + type +
        ", path=" + path +
        ", remarks=" + remarks +
        ", createUserId=" + createUserId +
        ", createAt=" + createAt +
        ", isDelete=" + isDelete +
        "}";
    }
}
