package com.yonyougov.oauth.config;

import com.alibaba.fastjson.JSON;
import com.yonyougov.oauth.enums.LoginTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author yindwe@yonyou.com
 * @Date 2020/5/18
 */

@Component("tigerAuthenticationSuccessHandler")
@Slf4j
public class TigerAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        log.info("登录成功");
        if(LoginTypeEnum.JSON.equals(securityProperties.getBrowser().getLoginType())){
            //返回json处理 默认也是json处理
            response.setContentType("application/json;charset=UTF-8");
            log.info("认证信息:"+ JSON.toJSONString(authentication));
            response.getWriter().write(JSON.toJSONString(authentication));
        } else {
            // 如果用户定义的是跳转，那么就使用父类方法进行跳转
            super.onAuthenticationSuccess(request, response, authentication);
        }

    }

}