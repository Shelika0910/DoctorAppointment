import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CheckTables {
    public static void main(String[] args) {
        System.out.println("Checking database tables...");
        
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
            
            Statement stmt = conn.createStatement();
            
            // Check if patients table exists
            java.sql.ResultSet rs = stmt.executeQuery("SHOW TABLES LIKE 'patients'");
            if (rs.next()) {
                System.out.println("Table 'patients' exists.");
                
                // Check the structure of the patients table
                rs = stmt.executeQuery("DESCRIBE patients");
                System.out.println("Structure of patients table:");
                while (rs.next()) {
                    System.out.println(rs.getString("Field") + " - " + rs.getString("Type"));
                }
            } else {
                System.out.println("Table 'patients' does not exist.");
            }
            
            // Check if doctors table exists
            rs = stmt.executeQuery("SHOW TABLES LIKE 'doctors'");
            if (rs.next()) {
                System.out.println("Table 'doctors' exists.");
                
                // Check the structure of the doctors table
                rs = stmt.executeQuery("DESCRIBE doctors");
                System.out.println("Structure of doctors table:");
                while (rs.next()) {
                    System.out.println(rs.getString("Field") + " - " + rs.getString("Type"));
                }
            } else {
                System.out.println("Table 'doctors' does not exist.");
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
