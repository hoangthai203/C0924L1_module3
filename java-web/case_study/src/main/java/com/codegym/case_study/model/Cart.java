package com.codegym.case_study.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> danhSachSanPham;

    public Cart() {
        this.danhSachSanPham = new ArrayList<>();
    }

    public List<CartItem> getDanhSachSanPham() {
        return danhSachSanPham;
    }

    public void themSanPham(CartItem item) {
        this.danhSachSanPham.add(item);
    }

    public double tinhTongTien() {
        double tongTien = 0;
        for (CartItem item : danhSachSanPham) {
            tongTien += item.getThanhTien();
        }
        return tongTien;
    }
}
