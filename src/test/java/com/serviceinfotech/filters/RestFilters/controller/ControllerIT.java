package com.serviceinfotech.filters.RestFilters.controller;

import com.serviceinfotech.filters.RestFilters.service.PersistenceService;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import sun.plugin2.main.server.ServerPrintHelper;


@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class ControllerIT {

    @Autowired
    MockMvc mvc;

    @MockBean
    PersistenceService persistenceService;

    @Test
    public void shouldReturnMessage() throws Exception {
        String requestedValue = "Prashant";
        mvc.perform(MockMvcRequestBuilders.get("/message").header("x-token", requestedValue))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Is.is("Hello " +requestedValue)));

       Mockito.verify(persistenceService).update();
    }

    @Test
    public void shouldReturnListOfCustomers() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/customers"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.customers[0].id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.customers[0].firstName").value("Prashant"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.customers[0].lastName").value("Naik"));

    }
}
