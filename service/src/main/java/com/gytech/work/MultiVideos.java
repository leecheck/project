package com.gytech.work;

import com.gytech.dataSource.DruidDataSourceProperty;
import com.gytech.work.entity.VideoProp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by LQ on 2018/6/29.
 * i.lq.web.LocalEntity
 */

@Component
@ConfigurationProperties(prefix = "videos")
public class MultiVideos {

    private List<VideoProp> list;

    public List<VideoProp> getList() {
        return list;
    }

    public void setList(List<VideoProp> list) {
        this.list = list;
    }
}
