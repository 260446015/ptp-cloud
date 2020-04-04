package com.yonyougov.gateway.repository;

import com.yonyougov.common.UuidUtils;
import com.yonyougov.gateway.entity.GatewayFilterDefinition;
import com.yonyougov.gateway.entity.GatewayPredicateDefinition;
import com.yonyougov.gateway.entity.GatewayRouteDefinition;
import com.yonyougov.gateway.mapper.PtpFilterDefinitionMapper;
import com.yonyougov.gateway.mapper.PtpPredicateDefinitionMapper;
import com.yonyougov.gateway.mapper.PtpRouteDefinitionMapper;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author yindwe@yonyou.com
 * @Date 2020/4/4
 */
@Component
//@ConditionalOnMissingClass(value = {"com.yonyougov.gateway.repository.RedisRouteDefinitionRepository"})
public class JdbcRouteDefinitionRepository implements RouteDefinitionRepository {

    @Resource
    private PtpRouteDefinitionMapper ptpRouteDefinitionMapper;
    @Resource
    private PtpFilterDefinitionMapper ptpFilterDefinitionMapper;
    @Resource
    private PtpPredicateDefinitionMapper ptpPredicateDefinitionMapper;

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        List<GatewayRouteDefinition> gatewayRouteDefinitionList = ptpRouteDefinitionMapper.findAllWithCompose();
        List<RouteDefinition> routeDefinitionList = gatewayRouteDefinitionList.stream().map(gatewayRouteDefinition -> {
            RouteDefinition routeDefinition = new RouteDefinition();
            routeDefinition.setId(gatewayRouteDefinition.getId());
            routeDefinition.setUri(URI.create(gatewayRouteDefinition.getUri()));
            routeDefinition.setOrder(Integer.parseInt(gatewayRouteDefinition.getOrderNum()));
            List<GatewayPredicateDefinition> predicates = gatewayRouteDefinition.getPredicates();
            if(predicates != null && !predicates.isEmpty()){
                List<PredicateDefinition> predicateDefinitions = predicates.stream().map(gatewayPredicateDefinition -> {
                    PredicateDefinition predicateDefinition = new PredicateDefinition();
                    predicateDefinition.setName(gatewayPredicateDefinition.getName());
                    predicateDefinition.setArgs(mapStringToMap(gatewayPredicateDefinition.getPredicate()));
                    return predicateDefinition;
                }).collect(Collectors.toList());
                routeDefinition.setPredicates(predicateDefinitions);
            }
            List<GatewayFilterDefinition> gatewayRouteDefinitionFilters = gatewayRouteDefinition.getFilters();
            if(gatewayRouteDefinitionFilters != null && !gatewayRouteDefinitionFilters.isEmpty()){
                List<FilterDefinition> filterDefinitions = gatewayRouteDefinitionFilters.stream().map(gatewayFilterDefinition -> {
                    FilterDefinition filterDefinition = new FilterDefinition();
                    filterDefinition.setArgs(mapStringToMap(gatewayFilterDefinition.getFilter()));
                    return filterDefinition;
                }).collect(Collectors.toList());
                routeDefinition.setFilters(filterDefinitions);
            }
            return routeDefinition;
        }).collect(Collectors.toList());
        return Flux.fromIterable(routeDefinitionList);
    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return route.flatMap(r -> {
            if (StringUtils.isEmpty(r.getId())) {
                return Mono.error(new IllegalArgumentException("id may not be empty"));
            }
            String id = r.getId();
            int order = r.getOrder();
            String uri = r.getUri().toString();
            String routeId = UuidUtils.getCruxUUid().toString();
            GatewayRouteDefinition gatewayRouteDefinition = new GatewayRouteDefinition();
            gatewayRouteDefinition.setUri(uri).setId(id).setOrderNum(String.valueOf(order)).setRouteId(routeId);
            ptpRouteDefinitionMapper.insert(gatewayRouteDefinition);
            List<PredicateDefinition> predicates = r.getPredicates();
            predicates.forEach(predicateDefinition -> {
                GatewayPredicateDefinition gatewayPredicateDefinition = new GatewayPredicateDefinition();
                gatewayPredicateDefinition.setName(predicateDefinition.getName());
                gatewayPredicateDefinition.setPredicate(predicateDefinition.getArgs().toString());
                gatewayPredicateDefinition.setRouteId(routeId);
                ptpPredicateDefinitionMapper.insert(gatewayPredicateDefinition);
            });
            List<FilterDefinition> filters = r.getFilters();
            filters.forEach(filterDefinition -> {
                GatewayFilterDefinition gatewayFilterDefinition = new GatewayFilterDefinition();
                gatewayFilterDefinition.setName(filterDefinition.getName());
                gatewayFilterDefinition.setFilter(filterDefinition.getArgs().toString());
                gatewayFilterDefinition.setRouteId(routeId);
                ptpFilterDefinitionMapper.insert(gatewayFilterDefinition);
            });
            return Mono.empty();
        });
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return routeId.flatMap(id -> {
            if (ptpRouteDefinitionMapper.selectByPrimaryKey(id) != null) {
                ptpRouteDefinitionMapper.deleteByPrimaryKey(id);
                ptpPredicateDefinitionMapper.deleteByRouteId(id);
                ptpFilterDefinitionMapper.deleteByRouteId(id);
                return Mono.empty();
            }
            return Mono.defer(() -> Mono.error(new NotFoundException("路由文件没有找到: " + routeId)));
        });
    }

    private static Map<String,String> mapStringToMap(String str){
        str=str.substring(1, str.length()-1);
        String[] strs=str.split(",");
        Map<String,String> map = new HashMap<>();
        for (String string : strs) {
            String key=string.split("=")[0];
            String value=string.split("=")[1];
            map.put(key, value);
        }
        return map;
    }
}
