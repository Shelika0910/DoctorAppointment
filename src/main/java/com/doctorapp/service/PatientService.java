package com.doctorapp.service;

import com.doctorapp.dao.PatientDAO;
import com.doctorapp.model.Patient;
import com.doctorapp.model.MedicalRecord;
import com.doctorapp.model.Prescription;

import java.util.List;

/**
 * Service layer for Patient-related operations.
 * This class acts as an intermediary between controllers and DAOs.
 */
public class PatientService {
    private PatientDAO patientDAO;
    
    public PatientService() {
        this.patientDAO = new PatientDAO();
    }
    
    /**
     * Get a patient by ID
     * @param id Patient ID
     * @return Patient object if found, null otherwise
     */
    public Patient getPatientById(int id) {
        return patientDAO.getPatientById(id);
    }
    
    /**
     * Get a patient by user ID
     * @param userId User ID
     * @return Patient object if found, null otherwise
     */
    public Patient getPatientByUserId(int userId) {
        return patientDAO.getPatientByUserId(userId);
    }
    
    /**
     * Get patient ID by user ID
     * @param userId User ID
     * @return Patient ID if found, 0 otherwise
     */
    public int getPatientIdByUserId(int userId) {
        return patientDAO.getPatientIdByUserId(userId);
    }
    
    /**
     * Get all patients
     * @return List of all patients
     */
    public List<Patient> getAllPatients() {
        return patientDAO.getAllPatients();
    }
    
    /**
     * Update a patient
     * @param patient The patient to update
     * @return true if update was successful, false otherwise
     */
    public boolean updatePatient(Patient patient) {
        return patientDAO.updatePatient(patient);
    }
    
    /**
     * Delete a patient
     * @param id Patient ID
     * @return true if deletion was successful, false otherwise
     */
    public boolean deletePatient(int id) {
        return patientDAO.deletePatient(id);
    }
    
    /**
     * Get total number of patients
     * @return Total number of patients
     */
    public int getTotalPatients() {
        return patientDAO.getTotalPatients();
    }
    
    /**
     * Get recent medical records for a patient
     * @param patientId Patient ID
     * @param limit Number of records to return
     * @return List of recent medical records
     */
    public List<MedicalRecord> getRecentMedicalRecords(int patientId, int limit) {
        return patientDAO.getRecentMedicalRecords(patientId, limit);
    }
    
    /**
     * Get current prescriptions for a patient
     * @param patientId Patient ID
     * @return List of current prescriptions
     */
    public List<Prescription> getCurrentPrescriptions(int patientId) {
        return patientDAO.getCurrentPrescriptions(patientId);
    }
}
