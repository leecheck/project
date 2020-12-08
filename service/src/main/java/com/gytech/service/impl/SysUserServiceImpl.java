package com.gytech.service.impl;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

import com.gytech.Configuration.token.JwtUtil;
import com.gytech.Const;
import com.gytech.LocalEntity.BTree;
import com.gytech.Security.entity.UserInfo;
import com.gytech.Security.ex.NotAdminAuthenticationException;
import com.gytech.entity.admin.*;
import com.gytech.service.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gytech.LocalEntity.Res;
import com.gytech.LocalEntity.ResultInfo;
import com.gytech.Utils.GU;
import com.gytech.Utils.MD5Util;
import com.gytech.mapper.admin.SysUserMapper;
import com.gytech.mapper.admin.SysUserRoleMapper;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author LQ
 * @since 2018-09-19
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysUserRoleMapper userRoleMapper;

    @Autowired
    private ISysUserRoleService userRoleService;

    @Autowired
    private ISysOrganizationService organizationService;

    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private ISysMenuService menuService;

    @Override
    public SysUser findByUserName(String username) {
        QueryWrapper<SysUser> ew = new QueryWrapper<>();
        ew.eq("username",username);
        SysUser sysUsers = sysUserMapper.selectOne(ew);
        return sysUsers;
    }

    @Override
    public SysUser getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication==null){
            return null;
        }
        if (authentication instanceof AnonymousAuthenticationToken){
            return null;
        }
        UserDetails customUserDetail = (UserDetails) authentication.getPrincipal();
        return findByUserName(customUserDetail.getUsername());
    }

    @Override
    public UserInfo simpleCurrentUser() {
        SysUser user = getCurrentUser();
        return sysUser2simpleUserinfo(user);
    }

    @Override
    public UserInfo sysUser2simpleUserinfo(SysUser user){
        if (user == null){
            return null;
        }
        UserInfo simpleUser = new UserInfo();
        Long orgId = null,areaId = null;
        simpleUser.setUserId(user.getId());
        simpleUser.setAlasName(user.getDisplayName());
        simpleUser.setUserName(user.getUsername());
        if (user.getOrganId() != null){
            orgId = user.getOrganId();
        }
        SysOrganization organization = null;
        if (orgId != null){
            organization = organizationService.getById(orgId);
            simpleUser.setOrgName(organization.getOrganName());
            simpleUser.setOrgId(organization.getId());
        }
        List<SysRole> roles = roleService.queryRolesByUserId(user.getId());
        List<String> roleCodes=roles.stream().map(SysRole::getRoleCode).collect(Collectors.toList());
        simpleUser.setRoles(roleCodes);
        List<SysMenu> menus = menuService.findByUserId(user.getId());
        List<String> powerCodes=menus.stream().map(SysMenu::getMenuCode).collect(Collectors.toList());
        simpleUser.setPowers(powerCodes);
        return simpleUser;
    }

    @Override
    public boolean authenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication==null){
            return false;
        }
        if (authentication instanceof AnonymousAuthenticationToken){
            return false;
        }
        return true;
    }

    @Transactional
    @Override
    public Res del(Long userId) {
        Res res = new Res();
        SysUser sysUser = getById(userId);
        if (sysUser == null){
            return res.data(false).reason("已不存在id为" + userId + "的用户");
        }
        boolean flag = removeById(userId);
        QueryWrapper<SysUserRole> ew = new QueryWrapper<>();
        ew.eq("user_id",userId);
        userRoleService.remove(ew);
        if (flag){
            return res.data(true).success();
        }
        return res.data(false).reason(ResultInfo.INFO_SQL_RETRY);
    }

    @Override
    public Res addUser(SysUser sysUser) {
        Res res = new Res();
        SysUser currentUser = getCurrentUser();
        if (currentUser==null){
            return res.reason(ResultInfo.INFO_NOT_LOGIN);
        }
        sysUser.setPassword(MD5Util.encode(sysUser.getPassword()));
        Date now = new Date();
        sysUser.setCreateTime(now);
        sysUser.setUpdateTime(now);
        sysUser.setCreateUserId(currentUser.getId());
        if (save(sysUser)){
            return res.success();
        }
        return res.reason(ResultInfo.INFO_SQL_RETRY);
    }

    @Override
    public Res editPass(Long userId, String password) {
        Res res = new Res();
        SysUser sysUser = getById(userId);
        if (sysUser==null){
            return res.reason(ResultInfo.INFO_NOT_FIND);
        }
        String pass = MD5Util.encode(password);
        sysUser.setPassword(pass);
        if (updateById(sysUser)){
            return res.success();
        }
        return res.reason(ResultInfo.INFO_SQL_RETRY);
    }

    @Override
    public Page query(Integer curPage,Integer pageSize,Map paramMap) {
        List<BTree> childOrg = organizationService.userOrgList();
        Long userOrg = simpleCurrentUser().getOrgId();
        BTree<SysOrganization> orgTree = (BTree<SysOrganization>) organizationService.orgTree().getData();
        List<BTree> child = orgTree.listTree();
        List<String> orgs = new ArrayList<>();
        orgs.add(String.valueOf(userOrg));
        for (BTree<SysOrganization> org:child){
            orgs.add(org.getId());
        }
        return queryPage(curPage,pageSize,paramMap,orgs);
    }

    @Override
    public Page queryNow(Integer curPage, Integer pageSize, Map paramMap) {
        Page pageEntity = new Page<SysUser>(curPage,pageSize);
        String username = GU.getMapKeyString("username",paramMap);
        String displayName = GU.getMapKeyString("displayName",paramMap);
        String orgCode = GU.getMapKeyString("organId",paramMap);
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(username)){
            wrapper.eq("user_name",username);
        }
        if(StringUtils.isNotBlank(displayName)){
            wrapper.eq("display_name",displayName);
            paramMap.put("display_name",displayName);
        }
        if (StringUtils.isNotBlank(orgCode)){
            wrapper.eq("organ_id",orgCode);
        }
        pageEntity.setRecords(sysUserMapper.selectList(wrapper));
        return pageEntity;
    }

    private Page queryPage(Integer curPage,Integer pageSize,Map paramMap,List<String> orgs) {
        Page pageEntity = new Page<SysUser>(curPage,pageSize);
        String username = GU.getMapKeyString("username",paramMap);
        String orgCode = GU.getMapKeyString("organId",paramMap);
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(username)){
            wrapper.like("username",username).or().like("display_name",username);
        }
        if (orgs.size()>0){
            wrapper.in("organ_id",orgs);
        }
        if (StringUtils.isNotBlank(orgCode)){
            wrapper.eq("organ_id",orgCode);
        }
        pageEntity.setRecords(sysUserMapper.selectList(wrapper));
        return pageEntity;
    }

    @Transactional
    @Override
    public Res roleUpdate(Long userId, List<Long> roleIds) {
        Res res = new Res();
        QueryWrapper<SysUserRole> ew = new QueryWrapper<>();
        ew.eq("user_id",userId);
        userRoleMapper.delete(ew);
        for (Long roleId:roleIds){
            SysUserRole userRole = new SysUserRole();
            userRole.setRoleId(roleId);
            userRole.setUserId(userId);
            userRoleMapper.insert(userRole);
        }
        return res.success();
    }

    @Override
    public Boolean usernameDump(String username,Long userId) {
        QueryWrapper<SysUser> entity = new QueryWrapper<SysUser>();
        if (userId == null){
            entity.eq("username",username);
            List<SysUser> users = baseMapper.selectList(entity);
            if (users.size()>0){
                return true;
            }
        }
        entity.eq("username",username);
        entity.ne("id",userId);
        List<SysUser> users = baseMapper.selectList(entity);
        if (users.size()>0){
            return true;
        }
        return false;
    }

    @Override
    public SysUser findByRawUserInfo(String username, String password) {
        Res res=new Res();
        QueryWrapper<SysUser> entity = new QueryWrapper<SysUser>();
        String pass = MD5Util.encode(password);
        entity.eq("username",username).eq("password",pass);
        List<SysUser> sysUsers=baseMapper.selectList(entity);
        if(sysUsers.size()>0){
            return sysUsers.get(0);
        }
        return null;
    }

    @Override
    public Res restLogin(String userName, String password) {
        Res res=new Res();
        Map result = new HashMap();
        SysUser user = findByUserName(userName);
        if(user == null){
            return res.reason("用户: " + userName + " 不存在!");
        }
        if(!user.getPassword().equals(MD5Util.encode(password))){
            return res.reason("用户密码不匹配!");
        }
        UserInfo simpleUser = sysUser2simpleUserinfo(user);
        String token = JwtUtil.createToken(simpleUser);
        result.put("token",token);
        result.put("user",simpleUser);
        return res.success().data(result);
    }

    @Override
    public Res refreshToken(String userName, String password) {
        Res res=new Res();
        SysUser user = findByUserName(userName);
        if(user == null){
            return res.reason("用户: " + userName + " 已过期，请重新登录!");
        }
        if(!user.getPassword().equals(MD5Util.encode(password))){
            return res.reason("用户信息过期，请重新登录!");
        }
        UserInfo simpleUser = sysUser2simpleUserinfo(user);
        String token = JwtUtil.createToken(simpleUser);
        return res.success().data(token);
    }

    private Boolean compareAuth(UserInfo userInfo,String auth){
        List<String> powers = userInfo.getPowers();
        List<String> authList = new ArrayList<>();
        authList.add(auth);
        authList.retainAll(powers);
        if(authList.size()>0){
            return true;
        }
        return false;
    }

    @Override
    public Boolean isSA(UserInfo userInfo) {
        return compareAuth(userInfo,Const.POWER_SUADMIN);
    }

    @Override
    public Boolean isEditAuth(UserInfo userInfo) {
        return compareAuth(userInfo,Const.POWER_TASKEDIT);
    }


}
