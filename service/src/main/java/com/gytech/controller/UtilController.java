package com.gytech.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.gytech.Base.BaseController;
import com.gytech.Configuration.token.PassToken;
import com.gytech.LocalEntity.Res;
import com.gytech.Utils.ExcelConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by LQ on 2019/10/29.
 * com.gytech.controller
 */
@Api(tags = "")
@RestController
@RequestMapping("rest/util")
public class UtilController extends BaseController {


    @Autowired
    private ExcelConfig excelConfig;

    //打开exe程序
    @PassToken
    @ApiOperation("调用exe")
    @PostMapping(value = "/runExe")
    @ResponseBody
    public Object runExe(String exePath){
        Process process = null;
        Runtime runtime = Runtime.getRuntime();
        try {
            process = runtime.exec(exePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }



}
