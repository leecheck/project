package com.gytech.controller.adminController;

import com.gytech.Base.BaseController;
import com.gytech.LocalEntity.Res;
import com.gytech.LocalEntity.ResultInfo;
import com.gytech.Utils.GU;
import com.gytech.entity.admin.SysLog;
import com.gytech.service.ISysLogService;
import com.gytech.service.ISysRunLogService;
import com.gytech.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

@Api(tags = "运行日志")
@Controller
@RequestMapping(value = "/admin/sysRunLog")
public class SysRunLogController extends BaseController {
    @Autowired
private ISysRunLogService iSysRunLogService;

    @Autowired
    private ISysUserService isysUserService;

    @Autowired
    private ISysLogService iSysLogService;
    @RequestMapping(value = "/list",method = {RequestMethod.GET})
    public Object StatPage(){
        return "sysadmin/sysRunLog";
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
            return res.success().data(iSysRunLogService.query(curPage,pageSize,parseParams(param)));
        } catch (Exception e) {
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
                    "删除了名为:"+iSysRunLogService.getById(id).getUserName()+"的运行日志"
            );
            iSysLogService.addLog(sysLog);
            return iSysRunLogService.del(id);
        } catch (Exception e) {
            e.printStackTrace();
            return res.reason(e.getMessage());
        }
    }
}
