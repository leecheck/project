package com.gytech.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gytech.Const;
import com.gytech.Configuration.EnvContext;
import com.gytech.LocalEntity.Res;
import com.gytech.LocalEntity.ResultInfo;
import com.gytech.Utils.GU;
import com.gytech.Utils.MD5Util;
import com.gytech.entity.admin.SysRole;
import com.gytech.entity.admin.SysRoleMenu;
import com.gytech.entity.admin.SysUser;
import com.gytech.entity.admin.SysUserRole;
import com.gytech.mapper.admin.SysRoleMapper;
import com.gytech.mapper.admin.SysRoleMenuMapper;
import com.gytech.mapper.admin.SysUserRoleMapper;
import com.gytech.service.ISysRoleMenuService;
import com.gytech.service.ISysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gytech.service.ISysUserRoleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author LQ
 * @since 2018-09-19
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Autowired
    private EnvContext envContext;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private ISysUserRoleService userRoleService;

    @Autowired
    private ISysRoleMenuService roleMenuService;
    @Override
    public IPage query(Integer curPage, Integer pageSize, Map paramMap) {
        IPage pageEntity =new Page<SysRole>(curPage,pageSize);
        return page(pageEntity);
    }

    @Override
    public Res add(SysRole sysRole) {
        Res res = new Res();
        SysUser currentUser = envContext.getCurrentUser();
        if (currentUser==null){
            return res.reason(ResultInfo.INFO_NOT_LOGIN);
        }
        sysRole.setCreateUserId(currentUser.getId());
        sysRole.setRoleType(StringUtils.defaultIfEmpty(sysRole.getRoleType(), Const.ROLE_USER));
        if (save(sysRole)){
            return res.success();
        }
        return res.reason(ResultInfo.INFO_SQL_RETRY);
    }

    @Transactional
    @Override
    public Res del(Long roleId) {
        Res res = new Res();
        SysRole sysRole = getById(roleId);
        if (sysRole == null){
            return res.data(false).reason("已不存在id为" + roleId + "的角色");
        }
        boolean flag = removeById(roleId);
        QueryWrapper<SysUserRole> userrole = new QueryWrapper<>();
        userrole.eq("role_id",roleId);
        userRoleService.remove(userrole);
        QueryWrapper<SysRoleMenu> rolemenu = new QueryWrapper<>();
        rolemenu.eq("role_id",roleId);
        roleMenuService.remove(rolemenu);
        if (flag){
            return res.data(true).success();
        }
        return res.data(false).reason(ResultInfo.INFO_SQL_RETRY);
    }

    @Override
    public Res queryByUserId(Long userId) {
        Res res = new Res();
        QueryWrapper<SysUserRole> ew = new QueryWrapper<>();
        ew.eq("user_id",userId);
        return res.success().data(sysUserRoleMapper.selectList(ew));
    }

    @Override
    public List<SysRole> queryRolesByUserId(Long userId) {
        List<SysRole> roles = new ArrayList<>();
        QueryWrapper<SysUserRole> ew = new QueryWrapper<>();
        ew.eq("user_id",userId);
        List<SysUserRole> relation = sysUserRoleMapper.selectList(ew);
        if (relation.size()==0){
            return roles;
        }
        List<Long> ids = new ArrayList<>();
        for (SysUserRole userRole:relation){
            ids.add(userRole.getRoleId());
        }
        QueryWrapper<SysRole> ewRole = new QueryWrapper<>();
        ewRole.in("id",ids);
        roles = sysRoleMapper.selectList(ewRole);
        return roles;
    }

    @Transactional
    @Override
    public Res menuUpdate(Long roldId, List<Long> menuIds) {
        Res res = new Res();
        QueryWrapper<SysRoleMenu> ew = new QueryWrapper<>();
        ew.eq("role_id",roldId);
        sysRoleMenuMapper.delete(ew);
        for (Long menu:menuIds){
            SysRoleMenu sysRoleMenu = new SysRoleMenu();
            sysRoleMenu.setMenuId(menu);
            sysRoleMenu.setRoleId(roldId);
            sysRoleMenuMapper.insert(sysRoleMenu);
        }
        return res.success();
    }

    @Override
    public Boolean roleCodeDump(String roleCode,Long roleId) {
        QueryWrapper<SysRole> entity = new QueryWrapper<SysRole>();
        if (roleId == null){
            entity.eq("role_code",roleCode);
            List<SysRole> roles = baseMapper.selectList(entity);
            if (roles.size()>0){
                return true;
            }
        }
        entity.eq("role_code",roleCode);
        entity.ne("id",roleId);
        List<SysRole> roles = baseMapper.selectList(entity);
        if (roles.size()>0){
            return true;
        }
        return false;
    }

    public boolean roleNameDump(String roleName,Long roleId) {
        QueryWrapper<SysRole> entity = new QueryWrapper<SysRole>();
        if (roleId == null){
            entity.eq("role_name",roleName);
            List<SysRole> roles = baseMapper.selectList(entity);
            if (roles.size()>0){
                return true;
            }
        }
        entity.eq("role_name",roleName);
        entity.ne("id",roleId);
        List<SysRole> roles = baseMapper.selectList(entity);
        if (roles.size()>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean isAdmin(SysUser user) {
        if (user==null){
            return false;
        }
        List<SysRole> roles = queryRolesByUserId(user.getId());
        for (SysRole role:roles){
            if (role.getRoleCode().equalsIgnoreCase("ROLE_ADMIN")){
                return true;
            }
        }
        return false;
    }
}
