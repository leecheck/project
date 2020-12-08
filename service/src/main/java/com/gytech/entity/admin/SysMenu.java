package com.gytech.entity.admin;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableName;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;

/**
 * <p>
 * 菜单
 * </p>
 *
 * @author LQ
 * @since 2018-09-19
 */
@TableName("sys_menu")
public class SysMenu extends Model<SysMenu> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 菜单地址
     */
    @TableField("menu_url")
    private String menuUrl;
    /**
     * 前端路由
     */
    @TableField("menu_route")
    private String menuRoute;
    /**
     * 菜单名称
     */
    @TableField("menu_name")
    private String menuName;
    /**
     * 菜单图标
     */
    @TableField("menu_icon")
    private String menuIcon;
    /**
     * 菜单类型
     */
    @TableField("menu_type")
    private String menuType;
    /**
     * 权限、菜单 代码
     */
    @TableField("model_name")
    private String modelName;

    /**
     * 所属模块名称
     */
    @TableField("menu_code")
    private String menuCode;
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

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public String getMenuRoute() {
        return menuRoute;
    }

    public void setMenuRoute(String menuRoute) {
        this.menuRoute = menuRoute;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
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

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SysMenu{" +
        "id=" + id +
        ", menuUrl=" + menuUrl +
        ", menuRoute=" + menuRoute +
        ", menuName=" + menuName +
        ", menuIcon=" + menuIcon +
        ", menuType=" + menuType +
        ", modelName=" + modelName +
        ", sort=" + sort +
        ", parentId=" + parentId +
        ", createUserId=" + createUserId +
        ", updateUserId=" + updateUserId +
        "}";
    }
}
