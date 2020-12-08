package com.gytech.Configuration;

import org.apache.tomcat.util.threads.ThreadPoolExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

/**
 * <p>
 * spring任务管理
 * </p>
 * 
 * @author tonggx
 * @since 2018年11月15日14:25:04
 *
 */
@EnableScheduling
@Configuration
public class SpringTaskManager implements SchedulingConfigurer{
	
	
	/**
	 * 定时任务注册器
	 */
	public ScheduledTaskRegistrar taskRegistrar;
	@Autowired
	public ScheduledAnnotationBeanPostProcessor annotationTaskRegistrar;
	
	@Bean
	public TaskScheduler taskScheduler() {
		ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
		scheduler.setPoolSize(200);
		scheduler.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		scheduler.setWaitForTasksToCompleteOnShutdown(true);
		return scheduler;
	}
	
	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		this.taskRegistrar = taskRegistrar;
	}


}
