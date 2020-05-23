package com.yonyougov.oauth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author yindwe@yonyou.com
 * @Date 2020/5/23
 */
@Controller
public class BaseMainController {

    @GetMapping("/auth/login")
    public String loginPage(Model model){
        model.addAttribute("loginProcessUrl","/ptp-auth/authentication/base");
        return "base-login";
    }
}
