package com.gytech.Security.annotation;

import java.lang.annotation.*;

/**
 * Created by LQ on 2019/9/11.
 * com.gytech.Configuration.token
 *  自动注入
 *  拦截方法，根据参数名注入当前用户实体
 *  用于参数注解
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JwtUser {

    String user() default "user";

}
