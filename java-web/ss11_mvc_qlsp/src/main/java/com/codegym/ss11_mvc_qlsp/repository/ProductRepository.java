package com.codegym.ss11_mvc_qlsp.repository;

import com.codegym.ss11_mvc_qlsp.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository implements IProductRepository {
    private static List<Product> productList = new ArrayList<>();
    static {
        productList.add(new Product(1, "Laptop", 1000, "Gaming Laptop", "Dell"));
        productList.add(new Product(2, "Phone", 500, "Smartphone", "Samsung"));
    }

    @Override
    public List<Product> findAll() {
        // kết nối DB lấy dữ liêu lên

        return productList;
    }

    @Override
    public boolean add(Product product) {
        // kết nối DB để thêm dữ liệu vào
        return productList.add(product);
    }
}