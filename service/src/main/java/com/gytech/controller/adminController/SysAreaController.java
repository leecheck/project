package com.gytech.controller.adminController;

import com.alibaba.fastjson.JSON;
import com.gytech.Base.BaseController;
import com.gytech.LocalEntity.Res;
import com.gytech.LocalEntity.ResultInfo;
import com.gytech.Utils.GU;
import com.gytech.entity.admin.SysArea;
import com.gytech.entity.admin.SysLog;
import com.gytech.service.ISysAreaService;
import com.gytech.service.ISysLogService;
import com.gytech.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@Api(tags = "区域管理")
@Controller
@RequestMapping(value = "/admin/sysArea")
public class SysAreaController extends BaseController {
    @Autowired
    private ISysAreaService iSysAreaService;

    @Autowired
    private ISysUserService isysUserService;

    @Autowired
    private ISysLogService iSysLogService;

    @RequestMapping(value = "/list",method = {RequestMethod.GET})
    public Object list(){
        return "sysadmin/sysArea";
    }

    @RequestMapping(value = "/query",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "列表查询")
    public Object query(
            @RequestParam(defaultValue = "{}") String param,
            @RequestParam(defaultValue = "1") Integer curPage,
            @RequestParam(defaultValue = "10") Integer pageSize
    ){
        Res res = new Res();
        try {
            return res.success().data(iSysAreaService.query(curPage,pageSize, parseParams(param)));
        } catch (Exception e) {
            e.printStackTrace();
            return res.reason(e.getMessage());
        }
    }

    @RequestMapping(value = "/add",method = {RequestMethod.POST})
    @ResponseBody
    @ApiOperation(value = "新增")
    public Object add(SysArea area) {
        Res res = new Res();
        try {
            SysLog sysLog=GU.addLog( isysUserService.getCurrentUser().getId(),"1",area.toString());
            iSysLogService.addLog(sysLog);
            return res.success().data(iSysAreaService.add(area));
        } catch (Exception e) {
            e.printStackTrace();
            return res.reason(ResultInfo.INFO_SQL_RETRY);
        }
    }

    @RequestMapping(value = "/edit",method = {RequestMethod.POST})
    @ResponseBody
    @ApiOperation(value = "新增或修改")
    public Object edit(SysArea area){
        Res res = new Res();
        try {
            if (iSysAreaService.saveOrUpdate(area)){
                SysLog sysLog=GU.addLog( isysUserService.getCurrentUser().getId(),"2",area.toString());
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
    @ApiOperation(value = "删除")
    public Object del(@RequestParam Long id){
        Res res = new Res();
        try {
            SysArea delArea = iSysAreaService.getById(id);
            SysLog sysLog=GU.addLog( isysUserService.getCurrentUser().getId(),"3","删除了:"+delArea.getAreaName() + "id:{" + id +"}的区域");
            iSysLogService.addLog(sysLog);
            return iSysAreaService.del(id);
        } catch (Exception e) {
            e.printStackTrace();
            return res.reason(e.getMessage());
        }
    }

    @RequestMapping(value = "/tree",method = {RequestMethod.POST})
    @ResponseBody
    @ApiOperation(value = "Area树状数据")
    public Object tree(@RequestParam Long id){
        Res res = new Res();
        try {
            return iSysAreaService.orgTree();
        } catch (Exception e) {
            e.printStackTrace();
            return res.reason(e.getMessage());
        }
    }

    @RequestMapping(value = "/areaCodeDump",method = {RequestMethod.POST})
    @ResponseBody
    @ApiOperation(value = "区域代码查重")
    public Object areaCodeDump(@RequestParam String areaCode,@RequestParam Long areaId){
        Res res = new Res();
        try {
            return res.success().data(iSysAreaService.areaCodeDump(areaCode,areaId));
        }catch (Exception e) {
            e.printStackTrace();
            return res.reason(e.getMessage());
        }
    }

}
