package com.gytech.controller.workController;


import com.gytech.Base.R;
import com.gytech.Configuration.token.PassToken;
import com.gytech.service.IDataCtdService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * CTD数据表 前端控制器
 * </p>
 *
 * @author LQ
 * @since 2020-11-12
 */
@Api(tags = "CTD数据")
@RestController
@RequestMapping("/rest/dataCtd")
public class DataCtdController {

    @Autowired
    private IDataCtdService service;

    @PassToken
    @PostMapping(value = "/datas/byStationSeg")
    public R query(String stationName,Integer segId){
        return R.okData(service.datasByStationAndSegment(stationName,segId));
    }

}

