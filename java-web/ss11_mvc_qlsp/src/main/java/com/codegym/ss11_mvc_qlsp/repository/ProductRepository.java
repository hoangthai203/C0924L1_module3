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
        System.out.println("Số lượng sản phẩm trong list: " + productList.size());
        return productList;

    }

    @Override
    public List<Product> searchByName(String name) {
        List<Product> searchList = new ArrayList<>();
        for (Product s : productList) {
            if (s.getName().contains(name)){
                searchList.add(s);
            }
        }
        return searchList;
    }

    @Override
    public boolean add(Product product) {
        // kết nối DB để thêm dữ liệu vào
        return productList.add(product);

    }

    @Override
    public boolean deleteById(int id) {
        for (int i = 0; i <productList.size() ; i++) {
            if (productList.get(i).getId()==id){
                productList.remove(i);
                break;
            }
        }
        return true;
    }
}


