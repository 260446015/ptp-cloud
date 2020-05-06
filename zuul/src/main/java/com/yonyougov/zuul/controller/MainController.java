package com.yonyougov.zuul.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author yindwe@yonyou.com
 * @Date 2020/4/30
 */
@RestController
public class MainController {

    @GetMapping
    public ModelAndView redirect(){
        return new ModelAndView("index");
    }
}
