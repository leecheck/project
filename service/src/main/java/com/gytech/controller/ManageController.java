package com.gytech.controller;

import com.gytech.Base.BaseController;
import com.gytech.Configuration.token.NeedToken;
import com.gytech.Configuration.token.PassToken;
import com.gytech.LocalEntity.Res;
import com.gytech.LocalEntity.ResultInfo;
import com.gytech.Security.annotation.JwtUser;
import com.gytech.Security.entity.UserInfo;
import com.gytech.Utils.GU;
import com.gytech.entity.admin.SysLog;
import com.gytech.entity.admin.SysUser;
import com.gytech.service.ISysLogService;
import com.gytech.service.ISysOrganizationService;
import com.gytech.service.ISysUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LQ on 2019/9/9.
 * com.gytech.controller.workController
 */
@RestController
@RequestMapping("/rest/manage")
public class ManageController extends BaseController {

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ISysOrganizationService organizationService;

    @Autowired
    private ISysLogService iSysLogService;

    @PassToken
    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    @ResponseBody
    public Object restLogin(@RequestParam String userName, @RequestParam String passWord) {
        Res res = new Res();
        try {
            return sysUserService.restLogin(userName, passWord);
        } catch (Exception e) {
            e.printStackTrace();
            return res.reason(e.getMessage());
        }
    }

}
