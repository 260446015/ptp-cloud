package com.yonyougov.gateway.dto;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author yindwe@yonyou.com
 * @Date 2020/4/1
 */
@Data
@Accessors(chain = true)
public class GatewayRouteDefinition implements Serializable {

    private static final long serialVersionUID = 5193790178305685521L;
    /**
     * 路由的Id
     */
    private String id;

    /**
     * 路由断言集合配置
     */
    private List<GatewayPredicateDefinition> predicates = new ArrayList<>();

    /**
     * 路由过滤器集合配置
     */
    private List<GatewayFilterDefinition> filters = new ArrayList<>();

    /**
     * 路由规则转发的目标uri
     */
    private String uri;

    /**
     * 路由执行的顺序
     */
    private int order = 0;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
