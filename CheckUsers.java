import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class CheckUsers {
    public static void main(String[] args) {
        System.out.println("Checking users in the database...");
        
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
            
            // Check users in the database
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");
            
            System.out.println("Users in the database:");
            System.out.println("ID | Username | Email | Phone | Role");
            System.out.println("-----------------------------------");
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String userName = rs.getString("username");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String role = rs.getString("role");
                
                System.out.println(id + " | " + userName + " | " + email + " | " + phone + " | " + role);
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
