package com.gytech.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gytech.LocalEntity.Res;
import com.gytech.entity.admin.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gytech.entity.admin.SysUser;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author LQ
 * @since 2018-09-19
 */
public interface ISysRoleService extends IService<SysRole> {

    IPage query(Integer curPage, Integer pageSize, Map paramMap);

    Res add(SysRole sysRole);

    Res del(Long id);

    Res queryByUserId(Long userId);

    List<SysRole> queryRolesByUserId(Long userId);

    Res menuUpdate(Long rodeId, List<Long> menuIds);

    Boolean roleCodeDump(String roleCode,Long roleId);//验证rolecode重名

    boolean roleNameDump(String roleCode,Long roleId);//验证rolename重名

    boolean isAdmin(SysUser user);
}
