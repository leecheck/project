package com.gytech.work;

import com.gytech.work.entity.VideoProp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by LQ on 2018/6/29.
 * i.lq.web.LocalEntity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "video")
public class MultiVideo {

    private Map<String, Object> videoMap;

    public Map<String, Object> getVideoMap() {
        return videoMap;
    }

    public void setVideoMap(Map<String, Object> videoMap) {
        this.videoMap = videoMap;
    }
}
