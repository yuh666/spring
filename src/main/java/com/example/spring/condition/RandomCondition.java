package com.example.spring.condition;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.MultiValueMap;

import java.util.Map;
import java.util.Random;

/**
 * TODO:DOCUMENT ME!
 *
 * @author yuhao
 * @date 2020/4/6 11:18 上午
 */
public class RandomCondition implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext,
            AnnotatedTypeMetadata annotatedTypeMetadata) {
        Map<String, Object> attributes = annotatedTypeMetadata.getAnnotationAttributes(
                ConditionalOnRandom.class.getName());
        boolean real = new Random().nextBoolean();
        boolean expect = (Boolean) attributes.get("value");
        System.out.println("expect:" + expect + ",real:" + real);
        return expect == real;
    }
}
