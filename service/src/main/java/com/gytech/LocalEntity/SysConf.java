package com.gytech.LocalEntity;

import com.alibaba.fastjson.JSON;
import com.gytech.Base.BaseController;
import com.gytech.Base.BaseLogger;
import com.gytech.Utils.FileUtil;
import com.gytech.entity.admin.SysMaplayer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by LQ on 2018/9/30.
 * com.gytech.LocalEntity
 */
public class SysConf extends BaseLogger {

    private String wkid = "4490";

    private List<StationConf> stationConf;

    private String iotServer;

    private String wsServer;

    public String getWkid() {
        return wkid;
    }

    public void setWkid(String wkid) {
        this.wkid = wkid;
    }

    public List<StationConf> getStationConf() {
        return stationConf;
    }

    public void setStationConf(List<StationConf> stationConf) {
        this.stationConf = stationConf;
    }

    public String getIotServer() {
        return iotServer;
    }

    public void setIotServer(String iotServer) {
        this.iotServer = iotServer;
    }

    public String getWsServer() {
        return wsServer;
    }

    public void setWsServer(String wsServer) {
        this.wsServer = wsServer;
    }
}
