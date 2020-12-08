package com.gytech.controller.adminController;

import com.alibaba.fastjson.JSON;
import com.gytech.Base.BaseController;
import com.gytech.LocalEntity.Res;
import com.gytech.LocalEntity.ResultInfo;
import com.gytech.Utils.GU;
import com.gytech.entity.admin.SysLog;
import com.gytech.entity.admin.SysRole;
import com.gytech.entity.admin.SysUser;
import com.gytech.service.ISysLogService;
import com.gytech.service.ISysMenuService;
import com.gytech.service.ISysRoleService;
import com.gytech.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by LQ on 2018/9/12.
 * controller.adminController
 */
@Api(tags = "角色管理")
@Controller
@RequestMapping(value = "/admin/sysRole")
public class SysRoleController extends BaseController {

    @Autowired
    private ISysRoleService sysRoleService;
    @Autowired
    private ISysMenuService iSysMenuService;
    @Autowired
    private ISysUserService isysUserService;

    @Autowired
    private ISysLogService iSysLogService;

    @RequestMapping(value = "/list",method = {RequestMethod.GET})
    public Object list(Model model){
        return "sysadmin/sysRole";
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
            return res.success().data(sysRoleService.query(curPage,pageSize,parseParams(param)));
        } catch (Exception e) {
            e.printStackTrace();
            return res.reason(e.getMessage());
        }
    }

    @RequestMapping(value = "/add",method = {RequestMethod.POST})
    @ResponseBody
    public Object add(SysRole role){
        Res res = new Res();
        try {
            SysLog sysLog=GU.addLog(isysUserService.getCurrentUser().getId(),"1",role.toString());
            iSysLogService.addLog(sysLog);
            return sysRoleService.add(role);
        }catch (Exception e) {
            e.printStackTrace();
            return res.reason(ResultInfo.INFO_PARAM_MAP_ERROR);
        }
    }

    @RequestMapping(value = "/edit",method = {RequestMethod.POST})
    @ResponseBody
    public Object edit(SysRole role){
        Res res = new Res();
        try {
            if (sysRoleService.saveOrUpdate(role)){
                SysLog sysLog=GU.addLog(isysUserService.getCurrentUser().getId(),"2",role.toString());
                iSysLogService.addLog(sysLog);
                return res.success();
            }
            return res.reason(ResultInfo.INFO_SQL_RETRY);
        }catch (Exception e) {
            e.printStackTrace();
            return res.reason(e.getMessage());
        }
    }

    /**
     * 删除role 删除关联的user-role role-menu
     * @param roleId
     * @return
     */
    @RequestMapping(value = "/del",method = {RequestMethod.POST})
    @ResponseBody
    public Object del(@RequestParam Long roleId){
        Res res = new Res();
        try {
            SysLog sysLog=GU.addLog(isysUserService.getCurrentUser().getId(),"3",
                    "删除了名为:"+sysRoleService.getById(roleId).getRoleName()+"的角色"
            );
            iSysLogService.addLog(sysLog);
            return sysRoleService.del(roleId);
        } catch (Exception e) {
            e.printStackTrace();
            return res.reason(e.getMessage());
        }
    }

    @ApiOperation(value = "以userid查询角色列表")
    @RequestMapping(value = "/query/uid",method = {RequestMethod.POST})
    @ResponseBody
    public Object queryByUserId(@RequestParam Long userId){
        Res res = new Res();
        try {
            return sysRoleService.queryByUserId(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return res.reason(e.getMessage());
        }
    }

    @ApiOperation(value = "更新角色对应的权限")
    @RequestMapping(value = "/menu/update")
    @ResponseBody
    public Object roleUpdate(@RequestParam Long rodeId,@RequestParam List<Long> menuIds){
        Res res = new Res();
        try {
            return sysRoleService.menuUpdate(rodeId,menuIds);
        } catch (Exception e) {
            e.printStackTrace();
            return res.reason(e.getMessage());
        }
    }

    @ApiOperation(value = "角色代码查重")
    @RequestMapping(value = "/roleCodeDump")
    @ResponseBody
    public Object roleCodeDump(@RequestParam String roleCode,@RequestParam Long roleId){
        Res res = new Res();
        try {
            return res.success().data(sysRoleService.roleCodeDump(roleCode,roleId));
        }catch (Exception e) {
            e.printStackTrace();
            return res.reason(e.getMessage());
        }
    }

    @ApiOperation(value = "角色名称查重")
    @RequestMapping(value = "/roleNameDump")
    @ResponseBody
    public Object roleNameDump(@RequestParam String roleName,@RequestParam Long roleId){
        Res res = new Res();
        try {
            return res.success().data(sysRoleService.roleNameDump(roleName,roleId));
        }catch (Exception e) {
            e.printStackTrace();
            return res.reason(e.getMessage());
        }
    }

}
