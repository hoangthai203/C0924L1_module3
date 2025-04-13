package com.codegym.furama.repository;

import com.codegym.furama.model.Customer;
import com.codegym.furama.util.BaseRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository implements ICustomerRepository {
    private static final String SELECT_ALL = "SELECT * FROM customer";
    private static final String INSERT = "INSERT INTO customer VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_BY_ID = "SELECT * FROM customer WHERE customer_id = ?";
    private static final String UPDATE = "UPDATE customer SET customer_type_id=?, customer_name=?, customer_birthday=?, customer_gender=?, customer_id_card=?, customer_phone=?, customer_email=?, customer_address=? WHERE customer_id=?";
    private static final String DELETE = "DELETE FROM customer WHERE customer_id = ?";

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        Connection connection = BaseRepository.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(SELECT_ALL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString("customer_id");
                int typeId = rs.getInt("customer_type_id");
                String name = rs.getString("customer_name");
                Date birthday = rs.getDate("customer_birthday");
                boolean gender = rs.getBoolean("customer_gender");
                String idCard = rs.getString("customer_id_card");
                String phone = rs.getString("customer_phone");
                String email = rs.getString("customer_email");
                String address = rs.getString("customer_address");
                customers.add(new Customer(id, typeId, name, birthday, gender, idCard, phone, email, address));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public void save(Customer customer) {
        try (Connection conn = BaseRepository.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT)) {
            ps.setString(1, customer.getId());
            ps.setInt(2, customer.getCustomerTypeId());
            ps.setString(3, customer.getName());
            ps.setDate(4, customer.getBirthday());
            ps.setBoolean(5, customer.isGender());
            ps.setString(6, customer.getIdCard());
            ps.setString(7, customer.getPhone());
            ps.setString(8, customer.getEmail());
            ps.setString(9, customer.getAddress());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Customer findById(String id) {
        try (Connection conn = BaseRepository.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_BY_ID)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Customer(
                        rs.getString("customer_id"),
                        rs.getInt("customer_type_id"),
                        rs.getString("customer_name"),
                        rs.getDate("customer_birthday"),
                        rs.getBoolean("customer_gender"),
                        rs.getString("customer_id_card"),
                        rs.getString("customer_phone"),
                        rs.getString("customer_email"),
                        rs.getString("customer_address")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Customer customer) {
        try (Connection conn = BaseRepository.getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE)) {
            ps.setInt(1, customer.getCustomerTypeId());
            ps.setString(2, customer.getName());
            ps.setDate(3, customer.getBirthday());
            ps.setBoolean(4, customer.isGender());
            ps.setString(5, customer.getIdCard());
            ps.setString(6, customer.getPhone());
            ps.setString(7, customer.getEmail());
            ps.setString(8, customer.getAddress());
            ps.setString(9, customer.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(String id) {
        try (Connection conn = BaseRepository.getConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE)) {
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<Customer> searchByNamePaging(String keyword, int offset, int limit) {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM customer WHERE customer_name LIKE ? LIMIT ?, ?";
        try (Connection conn = BaseRepository.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, "%" + keyword + "%");
            ps.setInt(2, offset);
            ps.setInt(3, limit);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                customers.add(new Customer(
                        rs.getString("customer_id"),
                        rs.getInt("customer_type_id"),
                        rs.getString("customer_name"),
                        rs.getDate("customer_birthday"),
                        rs.getBoolean("customer_gender"),
                        rs.getString("customer_id_card"),
                        rs.getString("customer_phone"),
                        rs.getString("customer_email"),
                        rs.getString("customer_address")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public int countTotalCustomerByName(String keyword) {
        String sql = "SELECT COUNT(*) FROM customer WHERE customer_name LIKE ?";
        try (Connection conn = BaseRepository.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
