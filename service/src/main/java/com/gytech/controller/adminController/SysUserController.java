package com.gytech.controller.adminController;

import com.alibaba.fastjson.JSON;
import com.gytech.Base.BaseController;
import com.gytech.Configuration.EnvContext;
import com.gytech.Configuration.token.PassToken;
import com.gytech.Const;
import com.gytech.LocalEntity.ResultInfo;
import com.gytech.LocalEntity.UserInfo;
import com.gytech.Security.annotation.Power;
import com.gytech.Utils.GU;
import com.gytech.Utils.MD5Util;
import com.gytech.entity.admin.*;
import com.gytech.service.*;
import com.gytech.LocalEntity.Res;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LQ on 2018/9/12.
 * controller.adminController
 */
@Api(tags = "用户管理")
@Controller
@RequestMapping(value = "/admin/sysUser")
public class SysUserController extends BaseController {

    @Autowired
    private EnvContext emv;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ISysLogService iSysLogService;

    @Autowired
    private ISysOrganizationService sysOrganizationService;

    @Autowired
    private ISysAreaService sysAreaService;

    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private ISysMaplayerService maplayerService;

    @RequestMapping(value = "/list",method = {RequestMethod.GET})
    public Object list(Model model){
        return "sysadmin/sysUser";
    }

    @RequestMapping(value = "/query",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Object query(
            @RequestParam(defaultValue = "{}") String param,
            @RequestParam(defaultValue = "1") Integer curPage,
            @RequestParam(defaultValue = "10") Integer pageSize
    ){
        Res res = new Res();
        try {
            return res.success().data(sysUserService.query(curPage,pageSize,parseParams(param)));
        }catch (Exception e) {
            e.printStackTrace();
            return res.reason(ResultInfo.INFO_PARAM_MAP_ERROR);
        }
    }

    @Power(value = Const.POWER_SUADMIN)
    @RequestMapping(value = "/add",method = {RequestMethod.POST})
    @ResponseBody
    public Object add(SysUser user){
        Res res = new Res();
        try {
            SysLog sysLog=GU.addLog(sysUserService.getCurrentUser().getId(),"1",user.toString());
            iSysLogService.addLog(sysLog);
            return sysUserService.addUser(user);
        }catch (Exception e) {
            e.printStackTrace();
            return res.reason(ResultInfo.INFO_SQL_RETRY);
        }
    }

    @Power(value = Const.POWER_SUADMIN)
    @RequestMapping(value = "/edit",method = {RequestMethod.POST})
    @ResponseBody
    public Object edit(SysUser user){
        Res res = new Res();
        try {
            if (sysUserService.saveOrUpdate(user)){
                SysLog sysLog=GU.addLog(sysUserService.getCurrentUser().getId(),"2",user.toString());
                iSysLogService.addLog(sysLog);
                return res.success();
            }
            return res.reason(ResultInfo.INFO_SQL_RETRY);
        }catch (Exception e) {
            e.printStackTrace();
            return res.reason(e.getMessage());
        }
    }

    @RequestMapping(value = "/del",method = {RequestMethod.POST})
    @ResponseBody
    public Object del(@RequestParam Long userId){
        Res res = new Res();
        try {
           SysLog sysLog=GU.addLog(sysUserService.getCurrentUser().getId(),"3",
                   "删除了名为:"+sysUserService.getById(userId).getUsername()+"的用户");
            iSysLogService.addLog(sysLog);
            return sysUserService.del(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return res.reason(e.getMessage());
        }
    }

    @RequestMapping(value = "/edit/pass",method = {RequestMethod.POST})
    @ResponseBody
    public Object pass(@RequestParam Long userId,@RequestParam String password){
        Res res = new Res();
        try {
            SysLog sysLog=GU.addLog(sysUserService.getCurrentUser().getId(),"2",
                    "修改了用户名为:"+sysUserService.getById(userId).getUsername()+"的密码");
            iSysLogService.addLog(sysLog);
            return sysUserService.editPass(userId,password);
        } catch (Exception e) {
            e.printStackTrace();
            return res.reason(e.getMessage());
        }
    }

    @RequestMapping(value = "/role/update",method = {RequestMethod.POST})
    @ResponseBody
    public Object roleUpdate(@RequestParam Long userId,@RequestParam List<Long> roleIds){
        Res res = new Res();
        try {
            return sysUserService.roleUpdate(userId,roleIds);
        } catch (Exception e) {
            e.printStackTrace();
            return res.reason(e.getMessage());
        }
    }
    @RequestMapping(value = "/usernameDump",method = {RequestMethod.POST})
    @ResponseBody
    public Object usernameDump(String username,Long userId){
        Res res = new Res();
        try {
            return res.success().data(sysUserService.usernameDump(username,userId));
        }catch (Exception e) {
            e.printStackTrace();
            return res.reason(e.getMessage());
        }
    }

    /**
     * 对外提供认证服务的接口
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/userAuthentication/api",method = {RequestMethod.POST})
    @ResponseBody
    public Object userAuthentication(String username,String password){
        Res res = new Res();
        UserInfo userInfo = new UserInfo();
        try {
            SysUser authenticatedUser = sysUserService.findByRawUserInfo(username,password);
            if (authenticatedUser==null){
                userInfo.setAuthenticated(false);
                return res.success().data(userInfo);
            }
            Long orgId = null,areaId = null;
            userInfo.setUser(authenticatedUser);
            if (authenticatedUser.getOrganId() != null){
                orgId = authenticatedUser.getOrganId();
            }
            SysOrganization organization = null;
            if (orgId != null){
                organization = sysOrganizationService.getById(orgId);
            }
            userInfo.setOrganization(organization);
            if (organization!=null && organization.getAreaId()!=null){
                areaId = organization.getId();
            }
            SysArea sysArea = null;
            if (areaId!=null){
                sysArea = sysAreaService.getById(areaId);
            }
            userInfo.setArea(sysArea);
            List<SysRole> roles = roleService.queryRolesByUserId(authenticatedUser.getId());
            userInfo.setRoles(roles);
            List<SysMenu> menus = menuService.findByUserId(authenticatedUser.getId());
            userInfo.setMenus(menus);
            Res mapconfig = maplayerService.getMapConfig();
            userInfo.setMapConfig(mapconfig.getData());
            return res.success().data(userInfo);
        }catch (Exception e) {
            e.printStackTrace();
            return res.reason(e.getMessage());
        }
    }

}
