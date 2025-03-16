package com.codegym.ss11_mvc_qlsp.service;

import com.codegym.ss11_mvc_qlsp.model.Product;
import java.util.List;

public interface IProductService {
    List<Product> findAll();
    boolean add(Product product);
    boolean deleteById(int id);
    List<Product> searchByName(String name);
    Product findById(int id);
    boolean update(Product product);
}
