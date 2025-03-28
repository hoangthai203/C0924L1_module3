package com.codegym.case_study.repository;

import com.codegym.case_study.model.DichVu;
import com.codegym.case_study.util.BaseRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DichVuRepository implements IDichVuRepository {

    @Override
    public List<DichVu> layDanhSachDichVu() throws SQLException {
        List<DichVu> danhSachDichVu = new ArrayList<>();
        String sql = "SELECT * FROM dich_vu";

        try (Connection connection = BaseRepository.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id_dich_vu");
                String ten = resultSet.getString("ten");
                String moTa = resultSet.getString("mo_ta");
                double gia = resultSet.getDouble("gia");
                String trangThai = resultSet.getString("trang_thai");

                DichVu dichVu = new DichVu(id, ten, moTa, gia, trangThai);
                danhSachDichVu.add(dichVu);
            }
        }
        return danhSachDichVu;
    }

    @Override
    public DichVu timDichVuTheoId(int id) throws SQLException {
        String sql = "SELECT * FROM dich_vu WHERE id_dich_vu = ?";
        DichVu dichVu = null;

        try (Connection connection = BaseRepository.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String ten = resultSet.getString("ten");
                String moTa = resultSet.getString("mo_ta");
                double gia = resultSet.getDouble("gia");
                String trangThai = resultSet.getString("trang_thai");

                dichVu = new DichVu(id, ten, moTa, gia, trangThai);
            }
        }
        return dichVu;
    }

    @Override
    public void themDichVu(DichVu dichVu) throws SQLException {
        String sql = "INSERT INTO dich_vu (ten, mo_ta, gia, trang_thai) VALUES (?, ?, ?, ?)";

        try (Connection connection = BaseRepository.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, dichVu.getTen());
            statement.setString(2, dichVu.getMoTa());
            statement.setDouble(3, dichVu.getGia());
            statement.setString(4, dichVu.getTrangThai());
            statement.executeUpdate();
        }
    }

    @Override
    public void capNhatDichVu(DichVu dichVu) throws SQLException {
        String sql = "UPDATE dich_vu SET ten = ?, mo_ta = ?, gia = ?, trang_thai = ? WHERE id_dich_vu = ?";

        try (Connection connection = BaseRepository.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, dichVu.getTen());
            statement.setString(2, dichVu.getMoTa());
            statement.setDouble(3, dichVu.getGia());
            statement.setString(4, dichVu.getTrangThai());
            statement.setInt(5, dichVu.getIdDichVu());
            statement.executeUpdate();
        }
    }

    @Override
    public void xoaDichVu(int id) throws SQLException {
        String sql = "DELETE FROM dich_vu WHERE id_dich_vu = ?";

        try (Connection connection = BaseRepository.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    @Override
    public void capNhatTrangThaiDichVu(int id, String trangThai) throws SQLException {
        String sql = "UPDATE dich_vu SET trang_thai = ? WHERE id_dich_vu = ?";

        try (Connection connection = BaseRepository.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, trangThai);
            statement.setInt(2, id);
            statement.executeUpdate();
        }
    }
}
