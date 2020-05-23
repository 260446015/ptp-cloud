package com.yonyougov.oauth.controller;

import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author yindwe@yonyou.com
 * @Date 2020/5/23
 */
@Controller
// 必须配置
//@SessionAttributes("authorizationRequest")
public class BootGrantController {

    @RequestMapping("/custom/confirm_access")
    public ModelAndView getAccessConfirmation(HttpServletRequest request) throws Exception {
        ModelAndView view = new ModelAndView();
        view.setViewName("base-grant");
        view.addObject("clientId", request.getParameter("client_id"));
        return view;
    }
}
