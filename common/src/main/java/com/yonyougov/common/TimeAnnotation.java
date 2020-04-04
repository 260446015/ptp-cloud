package com.yonyougov.common;

import java.lang.annotation.*;

/**
 * @Author yindwe@yonyou.com
 * @Date 2019/8/23
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TimeAnnotation {
}
