package com.gytech.Security.ex;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by LQ on 2018/9/11.
 * Security.ex
 */
public class RepeatedAuthenticationException extends AuthenticationException {
    public RepeatedAuthenticationException(String msg, Throwable t) {
        super(msg, t);
    }

    public RepeatedAuthenticationException(String msg) {
        super(msg);
    }
}