package com.gytech.controller.workController;


import com.gytech.Base.BaseController;
import com.gytech.Base.R;
import com.gytech.Configuration.token.PassToken;
import com.gytech.service.IDataAdcpService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * ADCP数据表 前端控制器
 * </p>
 *
 * @author LQ
 * @since 2020-11-12
 */
@Api(tags = "ADCP数据")
@RestController
@RequestMapping("/rest/dataAdcp")
public class DataAdcpController extends BaseController {

    @Autowired
    private IDataAdcpService service;

    @PassToken
    @ApiOperation("adcp站位数据")
    @PostMapping(value = "/datas/relateId")
    public R query(Integer relateId){
        return R.okData(service.getByRelateID(relateId));
    }


}

