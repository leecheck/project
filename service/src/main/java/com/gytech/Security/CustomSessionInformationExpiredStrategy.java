package com.gytech.Security;

import com.gytech.LocalEntity.Res;
import com.gytech.Utils.GU;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by LQ on 2018/11/30.
 * com.gytech.Security
 */
@Component
public class CustomSessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {

    private static String loginPath = "/login";
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        HttpServletResponse response = event.getResponse();
        HttpServletRequest request = event.getRequest();
        if (GU.isAjax(request)){
            response.setCharacterEncoding("utf-8");
            try (PrintWriter out = response.getWriter()) {
                response.setContentType("application/json; charset=utf-8");
                Res res = new Res();
                out.print(res.data("登录信息失效"));
                out.flush();
            }
        }else {
            response.sendRedirect(request.getContextPath() + loginPath);
        }
    }
}
