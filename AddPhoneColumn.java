import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AddPhoneColumn {
    public static void main(String[] args) {
        System.out.println("Adding phone column to users table...");
        
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
            
            // Add phone column to users table if it doesn't exist
            Statement stmt = conn.createStatement();
            
            // Check if phone column exists
            java.sql.ResultSet rs = stmt.executeQuery("SHOW COLUMNS FROM users LIKE 'phone'");
            if (!rs.next()) {
                System.out.println("Phone column does not exist. Adding it...");
                stmt.executeUpdate("ALTER TABLE users ADD COLUMN phone VARCHAR(20) AFTER password");
                System.out.println("Phone column added successfully.");
            } else {
                System.out.println("Phone column already exists.");
            }
            
            // Check the structure of the users table
            rs = stmt.executeQuery("DESCRIBE users");
            System.out.println("Structure of users table after update:");
            while (rs.next()) {
                System.out.println(rs.getString("Field") + " - " + rs.getString("Type"));
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
}
