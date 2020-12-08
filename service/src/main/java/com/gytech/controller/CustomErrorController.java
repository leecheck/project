package com.gytech.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by LQ on 2018/11/9.
 * com.gytech.controller
 */
@Controller
class CustomErrorController implements ErrorController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private HttpServletRequest request;


    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model){
        //logger.info(request.getRequestURI());
        //logger.info(request.getServletPath());
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
