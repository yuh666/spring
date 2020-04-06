package com.example.spring.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

public class MyFilter1 implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("MyFilter1 init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
            FilterChain filterChain) throws IOException, ServletException {
        System.out.println("MyFilter1 filter prev");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("MyFilter1 filter post");
    }

    @Override
    public void destroy() {
        System.out.println("MyFilter1 destroy");
    }
}
