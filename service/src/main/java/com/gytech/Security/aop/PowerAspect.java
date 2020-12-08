package com.gytech.Security.aop;


import com.gytech.Security.annotation.Power;
import com.gytech.Security.entity.UserInfo;
import com.gytech.Security.ex.LessPowerEx;
import com.gytech.controller.Handler.RequestExHandler;
import com.gytech.service.ISysUserService;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by LQ on 2019/9/4.
 * com.gytech.Security.aop
 */
@Aspect
@Component
public class PowerAspect {

    @Pointcut(value = "@annotation(com.gytech.Security.annotation.Power)")
    public void annotationPointCut() {
    }

    @Autowired
    private ISysUserService userService;

    @Autowired
    private RequestExHandler requestExHandler;

    @Around("annotationPointCut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method targetMethod = signature.getMethod();
        Power power = targetMethod.getAnnotation(Power.class);
        String powerValue = power.value();
        if(StringUtils.isNotEmpty(powerValue)){
            String[] needs = powerValue.split(",");//接口允许的角色
            List<String> needslist = new ArrayList<String>(Arrays.asList(needs));
            UserInfo userInfo = userService.simpleCurrentUser();
            List<String> powers = userInfo.getPowers();
            needslist.retainAll(powers);
            if(needslist.size()>0){
                return joinPoint.proceed();
            }else{
                //如果没有权限,抛出异常,由Spring框架捕获,跳转到错误页面
                return requestExHandler.LessPowerHandler(new LessPowerEx(LessPowerEx.TIP + powerValue));
            }
        }
        String methodName = signature.getMethod().getName();
        //System.out.println("方法名：" + methodName);
        return null;
    }

}