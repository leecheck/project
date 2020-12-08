package com.gytech.LocalEntity;

import com.gytech.entity.admin.*;

import java.util.List;

/**
 * Created by LQ on 2018/11/26.
 * com.gytech.LocalEntity
 */
public class UserInfo {

    private boolean authenticated = true;

    private SysUser user;

    private SysOrganization organization;

    private SysArea area;

    private List<SysRole> roles;

    private List<SysMenu> menus;

    private Object mapConfig;

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }

    public SysOrganization getOrganization() {
        return organization;
    }

    public void setOrganization(SysOrganization organization) {
        this.organization = organization;
    }

    public SysArea getArea() {
        return area;
    }

    public void setArea(SysArea area) {
        this.area = area;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

    public List<SysMenu> getMenus() {
        return menus;
    }

    public void setMenus(List<SysMenu> menus) {
        this.menus = menus;
    }

    public Object getMapConfig() {
        return mapConfig;
    }

    public void setMapConfig(Object mapConfig) {
        this.mapConfig = mapConfig;
    }
}
