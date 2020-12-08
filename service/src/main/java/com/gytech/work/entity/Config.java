package com.gytech.work.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="config")
public class Config {

    private String fileUpdatePath;

    public String getFileUpdatePath() {
        return fileUpdatePath;
    }

    public void setFileUpdatePath(String fileUpdatePath) {
        this.fileUpdatePath = fileUpdatePath;
    }
}
