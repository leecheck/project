package com.gytech.controller.adminController;

import com.alibaba.fastjson.JSON;
import com.gytech.Base.BaseController;
import com.gytech.Configuration.EnvContext;
import com.gytech.LocalEntity.Res;
import com.gytech.LocalEntity.ResultInfo;
import com.gytech.Utils.GU;
import com.gytech.entity.admin.SysDict;
import com.gytech.entity.admin.SysLog;
import com.gytech.entity.admin.SysOrganization;
import com.gytech.service.ISysDictService;
import com.gytech.service.ISysLogService;
import com.gytech.service.ISysOrganizationService;
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

/**
 * Created by LQ on 2018/9/12.
 * controller.SysDictController
 */
@Api(tags = "字典管理")
@Controller
@RequestMapping(value = "/admin/sysDict")
public class SysDictController extends BaseController {

    @Autowired
    private ISysDictService dictService;

    @Autowired
    private ISysUserService isysUserService;

    @Autowired
    private ISysLogService iSysLogService;

    @RequestMapping(value = "/list",method = {RequestMethod.GET})
    public Object list(Model model){
        return "sysadmin/sysDict";
    }

    @RequestMapping(value = "/query",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "分页查询")
    public Object query(
            @RequestParam(defaultValue = "{}") String param,
            @RequestParam(defaultValue = "1") Integer curPage,
            @RequestParam(defaultValue = "10") Integer pageSize
    ){
        Res res = new Res();
        try {
            return res.success().data(dictService.query(curPage,pageSize,parseParams(param)));
        } catch (Exception e) {
            e.printStackTrace();
            return res.reason(e.getMessage());
        }
    }

    @RequestMapping(value = "/add",method = {RequestMethod.POST})
    @ResponseBody
    @ApiOperation(value = "新增")
    public Object add(SysDict sysDict){
        Res res = new Res();
        try {
            SysLog sysLog=GU.addLog(isysUserService.getCurrentUser().getId(),"1",sysDict.toString());
            iSysLogService.addLog(sysLog);
            return res.success().data(dictService.add(sysDict));
        }catch (Exception e) {
            e.printStackTrace();
            return res.reason(ResultInfo.INFO_SQL_RETRY);
        }
    }

    @RequestMapping(value = "/edit",method = {RequestMethod.POST})
    @ResponseBody
    @ApiOperation(value = "新增或修改")
    public Object edit(SysDict sysDict){
        Res res = new Res();
        try {
            if (dictService.saveOrUpdate(sysDict)){
                SysLog sysLog=GU.addLog(isysUserService.getCurrentUser().getId(),"2",sysDict.toString());
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
            SysDict dict = dictService.getById(id);
            SysLog sysLog=GU.addLog(
                    isysUserService.getCurrentUser().getId(),"3",
                    "删除了:{代码:"+dict.getDictCode() +  ",字典名：" + dict.getDictName() +"的字典信息"
            );
            iSysLogService.addLog(sysLog);
            return dictService.del(id);
        } catch (Exception e) {
            e.printStackTrace();
            return res.reason(e.getMessage());
        }
    }

    @RequestMapping(value = "/tree",method = {RequestMethod.POST})
    @ResponseBody
    @ApiOperation(value = "树状数据")
    public Object tree(){
        Res res = new Res();
        try {
            return dictService.dictTree();
        } catch (Exception e) {
            e.printStackTrace();
            return res.reason(e.getMessage());
        }
    }
    @RequestMapping(value = "/dictCodeDump",method = {RequestMethod.POST})
    @ResponseBody
    @ApiOperation(value = "代码查重")
    public Object dictCodeDump(@RequestParam String dictCode,@RequestParam Long dictId){
        Res res = new Res();
        try {
            return res.success().data(dictService.dictCodeDump(dictCode,dictId));
        }catch (Exception e) {
            e.printStackTrace();
            return res.reason(e.getMessage());
        }
    }
    @RequestMapping(value = "/getDictItem/api",method = {RequestMethod.POST})
    @ResponseBody
    @ApiOperation(value = "code查字典子项")
    public Object getDictItem(@RequestParam String dictcode){
        Res res = new Res();
        try {
            return res.success().data(dictService.selectItemsByDictCode(dictcode));
        }catch (Exception e) {
            e.printStackTrace();
            return res.reason(e.getMessage());
        }
    }

    @RequestMapping(value = "/getDict/api",method = {RequestMethod.POST})
    @ResponseBody
    @ApiOperation(value = "code查字典项")
    public Object getDictByCode(@RequestParam String dictcode){
        Res res = new Res();
        try {
            return res.success().data(dictService.selectDictByCode(dictcode));
        }catch (Exception e) {
            e.printStackTrace();
            return res.reason(e.getMessage());
        }
    }
}
