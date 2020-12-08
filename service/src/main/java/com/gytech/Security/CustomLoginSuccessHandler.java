package com.gytech.Security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by LQ on 2018/11/9.
 * com.gytech.Security
 */
@Component("customLoginSuccessHandler")
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Value("${homePage}")
    private String homePage;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        response.sendRedirect( request.getContextPath() + "/" + homePage);
    }

}