package com.doctorapp.model;

public class MedicalRecord {
    private int id;
    private int patientId;
    private int doctorId;
    private String recordDate;
    private String diagnosis;
    private String treatment;
    private String notes;
    private String recordType;
    private String doctorName;
    
    public MedicalRecord() {
    }
    
    public MedicalRecord(int id, int patientId, int doctorId, String recordDate, String diagnosis,
                        String treatment, String notes, String recordType) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.recordDate = recordDate;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.notes = notes;
        this.recordType = recordType;
    }
    
    // Getters and Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getPatientId() {
        return patientId;
    }
    
    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }
    
    public int getDoctorId() {
        return doctorId;
    }
    
    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }
    
    public String getRecordDate() {
        return recordDate;
    }
    
    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }
    
    public String getDiagnosis() {
        return diagnosis;
    }
    
    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
    
    public String getTreatment() {
        return treatment;
    }
    
    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }
    
    public String getNotes() {
        return notes;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    public String getRecordType() {
        return recordType;
    }
    
    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }
    
    public String getDoctorName() {
        return doctorName;
    }
    
    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }
    
    // Helper method to get formatted date
    public String getFormattedDate() {
        if (recordDate == null || recordDate.isEmpty()) {
            return "";
        }
        
        try {
            java.time.LocalDate date = java.time.LocalDate.parse(recordDate);
            return date.format(java.time.format.DateTimeFormatter.ofPattern("dd MMM yyyy"));
        } catch (Exception e) {
            return recordDate;
        }
    }
}
