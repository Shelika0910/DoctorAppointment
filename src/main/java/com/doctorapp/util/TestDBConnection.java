package com.doctorapp.util;

import java.sql.Connection;
import java.sql.SQLException;

public class TestDBConnection {
    public static void main(String[] args) {
        System.out.println("Testing database connection...");

        try {
            // Define database connection parameters
            String url = "jdbc:mysql://localhost:3306/doctor_appointment";
            String username = "root";
            String password = "";

            System.out.println("Loading MySQL JDBC driver...");
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded successfully.");

            System.out.println("Connecting to database...");
            Connection conn = java.sql.DriverManager.getConnection(url, username, password);
            System.out.println("Connection successful!");

            // Test if the database exists
            java.sql.Statement stmt = conn.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery("SHOW DATABASES LIKE 'doctor_appointment'");

            if (rs.next()) {
                System.out.println("Database 'doctor_appointment' exists.");

                // Test if the users table exists
                rs = stmt.executeQuery("SHOW TABLES FROM doctor_appointment LIKE 'users'");
                if (rs.next()) {
                    System.out.println("Table 'users' exists.");

                    // Check the structure of the users table
                    rs = stmt.executeQuery("DESCRIBE doctor_appointment.users");
                    System.out.println("Structure of users table:");
                    while (rs.next()) {
                        System.out.println(rs.getString("Field") + " - " + rs.getString("Type"));
                    }
                } else {
                    System.out.println("Table 'users' does not exist.");
                }
            } else {
                System.out.println("Database 'doctor_appointment' does not exist.");
            }

            conn.close();
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
