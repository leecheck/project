package com.gytech.Base;

import com.alibaba.fastjson.JSON;
import com.gytech.Const;
import com.gytech.Utils.FileUtil;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyEditorSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

/**
 * Created by LQ on 2018/9/11.
 * controller
 */
public class BaseController extends BaseLogger{

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        String[] parsePatterns = new String[] { "yyyy-MM-dd HH:mm:ss" };
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                try {
                    setValue(DateUtils.parseDate(text, parsePatterns));
                } catch (ParseException e) {
                    throw new IllegalArgumentException("日期格式错误!");
                }
            }

            @Override
            public String getAsText() {
                Date value = (Date)getValue();
                return value == null ? "" : DateFormatUtils.format(value, "yyyy-MM-dd HH:mm:ss");
            }
        });
    }

    /**
     * result
     */
    private enum Result {
        success, result, error
    }

    /**
     * 重定向
     * @param url
     * @return
     */
    protected String redirectTo(String url) {
        StringBuffer rto = new StringBuffer("redirect:");
        rto.append(url);
        return rto.toString();
    }

    /**
     * str转utf-8 转map
     * @param paramsStr
     * @return
     */
    protected Map<String, String> parseParams(String paramsStr) throws UnsupportedEncodingException {
        String paramstr = java.net.URLDecoder.decode(paramsStr,"utf-8");
        return JSON.parseObject(paramstr, Map.class);
    }

    /**
     * str转utf-8 转map
     * @param paramsStr
     * @return
     */
    protected String parseParam(String paramsStr) throws UnsupportedEncodingException {
        return java.net.URLDecoder.decode(paramsStr,"utf-8");
    }

    /**
     * send file
     *
     * @param file
     * @param response
     */
    protected void sendFile(File file, HttpServletResponse response) throws IOException {
        if (file == null || response == null) return;
        FileUtil.sendFile(file,response);
    }


}
