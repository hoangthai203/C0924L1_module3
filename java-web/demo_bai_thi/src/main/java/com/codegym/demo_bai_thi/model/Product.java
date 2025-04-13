package com.codegym.demo_bai_thi.model;

public class Product {
    private String productId;
    private String name;
    private String unit;
    private double price;
    private String category;

    public Product() {}

    public Product(String productId, String name, String unit, double price, String category) {
        this.productId = productId;
        this.name = name;
        this.unit = unit;
        this.price = price;
        this.category = category;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}

