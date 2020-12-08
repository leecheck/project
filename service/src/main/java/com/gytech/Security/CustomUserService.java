package com.gytech.Security;

import com.gytech.Const;
import com.gytech.Security.entity.UserInfo;
import com.gytech.Security.ex.NotAdminAuthenticationException;
import com.gytech.Security.ex.UsernameNotFoundAuthenticationException;
import com.gytech.entity.admin.SysMenu;
import com.gytech.entity.admin.SysUser;
import com.gytech.service.ISysMenuService;
import com.gytech.service.ISysRoleService;
import com.gytech.service.ISysUserService;
import com.gytech.Security.ex.RepeatedAuthenticationException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by LQ on 2018/9/7.
 * Security
 */
@Component
public class CustomUserService implements UserDetailsService { //自定义UserDetailsService 接口

    @Autowired
    private ISysUserService userService;



    private Logger logger = LoggerFactory.getLogger(getClass());

    public UserDetails loadUserByUsername(String username) {
        logger.info("用户的用户名: {}", username);
        SysUser user = userService.findByUserName(username);
        UserInfo simpleUser = userService.sysUser2simpleUserinfo(user);
        if(user == null){
            throw new NotAdminAuthenticationException("用户: " + username + " 不存在!");
        }
        String[] needs = {Const.POWER_SUADMIN,Const.POWER_ADMIN};
        List<String> needslist = new ArrayList<String>(Arrays.asList(needs));
        List<String> powers = simpleUser.getPowers();
        needslist.retainAll(powers);
        if(needslist.size()==0){
            throw new NotAdminAuthenticationException("用户: " + username + "不具有登录权限!");
        }
        /*List<Object> principals = sessionRegistry.getAllPrincipals();
        for ( Object principal : principals) {
            if (user.getUsername().equals(((User) principal).getUsername())) {
                throw new RepeatedAuthenticationException("user has login！");
            }
        }*/
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (String permission : needslist) {
            if (StringUtils.isNotBlank(permission)) {
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission);
                //1：此处将权限信息添加到 GrantedAuthority 对象中，在后面进行全权限验证时会使用GrantedAuthority 对象。
                grantedAuthorities.add(grantedAuthority);
            }
        }
        return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }

}
