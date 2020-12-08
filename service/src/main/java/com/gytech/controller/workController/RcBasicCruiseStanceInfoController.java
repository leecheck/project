package com.gytech.controller.workController;


import com.gytech.Base.R;
import com.gytech.Configuration.token.PassToken;
import com.gytech.service.IRcBasicCruiseStanceInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 站位信息 前端控制器
 * </p>
 *
 * @author LQ
 * @since 2020-11-12
 */
@Api(tags = "基本站位信息")
@RestController
@RequestMapping("/rest/rcBasicCruiseStanceInfo")
public class RcBasicCruiseStanceInfoController {

    @Autowired
    private IRcBasicCruiseStanceInfoService service;

    @PassToken
    @ApiOperation("航段站位")
    @PostMapping("/query/cruiseSeg")
    public R query(Integer segId){
        return R.okData(service.getByCruiseSegId(segId));
    }

}

