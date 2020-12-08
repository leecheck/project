package com.gytech.controller;

import com.gytech.Base.BaseController;
import com.gytech.LocalEntity.Res;
import com.gytech.Security.CustomUserService;
import com.gytech.Utils.MD5Util;
import com.gytech.entity.admin.SysMenu;
import com.gytech.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LQ on 2018/9/7.
 * controller
 */
@Controller
public class LoginController extends BaseController {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ISysUserService iSysUserService;
    @Autowired
    private ISysRoleService iSysRoleService;
    @Autowired
    private ISysMenuService iSysMenuService;
    @Autowired
    private ISysOrganizationService iSysOrganizationService;
    @Autowired
    private ISysAreaService iSysAreaService;
    @Autowired
    private ISysMaplayerService iSysMaplayerService;

    @Value("${homePage}")
    private String homePage;



    @RequestMapping(value = "/login")
    public Object login(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !"anonymousUser".equals(authentication.getPrincipal())) {
            return redirectTo(homePage);
        }
        String message = (String) request.getSession().getAttribute("login_msg");
        if (message != null)
            model.addAttribute("msg", getMessage(message));
        return "login";
    }



}
