package com.codegym.case_study.repository;

import com.codegym.case_study.model.Cart;
import com.codegym.case_study.model.CartItem;
import com.codegym.case_study.model.Phone;
import com.codegym.case_study.util.BaseRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CartRepository implements ICartRepository {
    private static final String LAY_GIO_HANG = "SELECT * FROM gio_hang WHERE id_nguoi_dung = ?";
    private static final String THEM_VAO_GIO = "INSERT INTO gio_hang (id_nguoi_dung, id_san_pham, so_luong) VALUES (?, ?, ?)";
    private static final String CAP_NHAT_GIO = "UPDATE gio_hang SET so_luong = ? WHERE id_nguoi_dung = ? AND id_san_pham = ?";
    private static final String XOA_SAN_PHAM = "DELETE FROM gio_hang WHERE id_nguoi_dung = ? AND id_san_pham = ?";
    private static final String XOA_TOAN_BO = "DELETE FROM gio_hang WHERE id_nguoi_dung = ?";
    private static final String KIEM_TRA_SAN_PHAM = "SELECT so_luong FROM gio_hang WHERE id_nguoi_dung = ? AND id_san_pham = ?";

    private final PhoneRepository phoneRepository = new PhoneRepository();

    @Override
    public Cart layGioHangTheoNguoiDung(int idNguoiDung) {
        Cart gioHang = new Cart();
        try (Connection ketNoi = BaseRepository.getConnection();
             PreparedStatement truyVan = ketNoi.prepareStatement(LAY_GIO_HANG)) {
            truyVan.setInt(1, idNguoiDung);
            try (ResultSet ketQua = truyVan.executeQuery()) {
                while (ketQua.next()) {
                    int idSanPham = ketQua.getInt("id_san_pham");
                    int soLuong = ketQua.getInt("so_luong");

                    Phone phone = phoneRepository.getPhoneById(idSanPham);
                    if (phone != null) {
                        gioHang.getDanhSachSanPham().add(new CartItem(phone, soLuong));
                    }
                }
            }
        } catch (Exception loi) {
            loi.printStackTrace();
        }
        return gioHang;
    }

    private boolean kiemTraSanPhamTonTai(int idNguoiDung, int idSanPham) {
        try (Connection ketNoi = BaseRepository.getConnection();
             PreparedStatement truyVan = ketNoi.prepareStatement(KIEM_TRA_SAN_PHAM)) {
            truyVan.setInt(1, idNguoiDung);
            truyVan.setInt(2, idSanPham);
            try (ResultSet ketQua = truyVan.executeQuery()) {
                return ketQua.next(); // Trả về true nếu có dữ liệu
            }
        } catch (Exception loi) {
            loi.printStackTrace();
        }
        return false;
    }

    @Override
    public void themVaoGioHang(int idNguoiDung, CartItem sanPham) {
        if (kiemTraSanPhamTonTai(idNguoiDung, sanPham.getIdSanPham())) {
            capNhatSanPhamTrongGio(idNguoiDung, sanPham);
        } else {
            try (Connection ketNoi = BaseRepository.getConnection();
                 PreparedStatement truyVan = ketNoi.prepareStatement(THEM_VAO_GIO)) {
                truyVan.setInt(1, idNguoiDung);
                truyVan.setInt(2, sanPham.getIdSanPham());
                truyVan.setInt(3, sanPham.getSoLuong());
                truyVan.executeUpdate();
            } catch (Exception loi) {
                loi.printStackTrace();
            }
        }
    }

    @Override
    public void capNhatSanPhamTrongGio(int idNguoiDung, CartItem sanPham) {
        try (Connection ketNoi = BaseRepository.getConnection();
             PreparedStatement truyVan = ketNoi.prepareStatement(CAP_NHAT_GIO)) {
            truyVan.setInt(1, sanPham.getSoLuong());
            truyVan.setInt(2, idNguoiDung);
            truyVan.setInt(3, sanPham.getIdSanPham());
            truyVan.executeUpdate();
        } catch (Exception loi) {
            loi.printStackTrace();
        }
    }

    @Override
    public void xoaSanPhamKhoiGio(int idNguoiDung, int idSanPham) {
        try (Connection ketNoi = BaseRepository.getConnection();
             PreparedStatement truyVan = ketNoi.prepareStatement(XOA_SAN_PHAM)) {
            truyVan.setInt(1, idNguoiDung);
            truyVan.setInt(2, idSanPham);
            truyVan.executeUpdate();
        } catch (Exception loi) {
            loi.printStackTrace();
        }
    }

    @Override
    public void xoaToanBoGioHang(int idNguoiDung) {
        try (Connection ketNoi = BaseRepository.getConnection();
             PreparedStatement truyVan = ketNoi.prepareStatement(XOA_TOAN_BO)) {
            truyVan.setInt(1, idNguoiDung);
            truyVan.executeUpdate();
        } catch (Exception loi) {
            loi.printStackTrace();
        }
    }
}
