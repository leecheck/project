package com.gytech.controller.workController;


import com.gytech.Base.R;
import com.gytech.Configuration.token.PassToken;
import com.gytech.service.IRcBasicCruiseProjectInfoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 共享航次项目信息 前端控制器
 * </p>
 *
 * @author LQ
 * @since 2020-11-12
 */
@RestController
@RequestMapping("/rest/rcBasicCruiseProjectInfo")
public class RcBasicCruiseProjectInfoController {


    @Autowired
    private IRcBasicCruiseProjectInfoService service;
    /**
     *
     * @param year
     * @param name project name 非curisename
     * @return
     */

    @PassToken
    @ApiOperation(value="年-名称 模糊查询")
    @PostMapping(value = "/queryLike")
    public R queryLike(String year, String name){
        return R.okData(service.getByCruiseLike(year, name));
    }

}

