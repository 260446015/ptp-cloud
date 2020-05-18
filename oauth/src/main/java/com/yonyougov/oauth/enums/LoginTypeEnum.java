package com.yonyougov.oauth.enums;

import com.alibaba.fastjson.JSON;
import lombok.Getter;

/**
 * @Author yindwe@yonyou.com
 * @Date 2020/5/18
 */
@Getter
public enum  LoginTypeEnum {

    /**
     * json数据返回
     */
    JSON,
    /**
     * 重定向
     */
    REDIRECT;
}
