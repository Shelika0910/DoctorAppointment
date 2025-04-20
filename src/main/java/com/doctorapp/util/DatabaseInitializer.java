package com.doctorapp.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Database initializer that loads and executes SQL scripts to set up the database.
 * This class is automatically loaded when the application starts.
 */
public class DatabaseInitializer {

    private static final Logger LOGGER = Logger.getLogger(DatabaseInitializer.class.getName());

    /**
     * Initialize the database by executing SQL scripts
     */
    public static void initialize() {
        LOGGER.info("Initializing database...");

        try {
            // Load the SQL script
            InputStream is = DatabaseInitializer.class.getClassLoader().getResourceAsStream("db/doctor_appointment_schema.sql");

            // If still not found, create a default SQL script
            if (is == null) {
                LOGGER.warning("SQL script not found in any location. Creating a default script.");
                String defaultScript = """
                -- Default SQL script
                CREATE TABLE IF NOT EXISTS users (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    username VARCHAR(50) NOT NULL UNIQUE,
                    email VARCHAR(100) NOT NULL UNIQUE,
                    password VARCHAR(100) NOT NULL,
                    role VARCHAR(20) NOT NULL,
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                );
                CREATE TABLE IF NOT EXISTS doctors (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    user_id INT,
                    name VARCHAR(100) NOT NULL,
                    first_name VARCHAR(50),
                    last_name VARCHAR(50),
                    specialization VARCHAR(100) NOT NULL,
                    qualification VARCHAR(100) NOT NULL,
                    experience VARCHAR(50) NOT NULL,
                    email VARCHAR(100) NOT NULL,
                    phone VARCHAR(20) NOT NULL,
                    address TEXT NOT NULL,
                    consultation_fee VARCHAR(20) NOT NULL,
                    available_days VARCHAR(100) NOT NULL,
                    available_time VARCHAR(100) NOT NULL,
                    image_url VARCHAR(255),
                    profile_image VARCHAR(255),
                    bio TEXT
                );
                CREATE TABLE IF NOT EXISTS patients (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    user_id INT NOT NULL,
                    first_name VARCHAR(100),
                    last_name VARCHAR(100),
                    date_of_birth DATE,
                    gender VARCHAR(10),
                    phone VARCHAR(20),
                    address TEXT,
                    blood_group VARCHAR(5),
                    allergies TEXT,
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                );
                CREATE TABLE IF NOT EXISTS appointments (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    patient_id INT NOT NULL,
                    doctor_id INT NOT NULL,
                    patient_name VARCHAR(100) NOT NULL,
                    doctor_name VARCHAR(100) NOT NULL,
                    appointment_date DATE NOT NULL,
                    appointment_time VARCHAR(20) NOT NULL,
                    status VARCHAR(20) NOT NULL,
                    symptoms TEXT,
                    prescription TEXT,
                    reason VARCHAR(255),
                    notes TEXT,
                    fee DECIMAL(10, 2) DEFAULT 0.0,
                    medical_report TEXT
                );
                -- Add test users for each role
                INSERT IGNORE INTO users (username, email, password, role)
                VALUES
                ('Admin User', 'admin@example.com', 'YWRtaW4xMjM=', 'ADMIN'),
                ('Doctor User', 'doctor@example.com', 'ZG9jdG9yMTIz', 'DOCTOR'),
                ('Patient User', 'patient@example.com', 'cGF0aWVudDEyMw==', 'PATIENT');
                -- Add test doctor
                INSERT IGNORE INTO doctors (user_id, name, first_name, last_name, specialization, qualification, experience, email, phone, address, consultation_fee, available_days, available_time, image_url)
                SELECT
                    (SELECT id FROM users WHERE email = 'doctor@example.com'),
                    'Dr. Doctor User',
                    'Doctor',
                    'User',
                    'Cardiology',
                    'MD, MBBS',
                    '10 years',
                    'doctor@example.com',
                    '1234567890',
                    '123 Doctor St',
                    '100',
                    'Monday,Wednesday,Friday',
                    '9:00 AM - 5:00 PM',
                    'doctor1.jpg';
                -- Add test patient
                INSERT IGNORE INTO patients (user_id, first_name, last_name, date_of_birth, gender, phone, address, blood_group, allergies)
                SELECT
                    (SELECT id FROM users WHERE email = 'patient@example.com'),
                    'Patient',
                    'User',
                    '1990-01-01',
                    'Male',
                    '9876543210',
                    '456 Patient Ave',
                    'O+',
                    'None';
                """;
                is = new java.io.ByteArrayInputStream(defaultScript.getBytes());
            }
            // We should have a script by now, either from file or default

            // Read the SQL script
            StringBuilder sqlScript = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
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
            try (Connection conn = DBConnection.getConnection()) {
                try (Statement stmt = conn.createStatement()) {
                    for (String statement : statements) {
                        if (!statement.trim().isEmpty()) {
                            try {
                                stmt.execute(statement);
                            } catch (SQLException e) {
                                LOGGER.log(Level.WARNING, "Error executing SQL statement: " + statement, e);
                            }
                        }
                    }
                }
            } catch (SQLException | ClassNotFoundException e) {
                LOGGER.log(Level.SEVERE, "Database connection error", e);
            }

            LOGGER.info("Database initialization completed successfully.");

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error reading SQL script", e);
        }
    }

    /**
     * Static initializer to run the database initialization when the class is loaded
     */
    static {
        initialize();
    }
}
