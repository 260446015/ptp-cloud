package com.yonyougov.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author yindwe@yonyou.com
 * @Date 2020/3/31
 */
@Component
@Slf4j
public class MyFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        if(!ctx.sendZuulResponse()){
            return false;
        }
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        log.info("zuulFilter starting...");
        RequestContext ctx = RequestContext.getCurrentContext();

        return null;
    }
}
