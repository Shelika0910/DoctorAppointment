import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class CheckPatients {
    public static void main(String[] args) {
        System.out.println("Checking patients in the database...");

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

            // Check patients in the database
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT p.*, u.username, u.email, u.role FROM patients p JOIN users u ON p.user_id = u.id");

            System.out.println("Patients in the database:");
            System.out.println("ID | User ID | First Name | Last Name | Date of Birth | Gender | Phone | Blood Group | Username | Email | Role");
            System.out.println("---------------------------------------------------------------------------------------------------");

            while (rs.next()) {
                int id = rs.getInt("id");
                int userId = rs.getInt("user_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String dateOfBirth = rs.getString("date_of_birth");
                String gender = rs.getString("gender");
                String phone = rs.getString("phone");
                String bloodGroup = rs.getString("blood_group");
                String userName = rs.getString("username");
                String email = rs.getString("email");
                String role = rs.getString("role");

                System.out.println(id + " | " + userId + " | " + firstName + " | " + lastName + " | " + dateOfBirth + " | " + gender + " | " + phone + " | " + bloodGroup + " | " + userName + " | " + email + " | " + role);
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
