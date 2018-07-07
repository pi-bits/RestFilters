package com.serviceinfotech.filters.RestFilters.Interceptors;

import com.serviceinfotech.filters.RestFilters.config.Entity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ControllerInterceptor extends HandlerInterceptorAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerInterceptor.class);
    private Entity clientEntity;

    public ControllerInterceptor(Entity clientEntity) {
        this.clientEntity = clientEntity;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String clientName = request.getHeader("x-token");
        clientEntity.setApplicationID("Hello "+ clientName);
        return true;
    }

}
