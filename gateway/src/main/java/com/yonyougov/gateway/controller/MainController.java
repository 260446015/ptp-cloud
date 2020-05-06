package com.yonyougov.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author yindwe@yonyou.com
 * @Date 2020/4/30
 */
@RestController
public class MainController {

    @GetMapping
    public String redirect(){
        return "index";
    }

    @GetMapping("login/oauth2/code/{registrationId}")
    public String redirect(@PathVariable String registrationId,String code){
        System.out.println(code);
        return registrationId;
    }
}
