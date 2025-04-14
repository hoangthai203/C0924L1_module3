package com.codegym.furama.repository;

import com.codegym.furama.model.EducationDegree;
import com.codegym.furama.util.BaseRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EducationDegreeRepository {
    public List<EducationDegree> findAll() {
        List<EducationDegree> list = new ArrayList<>();
        try (Connection conn = BaseRepository.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM education_degree");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("education_degree_id");
                String name = rs.getString("education_degree_name");
                list.add(new EducationDegree(id, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
