package com.gytech.Configuration;

import java.util.Date;

import cn.hutool.cache.Cache;
import cn.hutool.cache.CacheUtil;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.gytech.Base.BaseLogger;

/**
 * Created by LQ on 2018/9/14.
 * com.gytech.Configuration
 */
@Configuration
public class BeanConfiguration extends BaseLogger {

    public BeanConfiguration() {
        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
        System.out.println("BeanConfiguration容器启动初始化...");
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        logger.info("BeanConfiguration-sessionRegistry...");
        SessionRegistry sessionRegistry = new SessionRegistryImpl();
        return sessionRegistry;
    }

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    @Bean
    public EnvContext envContext() {
        logger.info("BeanConfiguration-envContext...");
        Date systemStartDate = new Date();
        return new EnvContext(systemStartDate);
    }

    @Bean
    public HttpMessageConverters fastjsonHttpMessageConverter(){
        //定义一个转换消息的对象
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        //添加fastjson的配置信息 比如 ：是否要格式化返回的json数据
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.WriteNullListAsEmpty,//List字段如果为null,输出为[],而非null
                SerializerFeature.WriteNullStringAsEmpty ,//字符类型字段如果为null,输出为"",而非null
                SerializerFeature.DisableCircularReferenceDetect,//消除对同一对象循环引用的问题，默认为false（如果不配置有可能会进入死循环）
                SerializerFeature.WriteNullBooleanAsFalse,//Boolean字段如果为null,输出为false,而非null
                SerializerFeature.WriteMapNullValue, //是否输出值为null的字段,默认为false。
                SerializerFeature.WriteDateUseDateFormat
        );
        //在转换器中添加配置信息
        fastConverter.setFastJsonConfig(fastJsonConfig);
        HttpMessageConverter<?> converter = fastConverter;
        return new HttpMessageConverters(converter);

    }

    @Bean
    public PaginationInterceptor paginationInterceptor(){
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setDialectType("mysql");
        return paginationInterceptor;
    }

    @Bean
    public Cache<String,Object> sysCache(){
        return CacheUtil.newFIFOCache(50);
    }



}
