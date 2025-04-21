package com.doctorapp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/doctor_appointment";
    private static final String USERNAME = "root";
    private static final String PASSWORD = ""; // Set your password here

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        System.out.println("Attempting to connect to database: " + URL);
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("MySQL JDBC Driver loaded successfully");

            // Get a connection
            System.out.println("Connecting to database with username: " + USERNAME);
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Database connection established successfully");
            return conn;
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found: " + e.getMessage());
            e.printStackTrace();

            // Try to load the driver from the lib directory
            try {
                System.out.println("Attempting to load MySQL JDBC driver from lib directory");
                java.io.File file = new java.io.File("src/main/webapp/WEB-INF/lib/mysql-connector-java-8.0.28.jar");
                System.out.println("Checking if driver file exists: " + file.getAbsolutePath());
                System.out.println("File exists: " + file.exists());

                if (file.exists()) {
                    java.net.URL url = file.toURI().toURL();
                    java.net.URLClassLoader classLoader = (java.net.URLClassLoader) ClassLoader.getSystemClassLoader();
                    java.lang.reflect.Method method = java.net.URLClassLoader.class.getDeclaredMethod("addURL", java.net.URL.class);
                    method.setAccessible(true);
                    method.invoke(classLoader, url);
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    System.out.println("MySQL JDBC Driver loaded from lib directory");
                    return DriverManager.getConnection(URL, USERNAME, PASSWORD);
                } else {
                    throw new ClassNotFoundException("MySQL JDBC driver not found in the lib directory");
                }
            } catch (Exception ex) {
                System.err.println("Failed to load MySQL JDBC driver from lib directory: " + ex.getMessage());
                ex.printStackTrace();
                throw new ClassNotFoundException("Failed to load MySQL JDBC driver: " + ex.getMessage(), ex);
            }
        } catch (SQLException e) {
            System.err.println("Failed to connect to database: " + e.getMessage());
            e.printStackTrace();

            // Create a dummy in-memory H2 database as fallback
            try {
                System.out.println("Attempting to create H2 in-memory database as fallback");
                Class.forName("org.h2.Driver");
                Connection conn = DriverManager.getConnection("jdbc:h2:mem:doctor_appointment;DB_CLOSE_DELAY=-1", "sa", "");
                System.out.println("H2 in-memory database created successfully");
                return conn;
            } catch (Exception ex) {
                System.err.println("Failed to create H2 in-memory database: " + ex.getMessage());
                ex.printStackTrace();
                throw e; // Re-throw the original exception if H2 fallback fails
            }
        }
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
