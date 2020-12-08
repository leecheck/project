package com.gytech.controller.workController;


import com.gytech.Base.R;
import com.gytech.Configuration.token.PassToken;
import com.gytech.LocalEntity.Res;
import com.gytech.service.IDataMeteorologyService;
import com.gytech.service.impl.DataMeteorologyServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 海洋气象数据表 前端控制器
 * </p>
 *
 * @author LQ
 * @since 2020-11-12
 */
@Api(tags = "海洋气象数据")
@RestController
@RequestMapping("/rest/dataMeteorology")
public class DataMeteorologyController {

    @Autowired
    private IDataMeteorologyService  service;

    @PassToken
    @PostMapping(value = "/query")
    public Object query(){
        return null;
    }

}

