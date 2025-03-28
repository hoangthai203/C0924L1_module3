package com.codegym.case_study.model;

public class CartItem {
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
        return phone.getId(); // ✅ Lấy ID của điện thoại
    }

    public double getThanhTien() {
        return phone.getGia() * soLuong;
    }
}
