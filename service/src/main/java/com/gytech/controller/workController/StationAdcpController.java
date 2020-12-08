package com.gytech.controller.workController;


import com.gytech.Base.R;
import com.gytech.Configuration.token.PassToken;
import com.gytech.service.IStationAdcpService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * ADCP站位信息 前端控制器
 * </p>
 *
 * @author LQ
 * @since 2020-11-12
 */
@Api(tags = "ADCP站位")
@RestController
@RequestMapping("/rest/stationAdcp")
public class StationAdcpController {

    @Autowired
    private IStationAdcpService service;

    @PassToken
    @ApiOperation("航段查询")
    @PostMapping("/query/cruiseSeg")
    public R query(Integer segId){
        return R.okData(service.getByCruiseSegId(segId));
    }
}

