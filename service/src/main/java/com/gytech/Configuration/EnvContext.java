package com.gytech.Configuration;

import com.gytech.LocalEntity.UserDetail;
import com.gytech.Security.entity.UserInfo;
import com.gytech.entity.admin.SysUser;
import com.gytech.service.ISysRoleService;
import com.gytech.service.ISysUserService;
import com.gytech.service.impl.SysUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by LQ on 2018/9/14.
 * com.gytech.Configuration
 */
public class EnvContext {

    public EnvContext(Date data){
        this.sysStartDate = data;
    }

    private Date sysStartDate;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    ISysRoleService sysRoleService;

    /**
     * 当前用户entity
     * @return
     */
    public SysUser getCurrentUser(){
        return sysUserService.getCurrentUser();
    }

    /**
     * 最简用户信息
     * @return
     */
    public UserInfo getUserInfo(){
        return sysUserService.simpleCurrentUser();
    }

    public boolean isAdmin(){
        return sysRoleService.isAdmin(getCurrentUser());
    }

    public Date getSysStartData() {
        return sysStartDate;
    }

    public boolean authenticated(){
        return sysUserService.authenticated();
    };
}
