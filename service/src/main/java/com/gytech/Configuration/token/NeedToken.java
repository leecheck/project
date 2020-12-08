package com.gytech.Configuration.token;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 需要登录才能进行操作的注解UserLoginToken
 *
 * @author Li Lu
 * @date 2019-06-21
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface NeedToken {
    boolean required() default true;
}
