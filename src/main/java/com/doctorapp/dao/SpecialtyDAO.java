package com.doctorapp.dao;

import com.doctorapp.model.Specialty;
import com.doctorapp.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SpecialtyDAO {
    
    // Add a new specialty
    public boolean addSpecialty(Specialty specialty) {
        String query = "INSERT INTO specialties (name, description, icon_url) VALUES (?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, specialty.getName());
            pstmt.setString(2, specialty.getDescription());
            pstmt.setString(3, specialty.getIconUrl());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Get specialty by ID
    public Specialty getSpecialtyById(int id) {
        String query = "SELECT * FROM specialties WHERE id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, id);
            
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                Specialty specialty = new Specialty();
                specialty.setId(rs.getInt("id"));
                specialty.setName(rs.getString("name"));
                specialty.setDescription(rs.getString("description"));
                specialty.setIconUrl(rs.getString("icon_url"));
                
                return specialty;
            }
            
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    // Get all specialties
    public List<Specialty> getAllSpecialties() {
        List<Specialty> specialties = new ArrayList<>();
        String query = "SELECT * FROM specialties";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                Specialty specialty = new Specialty();
                specialty.setId(rs.getInt("id"));
                specialty.setName(rs.getString("name"));
                specialty.setDescription(rs.getString("description"));
                specialty.setIconUrl(rs.getString("icon_url"));
                
                specialties.add(specialty);
            }
            
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        return specialties;
    }
    
    // Update specialty
    public boolean updateSpecialty(Specialty specialty) {
        String query = "UPDATE specialties SET name = ?, description = ?, icon_url = ? WHERE id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, specialty.getName());
            pstmt.setString(2, specialty.getDescription());
            pstmt.setString(3, specialty.getIconUrl());
            pstmt.setInt(4, specialty.getId());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Delete specialty
    public boolean deleteSpecialty(int id) {
        String query = "DELETE FROM specialties WHERE id = ?";
        
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
}
