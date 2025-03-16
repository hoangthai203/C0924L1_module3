package com.codegym.ss11_mvc_qlsp.service;

import com.codegym.ss11_mvc_qlsp.model.Product;
import com.codegym.ss11_mvc_qlsp.repository.IProductRepository;
import com.codegym.ss11_mvc_qlsp.repository.ProductRepository;

import java.util.List;

public class ProductService implements IProductService {
    private IProductRepository productRepository = new ProductRepository();

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public boolean add(Product product) {
        return productRepository.add(product);
    }

    @Override
    public boolean deleteById(int id) {
        return productRepository.deleteById(id);
    }

    @Override
    public List<Product> searchByName(String name) {
        return productRepository.searchByName(name);
    }

    // Thêm phương thức findById
    @Override
    public Product findById(int id) {
        return productRepository.findById(id); // Gọi phương thức trong Repository
    }

    @Override
    public boolean update(Product product) {
        return productRepository.update(product);
    }
}
