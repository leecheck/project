package com.gytech.controller;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.gytech.Configuration.token.NeedToken;
import com.gytech.Configuration.token.PassToken;
import com.gytech.Utils.GeoU;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.gytech.Configuration.ConfigManager;
import com.gytech.entity.admin.SysUser;
import com.gytech.service.ISysMaplayerService;
import com.gytech.service.ISysUserService;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by LQ on 2018/6/24.
 * i.lq.web.controller
 */
@Controller
@RequestMapping(value = {"", "/index"})
public class IndexController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ISysMaplayerService maplayerService;

    @Autowired
    private ISysUserService sysUserService;




    @Value("${path.sql}")
    private String sqlPath;

    @Value("${wsServer}")
    private String wsServer;

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping(value = "/station")
    public Object demo(){
        return "work/station";
    }

    @RequestMapping(value = "/test")
    public Object test(){
        return "work/test";
    }

    @RequestMapping(value = "/work")
    public Object work(){
        return "work/work";
    }

    @RequestMapping(value = "/task")
    public Object task(Model model){
        model.addAttribute("wsServer", wsServer);
        return "work/task";
    }

    @RequestMapping(value = {"workMap"})
    public Object workMap1(Model model){
        ConfigManager configManager = ConfigManager.getInstance();
        model.addAttribute("wsServer", configManager.getWsServer());
        return "work/workMap";
    }

    @RequestMapping(value = {"","/","/workPage"})
    public Object workMap(Model model){
        model.addAttribute("wsServer", wsServer);
        return "work/workPage";
    }


    @NeedToken
    @ApiOperation(value = "", notes = "")
    @RequestMapping(value = "/get/users/api")
    @ResponseBody
    public Object list(){
        QueryWrapper<SysUser> entity = new QueryWrapper<SysUser>();
        return sysUserService.list(entity);
    }

    @RequestMapping(value = "/distence")
    public Object coor(){
        Map<String,Object> mapConf = new HashMap<>();
        Map<String,Object> value = JSON.parseObject("{\"testGround\":{\"name\":\"国家浅海海上综合试验场\",\"lonlats\":[[122.06444,37.5925],[122.064444,37.5741666],[122.0930555,37.5741666],[122.0930555,37.5925],[122.06444,37.592501]]},\"equipCenter\":{\"name\":\"海洋智能装备研究中心\",\"lonlat\":[122.090555,37.5419444]},\"chudao\":{\"name\":\"褚岛基地\",\"lonlat\":[122.0836111,37.4075]},\"baseStation\":{\"name\":\"AIS基站\",\"lonlat\":[122.135,37.566]},\"server\":{\"name\":\"数据中心\",\"lonlat\":[122.117,37.492]}}");
        mapConf.put("value",value);
        return GeoU.distance(new GeoU.LatLon(34.8,117.2),new GeoU.LatLon(34.8,117.21));
    }




}
