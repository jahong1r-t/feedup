package repository;

import entity.User;
import entity.enums.Language;
import entity.enums.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static db.DataSource.*;

public class UserRepository {

    public void addUser(User user) {
        String sql = "INSERT INTO users(id, username, full_name, phone_number, language, role,is_register) VALUES(?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, user.getId());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getFullName());
            ps.setString(4, user.getPhoneNumber());
            ps.setString(5, user.getLanguage().name());
            ps.setString(6, user.getRole().name());
            ps.setBoolean(7, user.getIsRegister());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void updateUser(User user) {
        String sql = "UPDATE users SET username = ?, full_name = ?, phone_number = ?, language = ?, role = ?,is_register = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getFullName());
            ps.setString(3, user.getPhoneNumber());
            ps.setString(4, user.getLanguage().name());
            ps.setString(5, user.getRole().name());
            ps.setLong(6, user.getId());
            ps.setBoolean(7, user.getIsRegister());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Update error: " + e.getMessage());
        }
    }

    public void deleteUser(Long id) {
        String sql = "DELETE FROM users WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Delete error: " + e.getMessage());
        }
    }

    public User getUser(Long id) {
        String sql = "SELECT * FROM users WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getLong("id"),
                        rs.getString("username"),
                        rs.getString("full_name"),
                        rs.getString("phone_number"),
                        Language.valueOf(rs.getString("language")),
                        Role.valueOf(rs.getString("role")),
                        rs.getBoolean("is_register")
                );
            }
        } catch (SQLException e) {
            System.err.println("Get user error: " + e.getMessage());
        }
        return null;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                users.add(new User(
                        rs.getLong("id"),
                        rs.getString("username"),
                        rs.getString("full_name"),
                        rs.getString("phone_number"),
                        Language.valueOf(rs.getString("language")),
                        Role.valueOf(rs.getString("role")),
                        rs.getBoolean("is_register")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Get all users error: " + e.getMessage());
        }

        return users;
    }


}
