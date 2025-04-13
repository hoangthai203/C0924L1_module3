package com.codegym.case_study.service;

import com.codegym.case_study.model.Cart;

public interface ICartService {
    void themSanPhamVaoGio(Integer idNguoiDung, int idSanPham, int soLuong);
    void xoaSanPhamKhoiGio(Integer idNguoiDung, int idSanPham);
    void capNhatSoLuong(Integer idNguoiDung, int idSanPham, int soLuongMoi);
    Cart layGioHang(Integer idNguoiDung);
    void xoaToanBoGioHang(Integer idNguoiDung);
}
