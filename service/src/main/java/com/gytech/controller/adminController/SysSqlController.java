package com.gytech.controller.adminController;

import com.alibaba.fastjson.JSON;
import com.gytech.Base.BaseController;
import com.gytech.Configuration.EnvContext;
import com.gytech.LocalEntity.Res;
import com.gytech.LocalEntity.ResultInfo;
import com.gytech.entity.admin.SysUser;
import com.gytech.service.ISysUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * Created by LQ on 2018/9/12.
 * controller.adminController
 */
@Api(tags = "数据库监控")
@Controller
@RequestMapping(value = "/admin/sysSql")
public class SysSqlController extends BaseController {

    @Autowired
    private EnvContext env;


    @RequestMapping(value = "/list")
    public Object sysSql(){
        return "sysadmin/sysSql";
    }


}
