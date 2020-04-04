package com.yonyougov.gateway.controller;

import com.yonyougov.gateway.dto.GatewayFilterDefinition;
import com.yonyougov.gateway.dto.GatewayPredicateDefinition;
import com.yonyougov.gateway.dto.GatewayRouteDefinition;
import com.yonyougov.gateway.service.impl.DynamicRouteServiceImpl;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author yindwe@yonyou.com
 * @Date 2020/4/1
 */
@RestController
@RequestMapping("route")
public class GatewayController {

    @Resource
    private DynamicRouteServiceImpl dynamicRouteService;

    /**
     * 增加路由
     * @param gatewayRouteDefinition
     * @return
     */
    @PostMapping("/add")
    public String add(@RequestBody GatewayRouteDefinition gatewayRouteDefinition) {
        try {
            return this.dynamicRouteService.add(gatewayRouteDefinition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "succss";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        return this.dynamicRouteService.delete(id);
    }

    @PostMapping("/update")
    public String update(@RequestBody GatewayRouteDefinition gatewayRouteDefinition) {
        return this.dynamicRouteService.update(gatewayRouteDefinition);
    }
}
