package com.gytech.Security.ex;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by LQ on 2018/9/11.
 * Security.ex
 */
public class UsernameNotFoundAuthenticationException extends AuthenticationException {
    public UsernameNotFoundAuthenticationException(String msg, Throwable t) {
        super(msg, t);
    }

    public UsernameNotFoundAuthenticationException(String msg) {
        super(msg);
    }
}