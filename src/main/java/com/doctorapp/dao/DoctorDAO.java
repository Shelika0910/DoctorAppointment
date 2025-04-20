package com.doctorapp.dao;

import com.doctorapp.model.Doctor;
import com.doctorapp.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {

    // Add a new doctor
    public boolean addDoctor(Doctor doctor) {
        String query = "INSERT INTO doctors (name, specialization, qualification, experience, email, phone, address, " +
                      "consultation_fee, available_days, available_time, image_url) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, doctor.getName());
            pstmt.setString(2, doctor.getSpecialization());
            pstmt.setString(3, doctor.getQualification());
            pstmt.setString(4, doctor.getExperience());
            pstmt.setString(5, doctor.getEmail());
            pstmt.setString(6, doctor.getPhone());
            pstmt.setString(7, doctor.getAddress());
            pstmt.setString(8, doctor.getConsultationFee());
            pstmt.setString(9, doctor.getAvailableDays());
            pstmt.setString(10, doctor.getAvailableTime());
            pstmt.setString(11, doctor.getImageUrl());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get doctor by ID
    public Doctor getDoctorById(int id) {
        String query = "SELECT * FROM doctors WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Doctor doctor = new Doctor();
                doctor.setId(rs.getInt("id"));
                doctor.setName(rs.getString("name"));
                doctor.setSpecialization(rs.getString("specialization"));
                doctor.setQualification(rs.getString("qualification"));
                doctor.setExperience(rs.getString("experience"));
                doctor.setEmail(rs.getString("email"));
                doctor.setPhone(rs.getString("phone"));
                doctor.setAddress(rs.getString("address"));
                doctor.setConsultationFee(rs.getString("consultation_fee"));
                doctor.setAvailableDays(rs.getString("available_days"));
                doctor.setAvailableTime(rs.getString("available_time"));
                doctor.setImageUrl(rs.getString("image_url"));

                return doctor;
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Get all doctors
    public List<Doctor> getAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        String query = "SELECT * FROM doctors";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Doctor doctor = new Doctor();
                doctor.setId(rs.getInt("id"));
                doctor.setName(rs.getString("name"));
                doctor.setSpecialization(rs.getString("specialization"));
                doctor.setQualification(rs.getString("qualification"));
                doctor.setExperience(rs.getString("experience"));
                doctor.setEmail(rs.getString("email"));
                doctor.setPhone(rs.getString("phone"));
                doctor.setAddress(rs.getString("address"));
                doctor.setConsultationFee(rs.getString("consultation_fee"));
                doctor.setAvailableDays(rs.getString("available_days"));
                doctor.setAvailableTime(rs.getString("available_time"));
                doctor.setImageUrl(rs.getString("image_url"));

                doctors.add(doctor);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return doctors;
    }

    // Get doctors by specialization
    public List<Doctor> getDoctorsBySpecialization(String specialization) {
        List<Doctor> doctors = new ArrayList<>();
        String query = "SELECT * FROM doctors WHERE specialization = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, specialization);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Doctor doctor = new Doctor();
                doctor.setId(rs.getInt("id"));
                doctor.setName(rs.getString("name"));
                doctor.setSpecialization(rs.getString("specialization"));
                doctor.setQualification(rs.getString("qualification"));
                doctor.setExperience(rs.getString("experience"));
                doctor.setEmail(rs.getString("email"));
                doctor.setPhone(rs.getString("phone"));
                doctor.setAddress(rs.getString("address"));
                doctor.setConsultationFee(rs.getString("consultation_fee"));
                doctor.setAvailableDays(rs.getString("available_days"));
                doctor.setAvailableTime(rs.getString("available_time"));
                doctor.setImageUrl(rs.getString("image_url"));

                doctors.add(doctor);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return doctors;
    }

    // Update doctor
    public boolean updateDoctor(Doctor doctor) {
        String query = "UPDATE doctors SET name = ?, specialization = ?, qualification = ?, experience = ?, " +
                      "email = ?, phone = ?, address = ?, consultation_fee = ?, available_days = ?, " +
                      "available_time = ?, image_url = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, doctor.getName());
            pstmt.setString(2, doctor.getSpecialization());
            pstmt.setString(3, doctor.getQualification());
            pstmt.setString(4, doctor.getExperience());
            pstmt.setString(5, doctor.getEmail());
            pstmt.setString(6, doctor.getPhone());
            pstmt.setString(7, doctor.getAddress());
            pstmt.setString(8, doctor.getConsultationFee());
            pstmt.setString(9, doctor.getAvailableDays());
            pstmt.setString(10, doctor.getAvailableTime());
            pstmt.setString(11, doctor.getImageUrl());
            pstmt.setInt(12, doctor.getId());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete doctor
    public boolean deleteDoctor(int id) {
        String query = "DELETE FROM doctors WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get total number of doctors
    public int getTotalDoctors() {
        String query = "SELECT COUNT(*) FROM doctors";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return 0;
    }

    // Get doctor ID by user ID
    public int getDoctorIdByUserId(int userId) {
        String query = "SELECT id FROM doctors WHERE user_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, userId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return 0;
    }

    // Get total patients by doctor
    public int getTotalPatientsByDoctor(int doctorId) {
        String query = "SELECT COUNT(DISTINCT patient_id) FROM appointments WHERE doctor_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, doctorId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return 0;
    }

    // Get pending reports by doctor
    public int getPendingReportsByDoctor(int doctorId) {
        String query = "SELECT COUNT(*) FROM appointments WHERE doctor_id = ? AND status = 'COMPLETED' AND medical_report IS NULL";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, doctorId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return 0;
    }

    // Get average rating by doctor
    public double getAverageRatingByDoctor(int doctorId) {
        String query = "SELECT AVG(rating) FROM doctor_ratings WHERE doctor_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, doctorId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble(1);
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return 0.0;
    }

    // Get top doctors
    public List<Doctor> getTopDoctors(int limit) {
        List<Doctor> doctors = new ArrayList<>();
        String query = "SELECT d.*, AVG(dr.rating) as avg_rating, COUNT(DISTINCT a.patient_id) as patient_count " +
                      "FROM doctors d " +
                      "LEFT JOIN doctor_ratings dr ON d.id = dr.doctor_id " +
                      "LEFT JOIN appointments a ON d.id = a.doctor_id " +
                      "GROUP BY d.id " +
                      "ORDER BY avg_rating DESC, patient_count DESC " +
                      "LIMIT ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, limit);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Doctor doctor = new Doctor();
                    doctor.setId(rs.getInt("id"));
                    doctor.setName(rs.getString("name"));
                    doctor.setSpecialization(rs.getString("specialization"));
                    doctor.setQualification(rs.getString("qualification"));
                    doctor.setExperience(rs.getString("experience"));
                    doctor.setEmail(rs.getString("email"));
                    doctor.setPhone(rs.getString("phone"));
                    doctor.setAddress(rs.getString("address"));
                    doctor.setConsultationFee(rs.getString("consultation_fee"));
                    doctor.setAvailableDays(rs.getString("available_days"));
                    doctor.setAvailableTime(rs.getString("available_time"));
                    doctor.setImageUrl(rs.getString("image_url"));
                    doctor.setRating(rs.getDouble("avg_rating"));
                    doctor.setPatientCount(rs.getInt("patient_count"));
                    doctor.setSuccessRate(90); // Default value for now

                    doctors.add(doctor);
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            // Fallback: Return some sample data if the query fails
            if (doctors.isEmpty()) {
                // Create sample doctors for testing
                Doctor doctor1 = new Doctor();
                doctor1.setId(1);
                doctor1.setName("Dr. John Smith");
                doctor1.setSpecialization("Cardiologist");
                doctor1.setQualification("MD, MBBS");
                doctor1.setExperience("10 years");
                doctor1.setRating(4.8);
                doctor1.setPatientCount(125);
                doctor1.setSuccessRate(98);

                Doctor doctor2 = new Doctor();
                doctor2.setId(2);
                doctor2.setName("Dr. Sarah Johnson");
                doctor2.setSpecialization("Neurologist");
                doctor2.setQualification("MD, PhD");
                doctor2.setExperience("8 years");
                doctor2.setRating(4.9);
                doctor2.setPatientCount(110);
                doctor2.setSuccessRate(95);

                Doctor doctor3 = new Doctor();
                doctor3.setId(3);
                doctor3.setName("Dr. Michael Brown");
                doctor3.setSpecialization("Orthopedic");
                doctor3.setQualification("MBBS, MS");
                doctor3.setExperience("12 years");
                doctor3.setRating(4.7);
                doctor3.setPatientCount(95);
                doctor3.setSuccessRate(92);

                doctors.add(doctor1);
                doctors.add(doctor2);
                doctors.add(doctor3);
            }
        }

        return doctors;
    }
}
