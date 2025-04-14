package com.codegym.furama.repository;

import com.codegym.furama.model.Division;
import com.codegym.furama.util.BaseRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DivisionRepository {
    public List<Division> findAll() {
        List<Division> list = new ArrayList<>();
        try (Connection conn = BaseRepository.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM division");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("division_id");
                String name = rs.getString("division_name");
                list.add(new Division(id, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
