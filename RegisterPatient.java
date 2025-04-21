import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class RegisterPatient {
    public static void main(String[] args) {
        System.out.println("Registering a test patient...");
        
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
            String userQuery = "INSERT INTO users (username, email, password, phone, role) VALUES (?, ?, ?, ?, ?)";
            
            // User details
            String testUsername = "John Doe";
            String testEmail = "john.doe@example.com";
            String testPassword = "password123";
            String testPhone = "9876543210";
            String testRole = "PATIENT";
            
            // Hash the password
            String hashedPassword = hashPassword(testPassword);
            
            System.out.println("Registering user with details:");
            System.out.println("Username: " + testUsername);
            System.out.println("Email: " + testEmail);
            System.out.println("Phone: " + testPhone);
            System.out.println("Role: " + testRole);
            
            PreparedStatement pstmt = conn.prepareStatement(userQuery, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, testUsername);
            pstmt.setString(2, testEmail);
            pstmt.setString(3, hashedPassword);
            pstmt.setString(4, testPhone);
            pstmt.setString(5, testRole);
            
            int rowsAffected = pstmt.executeUpdate();
            System.out.println("Rows affected (users): " + rowsAffected);
            
            if (rowsAffected > 0) {
                System.out.println("User registered successfully!");
                
                // Get the generated user ID
                ResultSet generatedKeys = pstmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int userId = generatedKeys.getInt(1);
                    System.out.println("Generated user ID: " + userId);
                    
                    // Register patient details
                    String patientQuery = "INSERT INTO patients (user_id, first_name, last_name, date_of_birth, gender, phone, address, blood_group, allergies) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    
                    // Patient details
                    String firstName = "John";
                    String lastName = "Doe";
                    String dateOfBirth = "1990-01-01";
                    String gender = "Male";
                    String address = "123 Main St, Anytown, USA";
                    String bloodGroup = "O+";
                    String allergies = "None";
                    
                    System.out.println("Registering patient with details:");
                    System.out.println("First Name: " + firstName);
                    System.out.println("Last Name: " + lastName);
                    System.out.println("Date of Birth: " + dateOfBirth);
                    System.out.println("Gender: " + gender);
                    System.out.println("Address: " + address);
                    System.out.println("Blood Group: " + bloodGroup);
                    System.out.println("Allergies: " + allergies);
                    
                    PreparedStatement patientStmt = conn.prepareStatement(patientQuery);
                    patientStmt.setInt(1, userId);
                    patientStmt.setString(2, firstName);
                    patientStmt.setString(3, lastName);
                    patientStmt.setString(4, dateOfBirth);
                    patientStmt.setString(5, gender);
                    patientStmt.setString(6, testPhone);
                    patientStmt.setString(7, address);
                    patientStmt.setString(8, bloodGroup);
                    patientStmt.setString(9, allergies);
                    
                    int patientRowsAffected = patientStmt.executeUpdate();
                    System.out.println("Rows affected (patients): " + patientRowsAffected);
                    
                    if (patientRowsAffected > 0) {
                        System.out.println("Patient details registered successfully!");
                    } else {
                        System.out.println("Failed to register patient details.");
                    }
                } else {
                    System.out.println("Failed to get generated user ID.");
                }
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
