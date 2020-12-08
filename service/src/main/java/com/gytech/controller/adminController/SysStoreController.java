package com.gytech.controller.adminController;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gytech.Base.BaseController;
import com.gytech.Configuration.EnvContext;
import com.gytech.LocalEntity.Res;
import com.gytech.LocalEntity.ResultInfo;
import com.gytech.Utils.GU;
import com.gytech.entity.admin.SysLog;
import com.gytech.entity.admin.SysStore;
import com.gytech.service.ISysLogService;
import com.gytech.service.ISysStoreService;
import com.gytech.service.ISysUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by LQ on 2018/9/12.
 * controller.adminController
 */

@EnableAsync
@Api(tags = "数据库备份管理")
@Controller
@RequestMapping(value = "/admin/sysStore")
public class SysStoreController extends BaseController {

    @Autowired
    private EnvContext env;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ISysLogService iSysLogService;

    @Autowired
    private ISysStoreService storeService;


    @RequestMapping(value = "/list",method = {RequestMethod.GET})
    public Object sysSql(){
        return "sysadmin/sysStoreBackup";
    }

    @RequestMapping(value = "/query",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Object query(
            @RequestParam(defaultValue = "1") Integer curPage,
            @RequestParam(defaultValue = "10") Integer pageSize
    ){
        Res res = new Res();
        try {
            Page pageEntity = new Page<SysStore>(curPage,pageSize);
            return res.success().data(storeService.page(pageEntity));
        }catch (Exception e) {
            e.printStackTrace();
            return res.reason(e.getMessage());
        }
    }

    @RequestMapping(value = "/add",method = {RequestMethod.POST})
    @ResponseBody
    public Object add(SysStore store){
        Res res = new Res();
        try {
            SysLog sysLog=GU.addLog(sysUserService.getCurrentUser().getId(),"1",store.toString());
            iSysLogService.addLog(sysLog);
            return res.success().data(storeService.add(store));
        }catch (Exception e) {
            e.printStackTrace();
            return res.reason(ResultInfo.INFO_SQL_RETRY);
        }
    }

    @RequestMapping(value = "/del",method = {RequestMethod.POST})
    @ResponseBody
    public Object del(@RequestParam Long id){
        Res res = new Res();
        try {
            SysLog sysLog=GU.addLog(sysUserService.getCurrentUser().getId(),"3",
                    "删除了名为:"+storeService.getById(id).getDatabaseName()+"的记录");
            iSysLogService.addLog(sysLog);
            return res.success().data(storeService.removeById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return res.reason(e.getMessage());
        }
    }

    @RequestMapping(value = "/edit",method = {RequestMethod.POST})
    @ResponseBody
    public Object edit(SysStore store){
        Res res = new Res();
        try {
            SysLog sysLog=GU.addLog(sysUserService.getCurrentUser().getId(),"2",store.toString());
            iSysLogService.addLog(sysLog);
            return res.success().data(storeService.updateById(store));
        }catch (Exception e) {
            e.printStackTrace();
            return res.reason(ResultInfo.INFO_SQL_RETRY);
        }
    }

    @RequestMapping(value = "/backup",method = {RequestMethod.POST})
    @ResponseBody
    public Object backup(@RequestParam Long id){
        Res res = new Res();
        try {
            logger.info("backup start");
            logger.info(String.valueOf(Thread.currentThread().getId()));
            Res connectRes = storeService.connect(id);
            if (connectRes.getSuccess()){
                storeService.backupStore(id);
                return res.success().data("备份已成功启动，稍后在文件管理页面查看备份结果");
            }else {
                return res.reason(connectRes.getReason());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return res.reason(e.getMessage());
        }
    }

    @RequestMapping(value = "/connect",method = {RequestMethod.POST})
    @ResponseBody
    public Object connect(@RequestParam Long id){
        Res res = new Res();
        try {
            return storeService.connect(id);
        } catch (Exception e) {
            e.printStackTrace();
            return res.reason(e.getMessage());
        }
    }


}
