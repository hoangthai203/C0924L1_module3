package com.codegym.case_study.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cart implements Serializable {
    private List<CartItem> danhSachSanPham;

    public Cart() {
        this.danhSachSanPham = new ArrayList<>();
    }

    public List<CartItem> getDanhSachSanPham() {
        return danhSachSanPham;
    }

    public void themSanPham(CartItem item) {
        for (CartItem sp : danhSachSanPham) {
            if (sp.getIdSanPham() == item.getIdSanPham()) {
                sp.setSoLuong(sp.getSoLuong() + item.getSoLuong());
                return;
            }
        }
        this.danhSachSanPham.add(item);
    }

    public void capNhatSanPham(int idSanPham, int soLuong) {
        for (CartItem item : danhSachSanPham) {
            if (item.getIdSanPham() == idSanPham) {
                item.setSoLuong(soLuong);
                return;
            }
        }
    }

    public void xoaSanPham(int idSanPham) {
        danhSachSanPham.removeIf(item -> item.getIdSanPham() == idSanPham);
    }

    public double tinhTongTien() {
        return danhSachSanPham.stream().mapToDouble(CartItem::getThanhTien).sum();
    }
}
