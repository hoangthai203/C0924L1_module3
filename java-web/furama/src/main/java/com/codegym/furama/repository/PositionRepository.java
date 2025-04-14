package com.codegym.furama.repository;

import com.codegym.furama.model.Position;
import com.codegym.furama.util.BaseRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PositionRepository {
    public List<Position> findAll() {
        List<Position> list = new ArrayList<>();
        try (Connection conn = BaseRepository.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM position");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Position(rs.getInt("position_id"), rs.getString("position_name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
