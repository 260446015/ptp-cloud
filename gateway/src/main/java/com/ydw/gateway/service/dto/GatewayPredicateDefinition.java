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
public class GatewayPredicateDefinition implements Serializable {

    private static final long serialVersionUID = -5061761358560073451L;
    /**
     * 断言对应的Name
     */
    private String name;

    /**
     * 配置的断言规则
     */
    private Map<String, String> args = new LinkedHashMap<>();

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
