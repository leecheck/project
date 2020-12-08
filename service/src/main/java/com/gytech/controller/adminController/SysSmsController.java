package com.gytech.controller.adminController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gytech.Base.BaseController;
import com.gytech.LocalEntity.Res;
import com.gytech.LocalEntity.ResultInfo;
import com.gytech.Utils.GU;
import com.gytech.entity.admin.SysSms;
import com.gytech.service.ISysSmsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api(tags = "短信管理")
@Controller
@RequestMapping(value = "/admin/sysSms")
public class SysSmsController extends BaseController {

    @Autowired
    public ISysSmsService sysSmsService;

    @RequestMapping(value = "/list",method = {RequestMethod.GET})
    public Object list(){
        return "sysadmin/sysSms";
    }

    @RequestMapping(value = "/query/code",method = {RequestMethod.POST})
    @ResponseBody
    public Object queryCode(@RequestParam Integer id){
        Res res = new Res();
        try {
            return res.success().data(sysSmsService.getById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return res.reason(e.getMessage());
        }
    }

    @RequestMapping(value = "/query")
    @ResponseBody
    public Object query(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "{}") String param
    ){
        Res res = new Res();
        try {
            Page pageEntity = new Page<SysSms>(pageNum,pageSize);
            QueryWrapper<SysSms> wrapper = new QueryWrapper<>();
            String smsName = GU.getMapKeyString("smsName",parseParams(param));
            if (StringUtils.isNotBlank(smsName)){
                wrapper.eq("sms_name",smsName);
            }
            return res.success().data(pageEntity.setRecords(sysSmsService.list(wrapper)));
        } catch (Exception e) {
            e.printStackTrace();
            return res.reason(e.getMessage());
        }
    }

    @ApiOperation(value = "绑定用户列表")
    @RequestMapping(value = "/bind/user",method = {RequestMethod.POST})
    @ResponseBody
    public Object bindUser(@RequestParam Long id,@RequestParam String userIds){
        Res res = new Res();
        try {
            sysSmsService.bindUser(id,userIds,res);
            return res;
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
            return res.data(sysSmsService.removeById(id)).success();
        } catch (Exception e) {
            e.printStackTrace();
            return res.reason(e.getMessage());
        }
    }

    @RequestMapping(value = "/add",method = {RequestMethod.POST})
    @ResponseBody
    public Object add(SysSms sms){
        Res res = new Res();
        try {
            sysSmsService.save(sms);
            return res.success();
        }catch (Exception e) {
            e.printStackTrace();
            return res.reason(ResultInfo.INFO_PARAM_MAP_ERROR);
        }
    }



}
