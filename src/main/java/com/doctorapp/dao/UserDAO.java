package com.doctorapp.dao;

import com.doctorapp.model.User;
import com.doctorapp.util.DBConnection;
import com.doctorapp.util.PasswordHasher;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    // Register a new user
    public boolean registerUser(User user) {
        String query = "INSERT INTO users (username, email, password, phone, role) VALUES (?, ?, ?, ?, ?)";

        System.out.println("Executing query: " + query);
        System.out.println("With values: " + user.getUsername() + ", " + user.getEmail() + ", [PASSWORD], " + user.getPhone() + ", " + user.getRole());

        try {
            // Test database connection first
            Connection testConn = DBConnection.getConnection();
            System.out.println("Database connection successful");
            testConn.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Database connection test failed: " + e.getMessage());
            e.printStackTrace();
            return false;
        }

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            // Hash the password
            String hashedPassword = PasswordHasher.hashPassword(user.getPassword());

            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, hashedPassword);
            pstmt.setString(4, user.getPhone());
            pstmt.setString(5, user.getRole());

            System.out.println("Executing prepared statement...");
            int rowsAffected = pstmt.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
            return rowsAffected > 0;

        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error registering user: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Authenticate user
    public User login(String email, String password) {
        String query = "SELECT * FROM users WHERE email = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, email);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // Get the stored hash
                String storedHash = rs.getString("password");

                // Verify the password
                if (PasswordHasher.verifyPassword(password, storedHash)) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setEmail(rs.getString("email"));
                    // Don't set the password in the user object for security
                    user.setPhone(rs.getString("phone"));
                    user.setRole(rs.getString("role"));

                    return user;
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Get user by email
    public User getUserByEmail(String email) {
        String query = "SELECT * FROM users WHERE email = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, email);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                // Don't set the password in the user object for security
                user.setPhone(rs.getString("phone"));
                user.setRole(rs.getString("role"));

                return user;
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Get user by ID
    public User getUserById(int id) {
        String query = "SELECT * FROM users WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                // Don't set the password in the user object for security
                user.setPhone(rs.getString("phone"));
                user.setRole(rs.getString("role"));

                return user;
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Get all users
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                // Don't set the password in the user object for security
                user.setPhone(rs.getString("phone"));
                user.setRole(rs.getString("role"));

                users.add(user);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return users;
    }

    // Update user
    public boolean updateUser(User user) {
        // Check if password is being updated
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            String query = "UPDATE users SET username = ?, email = ?, password = ?, phone = ?, role = ? WHERE id = ?";

            try (Connection conn = DBConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(query)) {

                // Hash the password
                String hashedPassword = PasswordHasher.hashPassword(user.getPassword());

                pstmt.setString(1, user.getUsername());
                pstmt.setString(2, user.getEmail());
                pstmt.setString(3, hashedPassword);
                pstmt.setString(4, user.getPhone());
                pstmt.setString(5, user.getRole());
                pstmt.setInt(6, user.getId());

                int rowsAffected = pstmt.executeUpdate();
                return rowsAffected > 0;

            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            // Update without changing password
            String query = "UPDATE users SET username = ?, email = ?, phone = ?, role = ? WHERE id = ?";

            try (Connection conn = DBConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(query)) {

                pstmt.setString(1, user.getUsername());
                pstmt.setString(2, user.getEmail());
                pstmt.setString(3, user.getPhone());
                pstmt.setString(4, user.getRole());
                pstmt.setInt(5, user.getId());

                int rowsAffected = pstmt.executeUpdate();
                return rowsAffected > 0;

            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    // Save patient details
    public boolean savePatientDetails(int userId, String dateOfBirth, String gender, String address, String bloodGroup, String allergies) {
        // First get the user's name to split into first and last name
        User user = getUserById(userId);
        if (user == null) {
            return false;
        }

        // Split the username into first and last name
        String[] nameParts = user.getUsername().split(" ", 2);
        String firstName = nameParts[0];
        String lastName = nameParts.length > 1 ? nameParts[1] : "";

        String query = "INSERT INTO patients (user_id, first_name, last_name, date_of_birth, gender, phone, address, blood_group, allergies) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, userId);
            pstmt.setString(2, firstName);
            pstmt.setString(3, lastName);
            pstmt.setString(4, dateOfBirth);
            pstmt.setString(5, gender);
            pstmt.setString(6, user.getPhone()); // Now we have phone in the User model
            pstmt.setString(7, address);
            pstmt.setString(8, bloodGroup);
            pstmt.setString(9, allergies);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error saving patient details: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Save doctor details
    public boolean saveDoctorDetails(int userId, String specialization, String qualification, String experience, String address, String bio) {
        // First get the user's name to split into first and last name
        User user = getUserById(userId);
        if (user == null) {
            return false;
        }

        // Split the username into first and last name
        String[] nameParts = user.getUsername().split(" ", 2);
        String firstName = nameParts[0];
        String lastName = nameParts.length > 1 ? nameParts[1] : "";

        String query = "INSERT INTO doctors (user_id, first_name, last_name, specialization, qualification, experience, phone, address, bio) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, userId);
            pstmt.setString(2, firstName);
            pstmt.setString(3, lastName);
            pstmt.setString(4, specialization);
            pstmt.setString(5, qualification);
            pstmt.setInt(6, Integer.parseInt(experience)); // Convert to int as per schema
            pstmt.setString(7, user.getPhone()); // Now we have phone in the User model
            pstmt.setString(8, address);
            pstmt.setString(9, bio);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error saving doctor details: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Delete user
    public boolean deleteUser(int id) {
        String query = "DELETE FROM users WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
