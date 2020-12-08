package com.gytech.appListener;

import com.gytech.Configuration.ConfigManager;
import com.gytech.entity.admin.SysRunLog;
import com.gytech.service.ISysRunLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Time: 上午11:07
 */
public class ApplicationStartedListener implements ApplicationListener<ApplicationStartedEvent> {

    private static final Logger LOG = LoggerFactory
            .getLogger(ApplicationStartedListener.class);

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        LOG.info("----------应用启动 加载ConfigManager----------");
    }
}
