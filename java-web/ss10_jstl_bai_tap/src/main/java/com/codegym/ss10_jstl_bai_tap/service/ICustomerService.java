package com.codegym.ss10_jstl_bai_tap.service;
import com.codegym.ss10_jstl_bai_tap.model.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> findAll();
}