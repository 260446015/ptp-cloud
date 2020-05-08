package com.yonyougov.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author yindwe@yonyou.com
 * @Date 2020/4/30
 */
@RestController
@RequestMapping("gateway-redirect")
public class MainController {

//    @GetMapping
//    public String redirect(){
//        return "index";
//    }

    @GetMapping
    public String redirect(String code){
        System.out.println(code);
        return code;
    }
}
