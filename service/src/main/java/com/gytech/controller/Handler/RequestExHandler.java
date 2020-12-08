package com.gytech.controller.Handler;

import com.gytech.LocalEntity.Res;
import com.gytech.LocalEntity.ResultInfo;
import com.gytech.Security.ex.LessPowerEx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by LQ on 2019/9/5.
 * com.gytech.controller
 */
@RestControllerAdvice
public class RequestExHandler {

    protected Logger logger= LoggerFactory.getLogger(getClass());

    @ExceptionHandler(value = LessPowerEx.class)
    @ResponseBody()
    public Res LessPowerHandler(LessPowerEx e)  {
        Res res = new Res();
        return res.reason(e.getMessage());
    }
}
