package com.gytech.controller.workController;


import com.gytech.Base.R;
import com.gytech.Configuration.token.PassToken;
import com.gytech.service.IDataBiochemistryService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 海洋生物化学数据表 前端控制器
 * </p>
 *
 * @author LQ
 * @since 2020-11-12
 */
@Api(tags = "海洋生物化学数据")
@RestController
@RequestMapping("/rest/dataBiochemistry")
public class DataBiochemistryController {

    @Autowired
    private IDataBiochemistryService service;

    @PassToken
    @PostMapping(value = "/datas/byStationSeg")
    public R query(String stationName, Integer segId){
        return R.okData(service.getByStationNameAndSegment(stationName,segId));
    }

}

