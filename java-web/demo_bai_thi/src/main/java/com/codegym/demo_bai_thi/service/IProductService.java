package com.codegym.demo_bai_thi.service;

import com.codegym.demo_bai_thi.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll();
    boolean add(Product product);
    boolean deleteById(String productId);
    List<Product> searchProducts(String name, String category);
    List<String> getAllCategories();
    List<String> getAllUnits();
}
