package com.gytech.Security.annotation;

import java.lang.annotation.*;

/**
 * Created by LQ on 2019/9/4.
 * com.gytech.Security.annotation
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Power {
    String value() default "";
}
