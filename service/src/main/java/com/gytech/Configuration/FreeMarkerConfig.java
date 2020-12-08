package com.gytech.Configuration;

import com.gytech.Base.BaseLogger;
import com.gytech.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import javax.annotation.PostConstruct;

/**
 * Created by LQ on 2018/9/14.
 * com.gytech.Configuration
 */
@Configuration
public class FreeMarkerConfig extends BaseLogger {

    @Autowired
    private freemarker.template.Configuration configuration;

    @Autowired
    private EnvContext envContext;

    // Spring 初始化的时候加载配置
    @PostConstruct
    public void setConfigure() throws Exception {
        logger.info("加载freemarker配置：");
        logger.info("加载freemarker-环境变量：");
        configuration.setDefaultEncoding(Const.DEFAULT_ENCODING);
        configuration.setDateTimeFormat(Const.DATETIME_FORMAT);
        configuration.setTimeFormat(Const.TIME_FORMAT);
        configuration.setDateFormat(Const.DATE_FORMAT);
        configuration.setURLEscapingCharset(Const.DEFAULT_ENCODING);
        configuration.setNumberFormat("0.##################");
        configuration.setBooleanFormat("true,false");
        configuration.setLocalizedLookup(false);
        //configuration.setTemplateUpdateDelay(5);
        configuration.setSharedVariable("env", envContext);
    }
}
