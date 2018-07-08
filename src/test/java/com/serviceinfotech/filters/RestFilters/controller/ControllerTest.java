package com.serviceinfotech.filters.RestFilters.controller;

import com.serviceinfotech.filters.RestFilters.config.Entity;
import com.serviceinfotech.filters.RestFilters.service.PersistenceService;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ControllerTest {

    @Mock
    Entity entity;

    @Mock
    PersistenceService persistenceService;

    @InjectMocks
    Controller controller;


    @Test
    public void shouldReturnMessage() throws Exception {
        Mockito.when(entity.getApplicationID()).thenReturn("Hello Prashant");
        assertThat("Hello Prashant", Is.is(controller.getMessage("Prashant")));
    }
}