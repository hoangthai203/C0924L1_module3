package com.codegym.case_study.repository;

import com.codegym.case_study.model.User;
import com.codegym.case_study.util.BaseRepository;

import java.sql.*;

public class UserRepository {
    public User timTheoTenDangNhap(String tenDangNhap) {
        String query = "SELECT * FROM nguoi_dung WHERE ten_dang_nhap = ?";
        try (Connection connection = BaseRepository.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, tenDangNhap);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new User(
                        resultSet.getInt("id_nguoi_dung"),
                        resultSet.getString("ten_dang_nhap"),
                        resultSet.getString("mat_khau"),
                        resultSet.getDate("ngay_sinh"),
                        resultSet.getString("dia_chi"),
                        resultSet.getString("email"),
                        resultSet.getString("so_dien_thoai"),
                        resultSet.getString("vai_tro")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
