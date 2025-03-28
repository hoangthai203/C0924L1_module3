package com.codegym.case_study.service;

import com.codegym.case_study.model.Cart;
import com.codegym.case_study.model.CartItem;

import java.util.List;

public interface ICartService {
    void themSanPhamVaoGio(int idSanPham, int soLuong);
    void xoaSanPhamKhoiGio(int idSanPham);
    void capNhatSoLuong(int idSanPham, int soLuongMoi);
    List<CartItem> layDanhSachSanPham();
    Cart layGioHang();
}
