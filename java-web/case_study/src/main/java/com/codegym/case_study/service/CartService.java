package com.codegym.case_study.service;

import com.codegym.case_study.model.Cart;
import com.codegym.case_study.model.CartItem;
import com.codegym.case_study.model.Phone;
import com.codegym.case_study.repository.PhoneRepository;
import com.codegym.case_study.service.ICartService;

import java.util.List;

public class CartService implements ICartService {
    private Cart gioHang;
    private PhoneRepository phoneRepository;

    public CartService() {
        this.gioHang = new Cart();
        this.phoneRepository = new PhoneRepository(); // ✅ Khởi tạo PhoneRepository để lấy thông tin điện thoại
    }

    @Override
    public void themSanPhamVaoGio(int idSanPham, int soLuong) {
        for (CartItem item : gioHang.getDanhSachSanPham()) {
            if (item.getIdSanPham() == idSanPham) {
                item.setSoLuong(item.getSoLuong() + soLuong);
                return;
            }
        }

        Phone phone = phoneRepository.getPhoneById(idSanPham); // ✅ Lấy thông tin sản phẩm từ database
        if (phone != null) {
            gioHang.getDanhSachSanPham().add(new CartItem(phone, soLuong));
        }
    }

    @Override
    public void xoaSanPhamKhoiGio(int idSanPham) {
        gioHang.getDanhSachSanPham().removeIf(item -> item.getIdSanPham() == idSanPham);
    }

    @Override
    public void capNhatSoLuong(int idSanPham, int soLuongMoi) {
        for (CartItem item : gioHang.getDanhSachSanPham()) {
            if (item.getIdSanPham() == idSanPham) {
                item.setSoLuong(soLuongMoi);
                return;
            }
        }
    }

    @Override
    public List<CartItem> layDanhSachSanPham() {
        return gioHang.getDanhSachSanPham();
    }

    @Override
    public Cart layGioHang() {
        return gioHang;
    }
}