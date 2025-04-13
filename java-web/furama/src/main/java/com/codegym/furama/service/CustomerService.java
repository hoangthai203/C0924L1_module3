package com.codegym.furama.service;

import com.codegym.furama.model.Customer;
import com.codegym.furama.repository.CustomerRepository;
import com.codegym.furama.repository.ICustomerRepository;

import java.util.List;

public class CustomerService implements ICustomerService {
    private ICustomerRepository customerRepository = new CustomerRepository();

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public Customer findById(String id) {
        return customerRepository.findById(id);
    }

    @Override
    public void update(Customer customer) {
        customerRepository.update(customer);
    }

    @Override
    public void remove(String id) {
        customerRepository.remove(id);
    }

    @Override
    public List<Customer> searchByNamePaging(String keyword, int offset, int limit) {
        return customerRepository.searchByNamePaging(keyword, offset, limit);
    }

    @Override
    public int countTotalCustomerByName(String keyword) {
        return customerRepository.countTotalCustomerByName(keyword);
    }
}
