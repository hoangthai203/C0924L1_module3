package com.codegym.ss11_mvc_qlsp.service;

import com.codegym.ss11_mvc_qlsp.model.Product;
import com.codegym.ss11_mvc_qlsp.repository.IProductRepository;
import com.codegym.ss11_mvc_qlsp.repository.ProductRepository;

import java.util.List;

public class ProductService implements IProductService {
    // xử lý nghiệp vụ:
    private IProductRepository productRepository = new ProductRepository();
    @Override
    public List<Product> findAll() {
        // không cần xử ly nghiệp vụ => gọi repository => lây list
        // gọi repository ( lấy dữ liệu)
        return productRepository.findAll();
    }

    @Override
    public boolean add(Product product) {
        // kiểm tra tính hợp lệ dữ liêu trước khi thêm mới vào csdl
        // validate cho này
        productRepository.add(product);
        return true;
    }
        @Override
        public List<Product> searchByName(String name) {

            return productRepository.searchByName(name);
        }

        @Override
        public boolean deleteById(int id) {
            return productRepository.deleteById(id);
    }

}

