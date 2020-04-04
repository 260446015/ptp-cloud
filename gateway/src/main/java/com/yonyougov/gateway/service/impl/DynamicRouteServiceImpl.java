package com.yonyougov.gateway.service.impl;

import com.yonyougov.gateway.dto.GatewayRouteDefinition;
import com.yonyougov.gateway.entity.GatewayFilterDefinition;
import com.yonyougov.gateway.entity.GatewayPredicateDefinition;
import com.yonyougov.gateway.repository.GatewayFilterDefinitionRepository;
import com.yonyougov.gateway.repository.GatewayPredicateDefinitionRepository;
import com.yonyougov.gateway.repository.GatewayRouteDefinitionRepository;
import com.yonyougov.gateway.repository.RedisRouteDefinitionRepository;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author yindwe@yonyou.com
 * @Date 2020/4/1
 */
@Service
public class DynamicRouteServiceImpl implements ApplicationEventPublisherAware {
    private ApplicationEventPublisher publisher;
    @Resource
    private RouteDefinitionWriter routeDefinitionWriter;

    /**
     * 增加路由
     * @param gatewayRouteDefinition
     * @return
     */
    @Transactional
    public String add(GatewayRouteDefinition gatewayRouteDefinition) {
        RouteDefinition routeDefinition = assembleRouteDefinition(gatewayRouteDefinition);
        return saveRouteDefinition(routeDefinition);
    }

    /**
     * 更新路由
     * @param gatewayRouteDefinition
     * @return
     */
    @Transactional
    public String update(GatewayRouteDefinition gatewayRouteDefinition) {
        RouteDefinition definition = assembleRouteDefinition(gatewayRouteDefinition);
        try {
            this.routeDefinitionWriter.delete(Mono.just(definition.getId()));
        } catch (Exception e) {
            return "update fail,not find route  routeId: "+definition.getId();
        }
        try {
            return saveRouteDefinition(definition);
        } catch (Exception e) {
            return "update route  fail";
        }
    }

    /**
     * 删除路由
     * @param id
     * @return
     */
    @Transactional
    public String delete(String id) {
        try {
            this.routeDefinitionWriter.delete(Mono.just(id));
            return "delete success";
        } catch (Exception e) {
            e.printStackTrace();
            return "delete fail";
        }

    }
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    private RouteDefinition assembleRouteDefinition(GatewayRouteDefinition gatewayRouteDefinition) {
        RouteDefinition definition = new RouteDefinition();
        definition.setId(gatewayRouteDefinition.getId());
        // Predicates
        List<PredicateDefinition> predicateDefinitions = gatewayRouteDefinition.getPredicates().stream().map(gatewayPredicateDefinition -> {
            PredicateDefinition predicate = new PredicateDefinition();
            predicate.setArgs(gatewayPredicateDefinition.getArgs());
            predicate.setName(gatewayPredicateDefinition.getName());
            return predicate;
        }).collect(Collectors.toList());
        definition.setPredicates(predicateDefinitions);
        // Filters
        List<FilterDefinition> filterDefinitions = gatewayRouteDefinition.getFilters().stream().map(gatewayFilterDefinition -> {
            FilterDefinition filter = new FilterDefinition();
            filter.setArgs(gatewayFilterDefinition.getArgs());
            filter.setName(gatewayFilterDefinition.getName());
            return filter;
        }).collect(Collectors.toList());
        definition.setFilters(filterDefinitions);
        // URI
        URI uri = UriComponentsBuilder.fromUriString(gatewayRouteDefinition.getUri()).build().toUri();
        definition.setUri(uri);
        return definition;
    }

    private String saveRouteDefinition(RouteDefinition routeDefinition) {
        routeDefinitionWriter.save(Mono.just(routeDefinition)).subscribe();
        this.publisher.publishEvent(new RefreshRoutesEvent(this));
        return "success";
    }
}
