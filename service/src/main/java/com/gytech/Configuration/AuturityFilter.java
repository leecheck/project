package com.gytech.Configuration;

import com.gytech.LocalEntity.Res;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by LQ on 2018/11/29.
 * com.gytech.Configuration
 */
@Order(2)
@WebFilter(filterName="AuturityFilter",urlPatterns="/admin/*")
public class AuturityFilter implements Filter {
    private Logger logger = LoggerFactory.getLogger(AuturityFilter.class);

    public static final String[] PERMITTED_RESOURCES = {"/**/api"};

    @Autowired
    private EnvContext envContext;

    @Value("${outer-filter}")
    private boolean enableFilter;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("AuturityFilter ==> init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (!enableFilter){
            chain.doFilter(request, response);
            return;
        }
        boolean isPermmited = false;
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String path = httpRequest.getRequestURI();
        if (path.contains("api")) {
            isPermmited = true;
        }
        if (envContext.isAdmin()) {
            isPermmited = true;
        }
        String requestType = httpRequest.getHeader("X-Requested-With");
        if (requestType!=null&&requestType.equalsIgnoreCase("XMLHttpRequest")){
            isPermmited = true;
            /*httpServletResponse.setCharacterEncoding("utf-8");
            try (PrintWriter out = httpServletResponse.getWriter()) {
                httpServletResponse.setContentType("application/json; charset=utf-8");
                Res res = new Res();
                out.print(res.data("请求权限不足"));
                out.flush();
            }*/
        }
        if (isPermmited) {
            chain.doFilter(request, response);
        }
        else {
            httpServletResponse.sendRedirect(httpRequest.getContextPath() + "/error/401");
        }
    }

    @Override
    public void destroy() {

    }
}