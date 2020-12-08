package com.gytech.work.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.beans.Transient;
import java.util.Map;

/**
 * Created by LQ on 2020/9/21 0021.
 * com.gytech.work.entity
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoProp {

    //任务id
    private String id;

    //流url
    private String stream;

    //file output path
    private String path;

    private String commandLine;

}
