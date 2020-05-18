package com.yonyougov.oauth.config;

import com.yonyougov.oauth.enums.LoginTypeEnum;

/**
 * @Author yindwe@yonyou.com
 * @Date 2020/5/18
 */
public class BrowserProperties {
    /**
     * 登录页面 不配置默认标准登录界面
     */
    private String loginPage = "/login";
    /**
     * 跳转类型 默认返回json数据
     */
    private LoginTypeEnum loginType = LoginTypeEnum.JSON;


    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public LoginTypeEnum getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginTypeEnum loginType) {
        this.loginType = loginType;
    }

}
