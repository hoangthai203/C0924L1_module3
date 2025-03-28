package com.codegym.case_study.model;

import java.io.Serializable;

public class CartItem implements Serializable {
    private Phone phone;
    private int soLuong;

    public CartItem(Phone phone, int soLuong) {
        this.phone = phone;
        this.soLuong = soLuong;
    }

    public Phone getPhone() {
        return phone;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getIdSanPham() {
        return phone.getId();
    }

    public double getThanhTien() {
        return phone.getGia() * soLuong;
    }
}
