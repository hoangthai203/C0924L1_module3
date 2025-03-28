package com.codegym.case_study.service;

import com.codegym.case_study.model.Cart;
import com.codegym.case_study.model.CartItem;
import com.codegym.case_study.model.Phone;
import com.codegym.case_study.repository.PhoneRepository;

import java.util.*;

public class CartService implements ICartService {
    private Map<Integer, Cart> danhSachGioHang = new HashMap<>();
    private PhoneRepository phoneRepository = new PhoneRepository();

    @Override
    public void themSanPhamVaoGio(int idNguoiDung, int idSanPham, int soLuong) {
        Cart gioHang = danhSachGioHang.getOrDefault(idNguoiDung, new Cart());

        for (CartItem item : gioHang.getDanhSachSanPham()) {
            if (item.getPhone().getId() == idSanPham) {
                item.setSoLuong(item.getSoLuong() + soLuong);
                danhSachGioHang.put(idNguoiDung, gioHang);
                return;
            }
        }

        Phone phone = phoneRepository.getPhoneById(idSanPham);
        if (phone != null) {
            gioHang.getDanhSachSanPham().add(new CartItem(phone, soLuong));
            danhSachGioHang.put(idNguoiDung, gioHang);
        }
    }

    @Override
    public void xoaSanPhamKhoiGio(int idNguoiDung, int idSanPham) {
        Cart gioHang = danhSachGioHang.getOrDefault(idNguoiDung, new Cart());

        gioHang.getDanhSachSanPham().removeIf(item -> item.getPhone().getId() == idSanPham);
        danhSachGioHang.put(idNguoiDung, gioHang);
    }

    @Override
    public void capNhatSoLuong(int idNguoiDung, int idSanPham, int soLuongMoi) {
        Cart gioHang = danhSachGioHang.getOrDefault(idNguoiDung, new Cart());

        for (CartItem item : gioHang.getDanhSachSanPham()) {
            if (item.getPhone().getId() == idSanPham) {
                if (soLuongMoi == 0) {
                    gioHang.getDanhSachSanPham().remove(item);
                } else {
                    item.setSoLuong(soLuongMoi);
                }
                danhSachGioHang.put(idNguoiDung, gioHang);
                return;
            }
        }
    }

    @Override
    public Cart layGioHang(int idNguoiDung) {
        return danhSachGioHang.getOrDefault(idNguoiDung, new Cart());
    }

    @Override
    public void xoaToanBoGioHang(int idNguoiDung) {
        danhSachGioHang.remove(idNguoiDung);
    }
}
