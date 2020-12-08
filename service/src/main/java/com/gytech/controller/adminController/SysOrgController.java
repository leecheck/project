package com.gytech.controller.adminController;

import com.alibaba.fastjson.JSON;
import com.gytech.Base.BaseController;
import com.gytech.Configuration.EnvContext;
import com.gytech.LocalEntity.Res;
import com.gytech.LocalEntity.ResultInfo;
import com.gytech.Utils.GU;
import com.gytech.entity.admin.SysLog;
import com.gytech.entity.admin.SysOrganization;
import com.gytech.service.ISysAreaService;
import com.gytech.service.ISysLogService;
import com.gytech.service.ISysOrganizationService;
import com.gytech.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

/**
 * Created by LQ on 2018/9/12.
 * controller.SysOrgController
 */
@Api(tags = "单位/行政组织管理")
@Controller
@RequestMapping(value = "/admin/sysOrg")
public class SysOrgController extends BaseController {

    @Autowired
    private EnvContext env;

    @Autowired
    private ISysUserService isysUserService;

    @Autowired
    private ISysOrganizationService organizationService;

    @Autowired
    private ISysAreaService iSysAreaService;

    @Autowired
    private ISysLogService iSysLogService;
    @RequestMapping(value = "/list",method = {RequestMethod.GET})
    public Object list(Model model){
        return "sysadmin/sysOrg";
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
            return res.success().data(organizationService.query(curPage,pageSize,parseParams(param)));
        }catch (Exception e) {
            e.printStackTrace();
            return res.reason(e.getMessage());
        }
    }

    @RequestMapping(value = "/add",method = {RequestMethod.POST})
    @ResponseBody
    public Object add(SysOrganization org){
        Res res = new Res();
        try {
            SysLog sysLog=GU.addLog(isysUserService.getCurrentUser().getId(),"1",org.toString());
            iSysLogService.addLog(sysLog);
            return organizationService.add(org);
        }catch (Exception e) {
            e.printStackTrace();
            return res.reason(e.getMessage());
        }
    }

    @RequestMapping(value = "/edit",method = {RequestMethod.POST})
    @ResponseBody
    public Object edit(SysOrganization org){
        Res res = new Res();
        try {
            if (organizationService.saveOrUpdate(org)){
                organizationService.updateTree();
                SysLog sysLog=GU.addLog(isysUserService.getCurrentUser().getId(),"2",org.toString());
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
    public Object del(@RequestParam Long id){
        Res res = new Res();
        try {
            SysLog sysLog=GU.addLog(isysUserService.getCurrentUser().getId(),"3",
                    "删除了名为:"+organizationService.getById(id).getOrganName()+"的单位");
            iSysLogService.addLog(sysLog);
            return organizationService.del(id);
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
            return organizationService.orgTree();
        } catch (Exception e) {
            e.printStackTrace();
            return res.reason(e.getMessage());
        }
    }

    /***
     * code重复检测
     */
    @RequestMapping(value = "/organCodeDump",method = {RequestMethod.POST})
    @ResponseBody
    public Object organCodeDump(@RequestParam String organCode,@RequestParam Long organId){
        Res res = new Res();
        try {
            return res.success().data(organizationService.organCodeDump(organCode,organId));
        }catch (Exception e) {
            e.printStackTrace();
            return res.reason(e.getMessage());
        }
    }
}
