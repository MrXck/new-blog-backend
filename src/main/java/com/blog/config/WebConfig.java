package com.blog.config;

import com.blog.interceptor.RateLimitInterceptor;
import com.blog.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final TokenInterceptor tokenInterceptor;

    public WebConfig(TokenInterceptor tokenInterceptor) {
        this.tokenInterceptor = tokenInterceptor;
    }

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
        registry.addInterceptor(this.tokenInterceptor).addPathPatterns(path).excludePathPatterns(exclude);
    }
}