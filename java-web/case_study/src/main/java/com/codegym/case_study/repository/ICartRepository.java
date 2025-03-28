package com.codegym.case_study.repository;

import com.codegym.case_study.model.Cart;
import com.codegym.case_study.model.CartItem;

public interface ICartRepository {
    Cart layGioHangTheoNguoiDung(int idNguoiDung);
    void themVaoGioHang(int idNguoiDung, CartItem sanPham);
    void capNhatSanPhamTrongGio(int idNguoiDung, CartItem sanPham);
    void xoaSanPhamKhoiGio(int idNguoiDung, int idSanPham);
    void xoaToanBoGioHang(int idNguoiDung);
}
