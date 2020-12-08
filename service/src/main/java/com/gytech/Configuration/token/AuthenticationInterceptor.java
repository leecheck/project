package com.gytech.Configuration.token;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.interfaces.Claim;
import com.gytech.Base.BaseLogger;
import com.gytech.Base.R;
import com.gytech.LocalEntity.ResultInfo;
import com.gytech.controller.Handler.RequestExHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * token
 */
public class AuthenticationInterceptor extends BaseLogger implements HandlerInterceptor {

    private static final String OPTIONS = "OPTIONS";

    @Autowired
    private RequestExHandler requestExHandler;

    @Value("${JWT.tokenKey}")
    private String tokenKey;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) {

        //对于options的请求不进行token检测即可
        if (OPTIONS.equals(httpServletRequest.getMethod())) {
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
        // 从 http 请求头中取出 token
        String token = httpServletRequest.getHeader(tokenKey);
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        //检查是否有用户权限的注解，有则认证
        if (method.isAnnotationPresent(NeedToken.class)) {
            NeedToken needToken = method.getAnnotation(NeedToken.class);
            if (needToken.required()) {
                // 执行认证
                if (token == null) {
                    printJson(httpServletResponse,ResultInfo.ERROR_TOKENNULL, ResultInfo.INFO_TOKENNULL);
                    return false;
                }
                try {
                    Map<String, Claim> claimMap = JwtUtil.verifyToken(token);
                } catch (RuntimeException e) {
                    printJson(httpServletResponse,ResultInfo.ERROR_TOKENINVALID,ResultInfo.INFO_TOKENINVALID);
                    return false;
                }
                return true;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) {
    }

    private static void printJson(HttpServletResponse response, Integer code,String msg) {
        String content = JSON.toJSONString(R.error(code,msg));
        printContent(response, content);
    }


    private static void printContent(HttpServletResponse response, String content) {
        PrintWriter pw = null;
        try {
            response.setContentType("text/html; charset=utf-8");
            response.setCharacterEncoding("UTF-8");
            pw = response.getWriter();
            pw.print(content);
            pw.flush();
        } catch (IOException e) {
        } finally {
            if (pw != null)
                pw.close();
        }
    }
}
