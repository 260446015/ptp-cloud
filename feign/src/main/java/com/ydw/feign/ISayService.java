package com.ydw.feign;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author yindwe@yonyou.com
 * @Date 2020/3/26
 */
@FeignClient("client")
public interface ISayService {
}
