package com.codegym.case_study.repository;

import com.codegym.case_study.model.Cart;
import com.codegym.case_study.model.CartItem;
import com.codegym.case_study.model.Phone;
import com.codegym.case_study.util.BaseRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CartRepository implements ICartRepository {
    private static final String LAY_GIO_HANG =
            "SELECT ctgh.id_dien_thoai, ctgh.so_luong, dt.ten, dt.gia " +
                    "FROM chi_tiet_gio_hang ctgh " +
                    "JOIN dien_thoai dt ON ctgh.id_dien_thoai = dt.id_dien_thoai " +
                    "JOIN gio_hang gh ON ctgh.id_gio_hang = gh.id_gio_hang " +
                    "WHERE gh.id_nguoi_dung = ?";

    private static final String LAY_ID_GIO_HANG =
            "SELECT id_gio_hang FROM gio_hang WHERE id_nguoi_dung = ?";

    private static final String TAO_GIO_HANG =
            "INSERT INTO gio_hang (id_nguoi_dung) VALUES (?)";

    private static final String THEM_VAO_GIO =
            "INSERT INTO chi_tiet_gio_hang (id_gio_hang, id_dien_thoai, so_luong, gia) " +
                    "VALUES (?, ?, ?, ?)";

    private static final String CAP_NHAT_GIO =
            "UPDATE chi_tiet_gio_hang " +
                    "SET so_luong = ? " +
                    "WHERE id_gio_hang = (SELECT id_gio_hang FROM gio_hang WHERE id_nguoi_dung = ?) " +
                    "AND id_dien_thoai = ?";

    private static final String XOA_SAN_PHAM =
            "DELETE FROM chi_tiet_gio_hang " +
                    "WHERE id_gio_hang = (SELECT id_gio_hang FROM gio_hang WHERE id_nguoi_dung = ?) " +
                    "AND id_dien_thoai = ?";

    private static final String XOA_TOAN_BO =
            "DELETE FROM chi_tiet_gio_hang " +
                    "WHERE id_gio_hang = (SELECT id_gio_hang FROM gio_hang WHERE id_nguoi_dung = ?)";

    private static final String KIEM_TRA_SAN_PHAM =
            "SELECT so_luong FROM chi_tiet_gio_hang " +
                    "WHERE id_gio_hang = (SELECT id_gio_hang FROM gio_hang WHERE id_nguoi_dung = ?) " +
                    "AND id_dien_thoai = ?";

    private final PhoneRepository phoneRepository = new PhoneRepository();

    @Override
    public Cart layGioHangTheoNguoiDung(int idNguoiDung) {
        Cart gioHang = new Cart();
        try (Connection ketNoi = BaseRepository.getConnection();
             PreparedStatement truyVan = ketNoi.prepareStatement(LAY_GIO_HANG)) {
            truyVan.setInt(1, idNguoiDung);
            try (ResultSet ketQua = truyVan.executeQuery()) {
                while (ketQua.next()) {
                    Phone phone = phoneRepository.getPhoneById(ketQua.getInt("id_dien_thoai"));
                    if (phone != null) {
                        int soLuong = ketQua.getInt("so_luong");
                        gioHang.themSanPham(new CartItem(phone, soLuong));
                    }
                }
            }
        } catch (Exception loi) {
            loi.printStackTrace();
        }
        return gioHang;
    }

    @Override
    public void themVaoGioHang(int idNguoiDung, CartItem sanPham) {
        try (Connection ketNoi = BaseRepository.getConnection();
             PreparedStatement truyVanLayId = ketNoi.prepareStatement(LAY_ID_GIO_HANG);
             PreparedStatement truyVanThem = ketNoi.prepareStatement(THEM_VAO_GIO)) {

            truyVanLayId.setInt(1, idNguoiDung);
            ResultSet ketQua = truyVanLayId.executeQuery();

            int idGioHang;
            if (ketQua.next()) {
                idGioHang = ketQua.getInt("id_gio_hang");
            } else {
                try (PreparedStatement truyVanTaoGio = ketNoi.prepareStatement(TAO_GIO_HANG, PreparedStatement.RETURN_GENERATED_KEYS)) {
                    truyVanTaoGio.setInt(1, idNguoiDung);
                    truyVanTaoGio.executeUpdate();
                    ResultSet keys = truyVanTaoGio.getGeneratedKeys();
                    if (keys.next()) {
                        idGioHang = keys.getInt(1);
                    } else {
                        throw new RuntimeException("Lỗi khi tạo giỏ hàng!");
                    }
                }
            }

            truyVanThem.setInt(1, idGioHang);
            truyVanThem.setInt(2, sanPham.getIdSanPham());
            truyVanThem.setInt(3, sanPham.getSoLuong());
            truyVanThem.setDouble(4, sanPham.getPhone().getGia());
            truyVanThem.executeUpdate();
        } catch (Exception loi) {
            loi.printStackTrace();
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
