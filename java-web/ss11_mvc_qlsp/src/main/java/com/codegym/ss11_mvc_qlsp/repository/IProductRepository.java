package com.codegym.ss11_mvc_qlsp.repository;

import com.codegym.ss11_mvc_qlsp.model.Product;
import java.util.List;

public interface IProductRepository {
    List<Product> findAll();
    boolean add(Product product);
    List<Product> searchByName(String name);
    boolean deleteById(int id);
}