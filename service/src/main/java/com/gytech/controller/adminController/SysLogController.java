package com.gytech.controller.adminController;

import com.alibaba.fastjson.JSON;
import com.gytech.Base.BaseController;
import com.gytech.Const;
import com.gytech.LocalEntity.Res;
import com.gytech.LocalEntity.ResultInfo;
import com.gytech.entity.admin.SysArea;
import com.gytech.entity.admin.SysLog;
import com.gytech.service.ISysDictService;
import com.gytech.service.ISysLogService;
import com.gytech.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.Map;

@Api(tags = "日志管理")
@Controller
@RequestMapping(value = "/admin/sysLog")
public class SysLogController extends BaseController {
    @Autowired
    private ISysLogService iSysLogService;

    @Autowired
    private ISysDictService dictService;

    @RequestMapping(value = "/list",method = {RequestMethod.GET})
    public Object list(){
        return "sysadmin/sysLog";
    }

    @RequestMapping(value = "/query",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Object querySysLog(
            @RequestParam(defaultValue = "{}") String param,
            @RequestParam(defaultValue = "1") Integer curPage,
            @RequestParam(defaultValue = "10") Integer pageSize
    ){
        Res res = new Res();
        try {
            return res.success().data(iSysLogService.querySysLog(curPage,pageSize,parseParams(param)));
        } catch (Exception e) {
            e.printStackTrace();
            return res.reason(e.getMessage());
        }
    }

    @RequestMapping(value = "/add",method = {RequestMethod.POST})
    @ResponseBody
    public Object add(@ApiParam(required = true,value="syslog")@RequestBody SysLog syslog){
        Res res = new Res();
        try {
            return res.success().data(iSysLogService.addLog(syslog));
        } catch (Exception e) {
            e.printStackTrace();
            return res.reason(e.getMessage());
        }
    }

    @RequestMapping(value = "/add/api",method = {RequestMethod.POST})
    @ResponseBody
    public Object addapi(@ApiParam(required = true,value="syslog")@RequestBody SysLog syslog){
        return add(syslog);
    }

    @RequestMapping(value = "/del",method = {RequestMethod.POST})
    @ResponseBody
    public Object del(@RequestParam Long id){
        Res res = new Res();
        try {
            return iSysLogService.del(id);
        } catch (Exception e) {
            e.printStackTrace();
            return res.reason(e.getMessage());
        }
    }

    @RequestMapping(value = "/logType",method = {RequestMethod.POST})
    @ResponseBody
    public Object logType(){
        Res res = new Res();
        try {
            return dictService.selectItemsByDictCode(Const.LOG_TYPE);
        } catch (Exception e) {
            e.printStackTrace();
            return res.reason(e.getMessage());
        }
    }


}
