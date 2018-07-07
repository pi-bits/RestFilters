package com.serviceinfotech.filters.RestFilters.controller;

import com.serviceinfotech.filters.RestFilters.config.Entity;
import com.serviceinfotech.filters.RestFilters.service.PersistenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    private static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);

    @Autowired
    PersistenceService persistenceService;
    @Autowired
    Entity clientEntity;

    @GetMapping("/message")
    public String getMessage(@RequestHeader(value = "x-token", required = true) String xToken) {
        LOGGER.info(clientEntity.getApplicationID());
        persistenceService.update();
        return clientEntity.getApplicationID();
    }
}
