package com.gytech.Security.ex;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by LQ on 2018/9/11.
 * Security.ex
 */
public class NotAdminAuthenticationException extends AuthenticationException {
    public NotAdminAuthenticationException(String msg, Throwable t) {
        super(msg, t);
    }

    public NotAdminAuthenticationException(String msg) {
        super(msg);
    }
}