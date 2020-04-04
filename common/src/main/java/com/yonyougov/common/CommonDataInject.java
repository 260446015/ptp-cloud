package com.yonyougov.common;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.Entity;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Calendar;

/**
 * @author yindwe@yonyu.com
 * @Date 2019/5/5 14:42
 * @Description
 */
@Component
@Aspect
@Slf4j
public class CommonDataInject {

    @Pointcut("execution(* com.yonyougov.*.mapper.*Mapper.insert*(..))")
    private void insertCutMethod() {
    }

    @Pointcut("execution(* com.yonyougov..*.controller..*.*(..))")
    private void timePoint() {
    }


    @Around(value = "timePoint()")
    public Object saveUserOperation(ProceedingJoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method targetMethod = methodSignature.getMethod();
        TimeAnnotation annotation = targetMethod.getAnnotation(TimeAnnotation.class);
        if (annotation != null) {
            long beginTime = System.currentTimeMillis();
            Object proceed = joinPoint.proceed();
            long endTime = System.currentTimeMillis();
            log.info("当前接口:{},执行时间为:{}", targetMethod.getName(), (endTime - beginTime));
            return proceed;
        }
        return joinPoint.proceed();
    }

    @Around("insertCutMethod()")
    public Object doInsertAround(ProceedingJoinPoint pjp) throws Throwable {
//        long cachedUid = uidGenService.getCachedUid();
//        System.out.println(cachedUid);
        Object[] args = pjp.getArgs();
        for (Object arg : args) {
            log.debug("[insert]" + arg);
            if (arg.getClass().getAnnotation(Entity.class) != null) {
                Field[] declaredFields = arg.getClass().getDeclaredFields();
                for (Field declaredField : declaredFields) {
                    if (declaredField.getName().equalsIgnoreCase("ts")) {
                        declaredField.setAccessible(true);
                        declaredField.set(arg, Calendar.getInstance().getTime());
                    } else if (declaredField.getName().equalsIgnoreCase("dr")) {
                        declaredField.setAccessible(true);
                        declaredField.set(arg, "N");
                    } else if (declaredField.getName().equalsIgnoreCase("id")) {
                        Object getId = arg.getClass().getDeclaredMethod("getId", null).invoke(arg, null);
                        if (StringUtils.isEmpty(getId)) {
                            declaredField.setAccessible(true);
                            declaredField.set(arg, UuidUtils.getCruxUUid().toString());
                        }
                    }
                }
            }
        }
        return pjp.proceed();
    }

}
