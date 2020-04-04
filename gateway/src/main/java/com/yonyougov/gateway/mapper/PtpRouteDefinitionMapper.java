package com.yonyougov.gateway.mapper;

import com.yonyougov.gateway.entity.GatewayRouteDefinition;
import java.util.List;

/**
 * @Author yindwe@yonyou.com
 * @Date 2020/4/4
 */
public interface PtpRouteDefinitionMapper {
    int deleteByPrimaryKey(String routeId);

    int insert(GatewayRouteDefinition record);

    GatewayRouteDefinition selectByPrimaryKey(String routeId);

    int updateByPrimaryKeySelective(GatewayRouteDefinition record);

    List<GatewayRouteDefinition> findAllWithCompose();
}