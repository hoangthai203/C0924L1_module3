package com.codegym.case_study.service;

import com.codegym.case_study.model.Cart;
import com.codegym.case_study.model.CartItem;
import java.util.List;

public interface ICartService {
    void themSanPhamVaoGio(int idNguoiDung, int idSanPham, int soLuong);
    void xoaSanPhamKhoiGio(int idNguoiDung, int idSanPham);
    void capNhatSoLuong(int idNguoiDung, int idSanPham, int soLuongMoi);
    Cart layGioHang(int idNguoiDung);
    void xoaToanBoGioHang(int idNguoiDung);
}


