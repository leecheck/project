package com.gytech.Security;

import com.gytech.Security.ex.NotAdminAuthenticationException;
import com.gytech.Security.ex.RepeatedAuthenticationException;
import com.gytech.Security.ex.UsernameNotFoundAuthenticationException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by LQ on 2018/9/11.
 * Security
 */
@Component("customLoginFailureHandler")
public class CustomLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    protected final Log logger = LogFactory.getLog(this.getClass());
    private String defaultFailureUrl = "/login";

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String errorMsg = "";
        if(this.defaultFailureUrl == null) {
            this.logger.debug("No failure URL set, sending 401 Unauthorized error");
            response.sendError(401, "Authentication Failed: " + exception.getMessage());
        } else {
            if(exception instanceof BadCredentialsException){
                errorMsg = "login.login.disabled";
            }else if(exception.getCause() instanceof UsernameNotFoundAuthenticationException){
                errorMsg = "login.username.not.found";
            }else if(exception.getCause() instanceof NotAdminAuthenticationException){
                errorMsg = "login.user.not.admin";
            }else if(exception.getCause() instanceof LockedException){
                errorMsg = "login.user.locked";
            }else if (exception.getCause() instanceof RepeatedAuthenticationException){
                errorMsg = "login.user.already.login";
            }else if(exception instanceof SessionAuthenticationException) {
                if (exception.getMessage().contains("Maximum sessions of")){
                    errorMsg = "login.user.already.login";
                }else {
                    errorMsg = "login.user.session.authentication";
                }
            }else{
                errorMsg = "login.failure.default";
            }
            this.logger.debug("Redirecting to loginpage");
            request.getSession().setAttribute("login_msg", errorMsg);
            response.sendRedirect( request.getContextPath() + defaultFailureUrl);
        }
    }
}
