package com.yonyougov.oauth.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author yindwe@yonyou.com
 * @Date 2020/5/18
 */

@ConfigurationProperties(value = "ptp-auth.auth",ignoreInvalidFields = true)
public class SecurityProperties {

    /**
     * 浏览器配置类
     */
    private BrowserProperties browser = new BrowserProperties();

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }

}
