package com.gytech.controller.workController;


import com.gytech.Base.BaseController;
import com.gytech.Base.R;
import com.gytech.Configuration.token.PassToken;
import com.gytech.service.IStationMeteorologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 海洋气象站位信息 前端控制器
 * </p>
 *
 * @author LQ
 * @since 2020-11-12
 */
@RestController
@RequestMapping("/rest/stationMeteorology")
public class StationMeteorologyController extends BaseController {

    @Autowired
    private IStationMeteorologyService stationMeteorologyService;

    @PassToken
    @PostMapping(value = "/query")
    public R query(){
        return R.okData(stationMeteorologyService.baseQuery());
    }

    /**
     * 空间查询
     * @param extent
     * @param thin
     * @param where
     * @return
     */
    @PassToken
    @PostMapping(value = "/spaceQuery")
    public R spaceQuery(String extent,Integer thin,Integer segId,String where){
        return R.okData(stationMeteorologyService.spaceQuery(extent,thin,segId,where));
    }

}

