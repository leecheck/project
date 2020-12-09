package com.gytech;

import com.gytech.appListener.ApplicationClosedListener;
import com.gytech.appListener.ApplicationReadyListener;
import com.gytech.appListener.ApplicationStartedListener;
import com.gytech.appListener.ApplicationStartingListener;
import com.gytech.work.Ws;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@ServletComponentScan
public class AppApplication {

	public static ConfigurableApplicationContext context;

	public static void main(String[] args) {
        SpringApplication application = new SpringApplication(AppApplication.class);
        application.addListeners(new ApplicationStartingListener());
        application.addListeners(new ApplicationStartedListener());
        application.addListeners(new ApplicationReadyListener());
        application.addListeners(new ApplicationClosedListener());
        ConfigurableApplicationContext configurableApplicationContext = application.run(args);
        //解决WebSocket不能注入的问题
        Ws.setApplicationContext(configurableApplicationContext);
	}
}
