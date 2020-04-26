package com.yonyougov.springsecruity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author yindwe@yonyou.com
 * @Date 2020/4/24
 */
@RestController
@RequestMapping("test")
public class TestController  {
    @GetMapping
    public String test(String str){
        return str;
    }
}
