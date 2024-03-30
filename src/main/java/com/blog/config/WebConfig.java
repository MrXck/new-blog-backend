package com.blog.config;

import com.blog.interceptor.RateLimitInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] path = {
                "/**",
        };
        String[] exclude = {
                "/js/**",
                "/img/**",
                "/css/**",
                "/swagger*/**",
        };
        registry.addInterceptor(new RateLimitInterceptor()).addPathPatterns(path).excludePathPatterns(exclude);
    }
}