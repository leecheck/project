package com.gytech.controller.adminController;

import com.gytech.Base.BaseController;
import com.gytech.Base.BaseLogger;
import com.gytech.LocalEntity.Res;
import com.gytech.LocalEntity.ResultInfo;
import com.gytech.service.ISysFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by LQ on 2018/12/4.
 * com.gytech.controller.adminController
 */
@Api(tags = "文件管理")
@Controller
@RequestMapping(value = "/admin/sysFile")
public class SysFileController extends BaseController {

    @Autowired
    private ISysFileService fileService;

    @RequestMapping(value = "/list",method = {RequestMethod.GET})
    public Object list(){
        return "sysadmin/sysFile";
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
            return res.success().data(fileService.query(curPage,pageSize,parseParams(param)));
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
        	fileService.removeById(id);
        	res.setSuccess(true);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return res.reason(e.getMessage());
        }
    }

    @RequestMapping(value = "/download",method = {RequestMethod.GET})
    @ResponseBody
    public Object download(@RequestParam Long id,HttpServletResponse response){
        Res res = new Res();
        try {
            return fileService.downloadById(id,response);
        } catch (Exception e) {
            e.printStackTrace();
            return res.reason(e.getMessage());
        }
    }
}
