package com.codegym.furama.service;

import com.codegym.furama.model.Employee;
import com.codegym.furama.repository.EmployeeRepository;
import com.codegym.furama.repository.IEmployeeRepository;

import java.util.List;

public class EmployeeService implements IEmployeeService {
    private final IEmployeeRepository employeeRepository = new EmployeeRepository();

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public Employee findById(String id) {
        return employeeRepository.findById(id);
    }

    @Override
    public void update(Employee employee) {
        employeeRepository.update(employee);
    }

    @Override
    public void remove(String id) {
        employeeRepository.remove(id);
    }
}
