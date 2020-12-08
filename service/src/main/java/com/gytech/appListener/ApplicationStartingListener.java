package com.gytech.appListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;

/**
 * Created with IntelliJ IDEA.
 * Time: 下午8:18
 */
public class ApplicationStartingListener implements ApplicationListener<ApplicationStartingEvent> {

    private static final Logger LOG = LoggerFactory
            .getLogger(ApplicationStartingListener.class);

    @Override
    public void onApplicationEvent(ApplicationStartingEvent applicationStartingEvent) {
        LOG.info("----------正在启动----------");
    }
}
