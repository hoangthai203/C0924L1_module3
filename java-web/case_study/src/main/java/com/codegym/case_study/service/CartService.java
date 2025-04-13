package com.codegym.case_study.service;

import com.codegym.case_study.model.Cart;
import com.codegym.case_study.model.CartItem;
import com.codegym.case_study.repository.CartRepository;
import com.codegym.case_study.repository.ICartRepository;
import com.codegym.case_study.repository.PhoneRepository;

public class CartService implements ICartService {
    private final ICartRepository cartRepository;

    public CartService() {
        this.cartRepository = new CartRepository();
    }

    @Override
    public void themSanPhamVaoGio(Integer idNguoiDung, int idSanPham, int soLuong) {
        if (idNguoiDung == null) {
            System.out.println("❌ Không xác định được người dùng.");
            return;
        }

        CartItem sanPhamMoi = new CartItem(new PhoneRepository().getPhoneById(idSanPham), soLuong);
        cartRepository.themVaoGioHang(idNguoiDung, sanPhamMoi);
    }

    @Override
    public void xoaSanPhamKhoiGio(Integer idNguoiDung, int idSanPham) {
        if (idNguoiDung != null) {
            cartRepository.xoaSanPhamKhoiGio(idNguoiDung, idSanPham);
        }
    }

    @Override
    public void capNhatSoLuong(Integer idNguoiDung, int idSanPham, int soLuongMoi) {
        if (idNguoiDung != null && soLuongMoi > 0) {
            CartItem sanPhamMoi = new CartItem(new PhoneRepository().getPhoneById(idSanPham), soLuongMoi);
            cartRepository.capNhatSanPhamTrongGio(idNguoiDung, sanPhamMoi);
        } else if (soLuongMoi == 0) {
            cartRepository.xoaSanPhamKhoiGio(idNguoiDung, idSanPham);
        }
    }

    @Override
    public Cart layGioHang(Integer idNguoiDung) {
        return idNguoiDung != null ? cartRepository.layGioHangTheoNguoiDung(idNguoiDung) : new Cart();
    }

    @Override
    public void xoaToanBoGioHang(Integer idNguoiDung) {
        if (idNguoiDung != null) {
            cartRepository.xoaToanBoGioHang(idNguoiDung);
        }
    }
}
