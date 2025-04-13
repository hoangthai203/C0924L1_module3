package com.codegym.furama.service;

import com.codegym.furama.model.Customer;
import java.util.List;

public interface ICustomerService {
    List<Customer> findAll();

    void save(Customer customer);
    Customer findById(String id);
    void update(Customer customer);
    void remove(String id);
    List<Customer> searchByNamePaging(String keyword, int offset, int limit);
    int countTotalCustomerByName(String keyword);
}
