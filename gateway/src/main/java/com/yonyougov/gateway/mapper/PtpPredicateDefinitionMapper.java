package com.yonyougov.gateway.mapper;

import com.yonyougov.gateway.entity.GatewayPredicateDefinition;
import org.apache.ibatis.annotations.Insert;import org.apache.ibatis.annotations.Param;

/**
 * @Author yindwe@yonyou.com
 * @Date 2020/4/4
 */
public interface PtpPredicateDefinitionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GatewayPredicateDefinition record);

    GatewayPredicateDefinition selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GatewayPredicateDefinition record);

    int deleteByRouteId(@Param("routeId") String routeId);
}