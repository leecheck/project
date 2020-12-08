package com.gytech.appListener;

import com.gytech.entity.admin.SysRunLog;
import com.gytech.service.ISysRunLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Time: 下午6:14
 */
public class ApplicationReadyListener implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger LOG = LoggerFactory
            .getLogger(ApplicationReadyListener.class);

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        LOG.info("----------启动完成----------");
    }
}
