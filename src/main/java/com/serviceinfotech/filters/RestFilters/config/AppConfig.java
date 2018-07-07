package com.serviceinfotech.filters.RestFilters.config;

import com.serviceinfotech.filters.RestFilters.Interceptors.ControllerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class AppConfig implements WebMvcConfigurer {

    @Autowired
    ControllerInterceptor controllerInterceptor;


    @Bean(name="clientEntity")
    @Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public Entity clientEntity() {
        return new Entity();
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(controllerInterceptor).
                addPathPatterns("/**/message/**/");;
    }
}
