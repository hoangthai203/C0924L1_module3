package com.codegym.ss10_jstl_bai_tap.service;

import com.codegym.ss10_jstl_bai_tap.model.Customer;
import com.codegym.ss10_jstl_bai_tap.repository.CustomerRepository;
import com.codegym.ss10_jstl_bai_tap.repository.ICustomerRepository;

import java.util.List;

public class CustomerService implements ICustomerService {
    private ICustomerRepository customerRepository = new CustomerRepository();

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }
}
