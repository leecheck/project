package com.gytech.Configuration;

import com.alibaba.fastjson.JSON;
import com.gytech.AppApplication;
import com.gytech.Base.BaseLogger;
import com.gytech.LocalEntity.StationConf;
import com.gytech.LocalEntity.SysConf;
import com.gytech.Utils.FileUtil;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by LQ on 2018/10/9.
 * com.gytech.Configuration
 */
public class ConfigManager extends BaseLogger {

    private String wkid = "4490";

    private List<StationConf> stationConf;

    private String iotServer;

    private String wsServer;

    private boolean error;

    private static volatile ConfigManager instance;

    private ConfigManager(){
        boolean activeOutFile = true;
        InputStream confStream = null;
        File outFile = new File("Conf.txt");
        if (!outFile.exists()){
            logger.info("外部配置文件不存在，尝试加载内部配置文件");
            activeOutFile = false;
        }
        try {
            if (activeOutFile){
                confStream = new FileInputStream(outFile);
            }else {
                Resource resource = new ClassPathResource("conf/Emv.txt");
                if (!resource.exists()){
                    logger.error("缺失系统配置，无法进行物联港数据连接（必须保证jar统计目录存在Conf.txt或者jar内存在配置文件）");
                }
                confStream = resource.getInputStream();
            }
        } catch (IOException e) {
            e.printStackTrace();
            this.error = true;
            logger.error("缺失系统关键配置：Conf.txt 或者 conf/Emv.txt，启动失败，关闭系统");
            AppApplication.context.close();
        }
        String configstr = null;
        configstr = FileUtil.stream2String(confStream,"UTF-8");
        SysConf sysConf = JSON.parseObject(configstr,SysConf.class);
        this.wkid = sysConf.getWkid();
        this.iotServer = sysConf.getIotServer();
        this.wsServer = sysConf.getWsServer();
        this.stationConf = sysConf.getStationConf();
    }

    public static ConfigManager getInstance(){
        if (instance == null){
            synchronized (SysConf.class){
                if (instance == null){
                    try {
                        instance = new ConfigManager();
                    } catch (Exception e) {
                        e.printStackTrace();
                        AppApplication.context.close();
                    }
                }
            }
        }
        return instance;
    }

    public void setWkid(String wkid) {
        this.wkid = wkid;
    }

    public void setStationConf(List<StationConf> stationConf) {
        this.stationConf = stationConf;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public static void setInstance(ConfigManager instance) {
        ConfigManager.instance = instance;
    }

    public String getWkid() {
        return wkid;
    }

    public List<StationConf> getStationConf() {
        return stationConf;
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

    public boolean isError() {
        return error;
    }
}
