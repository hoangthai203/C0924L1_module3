package com.codegym.furama.repository;

import com.codegym.furama.model.Employee;
import com.codegym.furama.util.BaseRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository implements IEmployeeRepository {

    private static final String SELECT_ALL = "SELECT * FROM employee";
    private static final String INSERT = "INSERT INTO employee VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_BY_ID = "SELECT * FROM employee WHERE employee_id = ?";
    private static final String UPDATE = "UPDATE employee SET employee_name=?, employee_birthday=?, employee_id_card=?, employee_salary=?, employee_phone=?, employee_email=?, employee_address=?, position_id=?, education_degree_id=?, division_id=? WHERE employee_id=?";
    private static final String DELETE = "DELETE FROM employee WHERE employee_id = ?";

    @Override
    public List<Employee> findAll() {
        List<Employee> list = new ArrayList<>();
        try (Connection conn = BaseRepository.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Employee(
                        rs.getString("employee_id"),
                        rs.getString("employee_name"),
                        rs.getDate("employee_birthday"),
                        rs.getString("employee_id_card"),
                        rs.getDouble("employee_salary"),
                        rs.getString("employee_phone"),
                        rs.getString("employee_email"),
                        rs.getString("employee_address"),
                        rs.getInt("position_id"),
                        rs.getInt("education_degree_id"),
                        rs.getInt("division_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    @Override
    public List<Employee> searchByNamePaging(String keyword, int offset, int limit) {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM employee WHERE employee_name LIKE ? LIMIT ?, ?";
        try (Connection conn = BaseRepository.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ps.setInt(2, offset);
            ps.setInt(3, limit);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Employee(
                        rs.getString("employee_id"),
                        rs.getString("employee_name"),
                        rs.getDate("employee_birthday"),
                        rs.getString("employee_id_card"),
                        rs.getDouble("employee_salary"),
                        rs.getString("employee_phone"),
                        rs.getString("employee_email"),
                        rs.getString("employee_address"),
                        rs.getInt("position_id"),
                        rs.getInt("education_degree_id"),
                        rs.getInt("division_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int countTotalEmployeeByName(String keyword) {
        int total = 0;
        String sql = "SELECT COUNT(*) FROM employee WHERE employee_name LIKE ?";
        try (Connection conn = BaseRepository.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    @Override
    public void save(Employee e) {
        try (Connection conn = BaseRepository.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT)) {
            ps.setString(1, e.getId());
            ps.setString(2, e.getName());
            ps.setDate(3, e.getBirthday());
            ps.setString(4, e.getIdCard());
            ps.setDouble(5, e.getSalary());
            ps.setString(6, e.getPhone());
            ps.setString(7, e.getEmail());
            ps.setString(8, e.getAddress());
            ps.setInt(9, e.getPositionId());
            ps.setInt(10, e.getEducationDegreeId());
            ps.setInt(11, e.getDivisionId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Employee findById(String id) {
        try (Connection conn = BaseRepository.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_BY_ID)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Employee(
                        rs.getString("employee_id"),
                        rs.getString("employee_name"),
                        rs.getDate("employee_birthday"),
                        rs.getString("employee_id_card"),
                        rs.getDouble("employee_salary"),
                        rs.getString("employee_phone"),
                        rs.getString("employee_email"),
                        rs.getString("employee_address"),
                        rs.getInt("position_id"),
                        rs.getInt("education_degree_id"),
                        rs.getInt("division_id")
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Employee e) {
        try (Connection conn = BaseRepository.getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE)) {
            ps.setString(1, e.getName());
            ps.setDate(2, e.getBirthday());
            ps.setString(3, e.getIdCard());
            ps.setDouble(4, e.getSalary());
            ps.setString(5, e.getPhone());
            ps.setString(6, e.getEmail());
            ps.setString(7, e.getAddress());
            ps.setInt(8, e.getPositionId());
            ps.setInt(9, e.getEducationDegreeId());
            ps.setInt(10, e.getDivisionId());
            ps.setString(11, e.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void remove(String id) {
        try (Connection conn = BaseRepository.getConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE)) {
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
