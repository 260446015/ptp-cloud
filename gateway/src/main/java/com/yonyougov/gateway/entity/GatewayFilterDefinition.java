package com.yonyougov.gateway.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author yindwe@yonyou.com
 * @Date 2020/4/1
 */
@Data
@Accessors(chain = true)
@Table(name = "ptp_filter_definition")
@Entity
public class GatewayFilterDefinition implements Serializable {

    private static final long serialVersionUID = -2979727256580670714L;
    @Id
    private String id;
    /**
     * Filter Name
     */
    private String name;

    /**
     * 对应的路由规则(改成一一对应)
     */
//    private Map<String, String> args = new LinkedHashMap<>();
    private String filter;

    private String routeId;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
