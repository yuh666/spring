package com.example.spring.condition;

import org.springframework.context.annotation.Bean;

/**
 * TODO:DOCUMENT ME!
 *
 * @author yuhao
 * @date 2020/4/6 11:26 上午
 */
@ConditionalOnRandom(value = true)
public class XXAutoConfiguration {

    @Bean
    public Object obj() {
        System.out.println("random init");
        return new Object();
    }
}
