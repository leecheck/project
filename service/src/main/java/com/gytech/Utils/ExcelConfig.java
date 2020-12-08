package com.gytech.Utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Configuration
@ConfigurationProperties(prefix = "excel-config")
public class ExcelConfig {

    private Map<String, String> buoy;

    private Map<String, String> point;

    private Map<String, String> pointPos;

    public Map<String, String> getBuoy() {
        return buoy;
    }

    public void setBuoy(Map<String, String> buoy) {
        this.buoy = buoy;
    }

    public Map<String, String> getPoint() {
        return point;
    }

    public void setPoint(Map<String, String> point) {
        this.point = point;
    }

    public Map<String, String> getPointPos() {
        return pointPos;
    }

    public void setPointPos(Map<String, String> pointPos) {
        this.pointPos = pointPos;
    }
}
