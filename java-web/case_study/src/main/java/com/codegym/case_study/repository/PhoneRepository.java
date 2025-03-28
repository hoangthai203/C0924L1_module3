package com.codegym.case_study.repository;

import com.codegym.case_study.model.Phone;
import com.codegym.case_study.util.BaseRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PhoneRepository {
    private static final String SELECT_ALL_PHONES = "SELECT * FROM dien_thoai";
    private static final String SELECT_PHONE_BY_ID = "SELECT * FROM dien_thoai WHERE id_dien_thoai = ?";

    public List<Phone> getAllPhones() {
        List<Phone> danhSachDienThoai = new ArrayList<>();
        try (Connection connection = BaseRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PHONES);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                danhSachDienThoai.add(mapResultSetToPhone(resultSet));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return danhSachDienThoai;
    }

    public Phone getPhoneById(int id) {
        try (Connection connection = BaseRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PHONE_BY_ID)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToPhone(resultSet);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Trả về null nếu không tìm thấy điện thoại
    }

    private Phone mapResultSetToPhone(ResultSet resultSet) throws Exception {
        int id = resultSet.getInt("id_dien_thoai");
        String ten = resultSet.getString("ten");
        String thuongHieu = resultSet.getString("thuong_hieu");
        String model = resultSet.getString("model");
        int namSanXuat = resultSet.getInt("nam_san_xuat");
        double gia = resultSet.getDouble("gia");
        int ram = resultSet.getInt("ram");
        int boNhoTrong = resultSet.getInt("bo_nho_trong");
        int dungLuongPin = resultSet.getInt("dung_luong_pin");
        String heDieuHanh = resultSet.getString("he_dieu_hanh");
        String trangThai = resultSet.getString("trang_thai");

        return new Phone(id, ten, thuongHieu, model, namSanXuat, gia, ram, boNhoTrong, dungLuongPin, heDieuHanh, trangThai);
    }
}
