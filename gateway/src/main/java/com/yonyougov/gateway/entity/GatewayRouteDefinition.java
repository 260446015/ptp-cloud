package com.yonyougov.gateway.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author yindwe@yonyou.com
 * @Date 2020/4/1
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "ptp_route_definition")
public class GatewayRouteDefinition implements Serializable {

    private static final long serialVersionUID = 5193790178305685521L;
    /**
     * 路由的Id
     */
    @Id
    private String routeId;

    @Column(nullable = false)
    private String id;

    /**
     * 路由断言集合配置
     */
    @Transient
    private List<GatewayPredicateDefinition> predicates = new ArrayList<>();

    /**
     * 路由过滤器集合配置
     */
    @Transient
    private List<GatewayFilterDefinition> filters = new ArrayList<>();

    /**
     * 路由规则转发的目标uri
     */
    private String uri;

    /**
     * 路由执行的顺序
     */
    private String orderNum = "0";

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

}
