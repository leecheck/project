package com.gytech.controller.adminController;

import com.alibaba.fastjson.JSON;
import com.gytech.Base.BaseController;
import com.gytech.Const;
import com.gytech.LocalEntity.Res;
import com.gytech.LocalEntity.ResultInfo;
import com.gytech.Utils.GU;
import com.gytech.entity.admin.SysLog;
import com.gytech.entity.admin.SysMaplayer;
import com.gytech.service.ISysDictService;
import com.gytech.service.ISysLogService;
import com.gytech.service.ISysMaplayerService;
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
 * controller.adminController
 */
@Api(tags = "地图图层管理")
@Controller
@RequestMapping(value = "/admin/sysMaplayer")
public class SysMaplayerController extends BaseController {

    @Autowired
    private ISysMaplayerService maplayerService;

    @Autowired
    private ISysDictService dictService;

    @Autowired
    private ISysUserService isysUserService;

    @Autowired
    private ISysLogService iSysLogService;

    @RequestMapping(value = "/list",method = {RequestMethod.GET})
    public Object list(Model model){
        return "sysadmin/sysMaplayer";
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
            return res.success().data(maplayerService.query(curPage,pageSize,parseParams(param)));
        } catch (Exception e) {
            e.printStackTrace();
            return res.reason(ResultInfo.INFO_PARAM_MAP_ERROR);
        }
    }

    @RequestMapping(value = "/add",method = {RequestMethod.POST})
    @ResponseBody
    public Object add(SysMaplayer layer){
        Res res = new Res();
        try {
            SysLog sysLog=GU.addLog(
                    isysUserService.getCurrentUser().getId(),"1",layer.toString()
            );
            iSysLogService.addLog(sysLog);
            return maplayerService.add(layer);
        }catch (Exception e) {
            e.printStackTrace();
            return res.reason(ResultInfo.INFO_PARAM_MAP_ERROR);
        }
    }

    @RequestMapping(value = "/edit",method = {RequestMethod.POST})
    @ResponseBody
    public Object edit(SysMaplayer layer){
        Res res = new Res();
        try {
            if (maplayerService.saveOrUpdate(layer)){
                SysLog sysLog=GU.addLog(isysUserService.getCurrentUser().getId(),"2",layer.toString());
                return iSysLogService.addLog(sysLog);
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
                    "删除名为:"+maplayerService.getById(id).getLayerName()+"的图层"
                            );
            iSysLogService.addLog(sysLog);
            return maplayerService.del(id);
        } catch (Exception e) {
            e.printStackTrace();
            return res.reason(e.getMessage());
        }
    }

    @ApiOperation(value = "获取layerGroup集合")
    @RequestMapping(value = "/get/layerGroup",method = {RequestMethod.POST})
    @ResponseBody
    public Object getLayerGroup(){
        Res res = new Res();
        try {
            return dictService.selectItemsByDictCode(Const.LAYER_GROUP);
        } catch (Exception e) {
            e.printStackTrace();
            return res.reason(e.getMessage());
        }
    }

    @ApiOperation(value = "获取layerType集合")
    @RequestMapping(value = "/get/layerType",method = {RequestMethod.POST})
    @ResponseBody
    public Object getLayerType(){
        Res res = new Res();
        try {
            return dictService.selectItemsByDictCode(Const.LAYER_TYPE);
        } catch (Exception e) {
            e.printStackTrace();
            return res.reason(e.getMessage());
        }
    }

    @ApiOperation(value = "获取mapConfig")
    @RequestMapping(value = "/get/mapConfig",method = {RequestMethod.POST})
    @ResponseBody
    public Object mapConfig(){
        Res res = new Res();
        try {
            return maplayerService.getMapConfig();
        } catch (Exception e) {
            e.printStackTrace();
            return res.reason(e.getMessage());
        }
    }

}
