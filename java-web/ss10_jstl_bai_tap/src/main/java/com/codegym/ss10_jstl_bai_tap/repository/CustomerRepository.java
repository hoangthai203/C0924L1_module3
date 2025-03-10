package com.codegym.ss10_jstl_bai_tap.repository;

import com.codegym.ss10_jstl_bai_tap.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerRepository implements ICustomerRepository {
    private static List<Customer> customerList = new ArrayList<>();
    static {
        customerList.add(new Customer(1, "Nguyễn Văn A", "1990-05-12", "Hà Nội", "images/customer1.png"));
        customerList.add(new Customer(2, "Trần Thị B", "1985-09-23", "Hồ Chí Minh", "images/customer2.png"));
        customerList.add(new Customer(3, "Lê Văn C", "1992-12-15", "Đà Nẵng", "images/customer3.png"));
        customerList.add(new Customer(3, "Lê Văn D", "1992-12-15", "Đà Nẵng", "images/customer4.png"));
        customerList.add(new Customer(3, "Lê Văn Hoàng E", "1992-12-15", "Đà Nẵng", "images/customer5.png"));
    }

    @Override
    public List<Customer> findAll() {
        return customerList;
    }
}

