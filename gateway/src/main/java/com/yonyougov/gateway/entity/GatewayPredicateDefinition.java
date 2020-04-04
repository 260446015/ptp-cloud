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
@Entity
@Table(name = "ptp_predicate_definition")
public class GatewayPredicateDefinition implements Serializable {

    private static final long serialVersionUID = -5061761358560073451L;

    @Id
    private String id;
    /**
     * 断言对应的Name
     */
    private String name;

    private String routeId;

    /**
     * 配置的断言规则(改成一一对应)
     */
    private String predicate;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
