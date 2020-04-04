package com.yonyougov.gateway.mapper;

import com.yonyougov.gateway.entity.GatewayFilterDefinition;
import org.apache.ibatis.annotations.Param;

/**
 * @Author yindwe@yonyou.com
 * @Date 2020/4/4
 */
public interface PtpFilterDefinitionMapper {
    int deleteByPrimaryKey(String id);

    int insert(GatewayFilterDefinition record);

    GatewayFilterDefinition selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GatewayFilterDefinition record);

    int deleteByRouteId(@Param("routeId") String routeId);
}