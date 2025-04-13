package com.codegym.demo_bai_thi.repository;

import com.codegym.demo_bai_thi.model.Product;
import com.codegym.demo_bai_thi.util.BaseRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductRepository implements IProductRepository {
    private final String SELECT_ALL = "SELECT * FROM products";
    private final String INSERT_INTO = "INSERT INTO products(productId, name, unit, price, category) VALUES (?, ?, ?, ?, ?)";
    private final String DELETE_BY_ID = "DELETE FROM products WHERE productId = ?";
    private final String SELECT_ALL_CATEGORIES = "SELECT category FROM product_categories";

    @Override
    public List<Product> findAll() {
        List<Product> productList = new ArrayList<>();
        Connection connection = BaseRepository.getConnectDB();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String productId = resultSet.getString("productId");
                String name = resultSet.getString("name");
                String unit = resultSet.getString("unit");
                double price = resultSet.getDouble("price");
                String category = resultSet.getString("category");
                Product product = new Product(productId, name, unit, price, category);
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return productList;
    }

    @Override
    public boolean add(Product product) {
        Connection connection = BaseRepository.getConnectDB();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO);
            preparedStatement.setString(1, product.getProductId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setString(3, product.getUnit());
            preparedStatement.setDouble(4, product.getPrice());
            preparedStatement.setString(5, product.getCategory());

            int effectRow = preparedStatement.executeUpdate();
            return effectRow == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean deleteById(String productId) {
        Connection connection = BaseRepository.getConnectDB();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID);
            preparedStatement.setString(1, productId);
            int effectRow = preparedStatement.executeUpdate();
            return effectRow == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public List<Product> searchProducts(String name, String category) {
        List<Product> productList = new ArrayList<>();
        Connection connection = BaseRepository.getConnectDB();
        StringBuilder query = new StringBuilder("SELECT * FROM products WHERE 1=1");
        List<Object> params = new ArrayList<>();

        if (name != null && !name.trim().isEmpty()) {
            query.append(" AND name LIKE ?");
            params.add("%" + name + "%");
        }

        if (category != null && !category.trim().isEmpty() && !category.equals("all")) {
            query.append(" AND category = ?");
            params.add(category);
        }

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query.toString());
            for (int i = 0; i < params.size(); i++) {
                preparedStatement.setObject(i + 1, params.get(i));
            }

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String productId = resultSet.getString("productId");
                String productName = resultSet.getString("name");
                String unit = resultSet.getString("unit");
                double price = resultSet.getDouble("price");
                String productCategory = resultSet.getString("category");

                Product product = new Product(productId, productName, unit, price, productCategory);
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return productList;
    }

    @Override
    public List<String> getAllCategories() {
        List<String> categories = new ArrayList<>();
        Connection connection = BaseRepository.getConnectDB();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CATEGORIES);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                categories.add(resultSet.getString("category"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return categories;
    }

    @Override
    public List<String> getAllUnits() {
        // Common units for produce items
        return Arrays.asList("Kg", "Bó", "Túi", "Quả", "Cành");
    }
}
