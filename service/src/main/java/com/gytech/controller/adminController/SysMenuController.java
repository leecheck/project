package com.gytech.controller.adminController;

import com.alibaba.fastjson.JSON;
import com.gytech.Base.BaseController;
import com.gytech.Configuration.EnvContext;
import com.gytech.LocalEntity.Res;
import com.gytech.LocalEntity.ResultInfo;
import com.gytech.Utils.GU;
import com.gytech.entity.admin.SysLog;
import com.gytech.entity.admin.SysMenu;
import com.gytech.entity.admin.SysOrganization;
import com.gytech.service.ISysLogService;
import com.gytech.service.ISysMenuService;
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
import java.util.Map;

@Api(tags = "菜单及权限管理")
@Controller
@RequestMapping(value = "/admin/sysMenu")
public class SysMenuController extends BaseController{

    @Autowired
    private EnvContext env;

    @Autowired
    private ISysUserService isysUserService;

    @Autowired
    private ISysMenuService iSysMenuService;

    @Autowired
    private ISysLogService iSysLogService;

    @RequestMapping(value = "/list",method = {RequestMethod.GET})
    public Object list(Model model){
        return "sysadmin/sysMenu";
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
            return res.success().data(iSysMenuService.query(curPage,pageSize,parseParams(param)));
        } catch (Exception e) {
            e.printStackTrace();
            return res.reason(e.getMessage());
        }
    }

    @RequestMapping(value = "/add",method = {RequestMethod.POST})
    @ResponseBody
    public Object add(SysMenu menu){
        Res res = new Res();
        try {
            SysLog sysLog=GU.addLog(isysUserService.getCurrentUser().getId(),"1",menu.toString());
            iSysLogService.addLog(sysLog);
            return iSysMenuService.add(menu);
        }catch (Exception e) {
            e.printStackTrace();
            return res.reason(ResultInfo.INFO_SQL_RETRY);
        }
    }

    @RequestMapping(value = "/edit",method = {RequestMethod.POST})
    @ResponseBody
    public Object edit(SysMenu menu){
        Res res = new Res();
        try {
            if (iSysMenuService.saveOrUpdate(menu)){
                SysLog sysLog=GU.addLog(isysUserService.getCurrentUser().getId(),"2",menu.toString());
                iSysLogService.addLog(sysLog);
                return res.success();
            }
            return res.reason(ResultInfo.INFO_SQL_RETRY);
        }catch (Exception e) {
            e.printStackTrace();
            return res.reason(e.getMessage());
        }
    }

    @RequestMapping(value = "/childNameDump",method = {RequestMethod.POST})
    @ResponseBody
    public Object childNameDump(String menuName,Long parentId,Long menuId){
        Res res = new Res();
        try {
            return res.success().data(iSysMenuService.childNameDump(menuName,parentId,menuId));
        }catch (Exception e) {
            e.printStackTrace();
            return res.reason(e.getMessage());
        }
    }
    @RequestMapping(value = "/del",method = {RequestMethod.POST})
    @ResponseBody
    public Object del(@RequestParam Long id){
        Res res = new Res();
        try {
            SysLog sysLog=GU.addLog(isysUserService.getCurrentUser().getId(),"3",
                    "删除了名为:"+iSysMenuService.getById(id).getMenuName()+"的菜单");
            iSysLogService.addLog(sysLog);
            return iSysMenuService.del(id);
        } catch (Exception e) {
            e.printStackTrace();
            return res.reason(e.getMessage());
        }
    }
    @RequestMapping(value = "/tree",method = {RequestMethod.POST})
    @ResponseBody
    public Object tree(@RequestParam Long id){
        Res res = new Res();
        try {
            return iSysMenuService.menuTree();
        } catch (Exception e) {
            e.printStackTrace();
            return res.reason(e.getMessage());
        }
    }

    @ApiOperation(value = "根据roleid查询对应权限")
    @RequestMapping(value = "/query/RoleId",method = {RequestMethod.POST})
    @ResponseBody
    public Object queryByRoleId(@RequestParam Long RoleId){
        Res res = new Res();
        try {
            return iSysMenuService.queryByRoleId(RoleId);
        } catch (Exception e) {
            e.printStackTrace();
            return res.reason(e.getMessage());
        }
    }
}
