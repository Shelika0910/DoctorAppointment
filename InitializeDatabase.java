import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InitializeDatabase {
    public static void main(String[] args) {
        System.out.println("Initializing database...");
        
        try {
            // Define database connection parameters
            String url = "jdbc:mysql://localhost:3306/";
            String username = "root";
            String password = "";
            
            System.out.println("Loading MySQL JDBC driver...");
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded successfully.");
            
            System.out.println("Connecting to database...");
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connection successful!");
            
            // Read the SQL script
            System.out.println("Reading SQL script...");
            StringBuilder sqlScript = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/db/doctor_appointment_schema.sql"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    // Skip comments and empty lines
                    if (line.trim().startsWith("--") || line.trim().isEmpty()) {
                        continue;
                    }
                    sqlScript.append(line);
                    if (line.trim().endsWith(";")) {
                        sqlScript.append("\n");
                    } else {
                        sqlScript.append(" ");
                    }
                }
            }
            
            // Split the script into individual statements
            String[] statements = sqlScript.toString().split(";\\s*\n");
            
            // Execute each statement
            Statement stmt = conn.createStatement();
            for (String statement : statements) {
                if (!statement.trim().isEmpty()) {
                    try {
                        System.out.println("Executing: " + statement.trim());
                        stmt.execute(statement);
                    } catch (SQLException e) {
                        System.err.println("Error executing SQL statement: " + e.getMessage());
                    }
                }
            }
            
            System.out.println("Database initialization completed successfully.");
            
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
