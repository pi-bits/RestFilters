package com.serviceinfotech.filters.RestFilters.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.serviceinfotech.filters.RestFilters.controller.Customer;
import com.serviceinfotech.filters.RestFilters.controller.Customers;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CustomerServiceIT {

    @Autowired
    CustomerService customerService;

    @Autowired
    ObjectMapper objectMapper;

    Customers expectedCustomers;

    @Before
    public void setUp() throws Exception {
        expectedCustomers = new Customers();
        expectedCustomers.add(new Customer(1,"Prashant", "Naik"));
        expectedCustomers.add(new Customer(2,"Alberto", "Garcia"));
        expectedCustomers.add(new Customer(3,"Suresh", "Appavu"));
        JsonNode response = objectMapper.valueToTree(expectedCustomers);

        stubFor(WireMock.get(WireMock.urlEqualTo("/customers"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(response.toString())));


    }
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8080);




    @Test
    public void shouldGetCustomerList() throws Exception {
        Customers actualCustomers = customerService.getCustomers();
        assertThat(actualCustomers.get(0)).isEqualToComparingFieldByFieldRecursively(expectedCustomers.get(0));
        assertThat(actualCustomers.get(1)).isEqualToComparingFieldByFieldRecursively(expectedCustomers.get(1));
        assertThat(actualCustomers.get(2)).isEqualToComparingFieldByFieldRecursively(expectedCustomers.get(2));
        assertThat(expectedCustomers).extracting("firstName").contains("Prashant","Alberto","Suresh");
        assertThat(expectedCustomers).extracting("lastName").contains("Naik","Garcia","Appavu");
        assertThat(expectedCustomers).extracting("id").contains(1,2,3);


    }
}