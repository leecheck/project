package com.gytech.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gytech.LocalEntity.Res;
import com.gytech.Security.entity.UserInfo;
import com.gytech.entity.admin.SysMenu;
import com.gytech.entity.admin.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author LQ
 * @since 2018-09-19
 */
public interface ISysUserService extends IService<SysUser> {

    SysUser findByUserName(String username);

    SysUser getCurrentUser();

    UserInfo simpleCurrentUser();

    UserInfo sysUser2simpleUserinfo(SysUser user);

    boolean authenticated();

    Res del(Long userId);

    Res addUser(SysUser user);

    Res editPass(Long userId,String password);

    Page query(Integer curPage,Integer pageSize,Map paramMap);

    Page queryNow(Integer curPage,Integer pageSize,Map paramMap);

    Res roleUpdate(Long userId, List<Long> roleIds);

    Boolean usernameDump(String username,Long userId);//验证 username 重复

    SysUser findByRawUserInfo(String username,String rawpassword);

    Res restLogin(String userName,String Password);

    Res refreshToken(String userName,String password);

    Boolean isSA(UserInfo userInfo);

    Boolean isEditAuth(UserInfo userInfo);

}
