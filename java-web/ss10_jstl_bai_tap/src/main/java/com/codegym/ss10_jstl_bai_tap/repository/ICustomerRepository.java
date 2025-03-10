package com.codegym.ss10_jstl_bai_tap.repository;

import com.codegym.ss10_jstl_bai_tap.model.Customer;

import java.util.List;

public interface ICustomerRepository {
    List<Customer> findAll();
}
