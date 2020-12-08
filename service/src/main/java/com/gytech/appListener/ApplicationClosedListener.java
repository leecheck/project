package com.gytech.appListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

/**
 * Created with IntelliJ IDEA.
 * Time: 上午11:09
 */
public class ApplicationClosedListener implements ApplicationListener<ContextClosedEvent> {

    private static final Logger LOG = LoggerFactory
            .getLogger(ApplicationClosedListener.class);

    @Override
    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
        LOG.info("----------关闭----------");
    }
}
