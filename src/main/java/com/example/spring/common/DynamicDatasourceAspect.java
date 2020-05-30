package com.example.spring.common;


import com.example.spring.enums.DatasourceEnum;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;


@Component
@Order(-1)
@Aspect
public class DynamicDatasourceAspect {

    @Pointcut("execution(* com.example.spring.dao..*(..))")
    public void method() {

    }

    @Around("method()")
    public Object determineDatasource(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        TargetDatasource annotation = method.getAnnotation(TargetDatasource.class);
        DatasourceEnum value = annotation.value();
        DatasourceHolder.set(value);
        return pjp.proceed();
    }
}
