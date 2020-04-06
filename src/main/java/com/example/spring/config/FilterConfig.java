package com.example.spring.config;

import com.example.spring.filter.MyFilter1;
import com.example.spring.filter.MyFilter2;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Collections;

@Configuration
public class FilterConfig {


    @Bean
    public FilterRegistrationBean<MyFilter1> registerMyFilter1() {
        FilterRegistrationBean<MyFilter1> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.setFilter(new MyFilter1());
        filterRegistrationBean.setUrlPatterns(Collections.singletonList("/*"));
        return filterRegistrationBean;
    }


    @Bean
    public FilterRegistrationBean<MyFilter2> registerMyFilter2() {
        FilterRegistrationBean<MyFilter2> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setOrder(2);
        filterRegistrationBean.setFilter(new MyFilter2());
        filterRegistrationBean.setUrlPatterns(Collections.singletonList("/*"));
        return filterRegistrationBean;
    }
}
