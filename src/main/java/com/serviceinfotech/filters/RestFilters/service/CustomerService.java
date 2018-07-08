package com.serviceinfotech.filters.RestFilters.service;

import com.serviceinfotech.filters.RestFilters.controller.Customers;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class CustomerService {
    private RestTemplate restTemplate;

    public CustomerService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public Customers getCustomers() {
        return restTemplate.getForObject("http://localhost:8080/customers",Customers.class);
    }
}
