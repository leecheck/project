package com.gytech.Security.aop;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.interfaces.Claim;
import com.gytech.Configuration.token.JwtUtil;
import com.gytech.Security.annotation.JwtUser;
import com.gytech.Security.entity.UserInfo;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by LQ on 2019/9/11.
 * com.gytech.Security.aop
 */
@Aspect
@Component
public class JwtUserAspect {

    @Value("${JWT.tokenKey}")
    private String tokenKey;

    @Pointcut(value = "@annotation(com.gytech.Security.annotation.JwtUser)")
    public void JwtUser() {
    }

    @Around("JwtUser()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        UserInfo userInfo = getJwtUser();
        Object[] params = joinPoint.getArgs();
        Integer paranIndex = getParamIndex(joinPoint);
        params[paranIndex] = userInfo;
        return joinPoint.proceed(params);
    }

    private UserInfo getJwtUser(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        String headerToken = request.getHeader(tokenKey);
        return JwtUtil.extrack(headerToken);
    }

    private Integer getParamIndex(JoinPoint joinPoint){
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();//方法名
        Object[] arguments = joinPoint.getArgs();//参数列表
        Integer index = -1;
        try {
            Class targetClass = Class.forName(targetName);
            Method[] methods = targetClass.getMethods();

            for (Method method : methods) {
                if (method.getName().equals(methodName)) {
                    LocalVariableTableParameterNameDiscoverer localVariableTableParameterNameDiscoverer =
                            new LocalVariableTableParameterNameDiscoverer();
                    String[] params = localVariableTableParameterNameDiscoverer.getParameterNames(method);
                    Annotation[][] annotations = method.getParameterAnnotations();
                    for (int i = 0; i < arguments.length; i++) {
                        Annotation[] annotations1 = annotations[i];
                        for (Annotation annotation : annotations1) {
                            if (annotation instanceof JwtUser) {
                                JwtUser jwtUser = (JwtUser) annotation;
                                if (jwtUser.user().equals(params[i])) {
                                    return i;
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {

        }
        return index;
    }
}
