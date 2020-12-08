package com.gytech.dataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by LQ on 2018/6/29.
 * i.lq.web.LocalEntity
 */
@Component
@ConfigurationProperties(prefix = "local-data-source")
public class MultiDs {

    private Map<String,DruidDataSourceProperty> dss;

    public Map<String, DruidDataSourceProperty> getDss() {
        return dss;
    }

    public void setDss(Map<String, DruidDataSourceProperty> dss) {
        this.dss = dss;
    }
}
