package com.serviceinfotech.filters.RestFilters.controller;

import com.serviceinfotech.filters.RestFilters.service.CustomerService;
import com.serviceinfotech.filters.RestFilters.service.PersistenceService;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class ControllerIT {

    @Autowired
    MockMvc mvc;

    @MockBean
    PersistenceService persistenceService;

    @MockBean
    CustomerService customerService;

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
       Customers expectedCustomers = new Customers();
        expectedCustomers.add(new Customer(1,"Prashant", "Naik"));
        expectedCustomers.add(new Customer(2,"Alberto", "Garcia"));
        expectedCustomers.add(new Customer(3,"Suresh", "Appavu"));
        Mockito.when(customerService.getCustomers()).thenReturn(expectedCustomers);

        mvc.perform(MockMvcRequestBuilders.get("/customers"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.customers[0].id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.customers[0].firstName").value("Prashant"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.customers[0].lastName").value("Naik"));

    }
}
