package com.gytech.controller.Handler;

import com.gytech.Base.R;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by LQ on 2018/11/9.
 * com.tianren.sy.controller
 */
@ControllerAdvice
class CustomErrorHandler implements ErrorController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private HttpServletRequest request;

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public R defaultException(HttpServletRequest request, Exception e){
        return R.error(HttpStatus.SC_INTERNAL_SERVER_ERROR,e.getMessage());
    }

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model){
        logger.info(request.getRequestURI());
        logger.info(request.getServletPath());
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        model.addAttribute("msg",statusCode);
        return "error";
    }

    @RequestMapping("/error/401")
    public String handle401(HttpServletRequest request, Model model){
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        model.addAttribute("msg","未授权");
        return "error";
    }

    @RequestMapping("/error/invalidSession")
    public String invalidSession(HttpServletRequest request, Model model){
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        model.addAttribute("msg","登录超时");
        return "error";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
