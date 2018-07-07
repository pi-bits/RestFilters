package com.serviceinfotech.filters.RestFilters.controller;

import org.hamcrest.core.Is;
import org.junit.Test;

import static org.junit.Assert.*;

public class ControllerTest {


    @Test
    public void shouldReturnMessage() throws Exception {
        Controller controller = new Controller();
        assertThat("Hello Prashant", Is.is(controller.getMessage("Prashant")));
    }
}