package com.codegym.furama.service;

import com.codegym.furama.model.Customer;
import com.codegym.furama.model.Employee;
import java.util.List;

public interface IEmployeeService {
    List<Employee> findAll();
    void save(Employee employee);
    Employee findById(String id);
    void update(Employee employee);
    void remove(String id);
    List<Employee> searchByNamePaging(String keyword, int offset, int limit);
    int countTotalEmployeeByName(String keyword);
}
