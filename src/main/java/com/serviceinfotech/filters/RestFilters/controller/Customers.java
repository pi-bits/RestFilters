package com.serviceinfotech.filters.RestFilters.controller;

import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.ArrayList;
@JsonRootName(value = "customers")
public class Customers extends ArrayList<Customer> {
    public Customers() {
    }
}
