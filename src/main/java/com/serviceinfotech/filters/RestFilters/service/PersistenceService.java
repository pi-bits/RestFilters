package com.serviceinfotech.filters.RestFilters.service;

import com.serviceinfotech.filters.RestFilters.config.Entity;
import com.serviceinfotech.filters.RestFilters.controller.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersistenceService {
    @Autowired
    Entity clientEntity;
    private static final Logger LOGGER = LoggerFactory.getLogger(PersistenceService.class);
    public void update() {
        LOGGER.info("Message is " + clientEntity.getApplicationID());
    }
}
