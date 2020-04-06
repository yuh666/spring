package com.example.spring.condition;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * TODO:DOCUMENT ME!
 *
 * @author yuhao
 * @date 2020/4/6 11:22 上午
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Conditional(value = {RandomCondition.class})
public @interface ConditionalOnRandom {

    boolean value() default false;
}
