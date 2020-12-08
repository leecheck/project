package com.gytech.LocalEntity;

import com.gytech.entity.admin.SysUser;

import java.util.List;

/**
 * Created by LQ on 2018/11/29.
 * com.gytech.LocalEntity
 */
public class UserDetail {

    private boolean isAdmin = true;

    private SysUser user;

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }
}
