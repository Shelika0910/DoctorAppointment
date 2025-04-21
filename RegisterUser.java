import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class RegisterUser {
    public static void main(String[] args) {
        System.out.println("Registering a test user...");
        
        try {
            // Define database connection parameters
            String url = "jdbc:mysql://localhost:3306/doctor_appointment";
            String username = "root";
            String password = "";
            
            System.out.println("Loading MySQL JDBC driver...");
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded successfully.");
            
            System.out.println("Connecting to database...");
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connection successful!");
            
            // Register a test user
            String query = "INSERT INTO users (username, email, password, phone, role) VALUES (?, ?, ?, ?, ?)";
            
            // User details
            String testUsername = "Test User";
            String testEmail = "test@example.com";
            String testPassword = "password123";
            String testPhone = "1234567890";
            String testRole = "PATIENT";
            
            // Hash the password
            String hashedPassword = hashPassword(testPassword);
            
            System.out.println("Registering user with details:");
            System.out.println("Username: " + testUsername);
            System.out.println("Email: " + testEmail);
            System.out.println("Phone: " + testPhone);
            System.out.println("Role: " + testRole);
            
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, testUsername);
            pstmt.setString(2, testEmail);
            pstmt.setString(3, hashedPassword);
            pstmt.setString(4, testPhone);
            pstmt.setString(5, testRole);
            
            int rowsAffected = pstmt.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
            
            if (rowsAffected > 0) {
                System.out.println("User registered successfully!");
            } else {
                System.out.println("Failed to register user.");
            }
            
            conn.close();
            System.out.println("Database connection closed.");
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("Driver not found: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // Hash a password with SHA-256
    private static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedPassword = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hashedPassword);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }
}
