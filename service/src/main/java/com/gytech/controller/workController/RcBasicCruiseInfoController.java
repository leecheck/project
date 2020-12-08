package com.gytech.controller.workController;


import com.gytech.Base.R;
import com.gytech.Configuration.token.PassToken;
import com.gytech.service.IRcBasicCruiseInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 共享航次信息 前端控制器
 * </p>
 *
 * @author LQ
 * @since 2020-11-12
 */
@Api(tags = "共享航次信息")
@Controller
@RequestMapping("/rest/rcBasicCruiseInfo")
public class RcBasicCruiseInfoController {

    @Autowired private IRcBasicCruiseInfoService service;

    @ApiOperation(value="id查询")
    @PostMapping(value = "/query")
    @PassToken
    public R query(Integer cruiseId){
        return R.okData(service.getByCruiseId(cruiseId));
    }



}

