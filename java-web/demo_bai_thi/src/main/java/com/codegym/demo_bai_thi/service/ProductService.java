package com.codegym.demo_bai_thi.service;

import com.codegym.demo_bai_thi.model.Product;
import com.codegym.demo_bai_thi.repository.IProductRepository;
import com.codegym.demo_bai_thi.repository.ProductRepository;

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
    public boolean deleteById(String productId) {
        return productRepository.deleteById(productId);
    }

    @Override
    public List<Product> searchProducts(String name, String category) {
        return productRepository.searchProducts(name, category);
    }

    @Override
    public List<String> getAllCategories() {
        return productRepository.getAllCategories();
    }

    @Override
    public List<String> getAllUnits() {
        return productRepository.getAllUnits();
    }
}

