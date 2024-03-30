package com.study.config;

import com.study.interceptor.LoggerInterceptor;
import com.study.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new LoggerInterceptor())
                .order(1)
                .excludePathPatterns("/css/**", "/images/**", "/js/**");

        registry.addInterceptor(new LoginCheckInterceptor())
                .order(2)
                .excludePathPatterns("/css/**", "/images/**", "/js/**", "/log*", "/member-count", "/members/**");
    }

}
