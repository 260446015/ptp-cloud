package com.ydw.gateway.service.dto;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author yindwe@yonyou.com
 * @Date 2020/4/1
 */
@Data
@Accessors(chain = true)
public class GatewayFilterDefinition implements Serializable {

    private static final long serialVersionUID = -2979727256580670714L;
    /**
     * Filter Name
     */
    private String name;

    /**
     * 对应的路由规则
     */
    private Map<String, String> args = new LinkedHashMap<>();

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
