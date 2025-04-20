package com.doctorapp.model;

public class Prescription {
    private int id;
    private int patientId;
    private int doctorId;
    private String medicationName;
    private String dosage;
    private String frequency;
    private String duration;
    private String startDate;
    private String endDate;
    private String instructions;
    private String doctorName;
    private boolean isActive;
    
    public Prescription() {
    }
    
    public Prescription(int id, int patientId, int doctorId, String medicationName, String dosage,
                       String frequency, String duration, String startDate, String endDate, String instructions) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.medicationName = medicationName;
        this.dosage = dosage;
        this.frequency = frequency;
        this.duration = duration;
        this.startDate = startDate;
        this.endDate = endDate;
        this.instructions = instructions;
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
    
    public String getMedicationName() {
        return medicationName;
    }
    
    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }
    
    public String getDosage() {
        return dosage;
    }
    
    public void setDosage(String dosage) {
        this.dosage = dosage;
    }
    
    public String getFrequency() {
        return frequency;
    }
    
    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }
    
    public String getDuration() {
        return duration;
    }
    
    public void setDuration(String duration) {
        this.duration = duration;
    }
    
    public String getStartDate() {
        return startDate;
    }
    
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    
    public String getEndDate() {
        return endDate;
    }
    
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    
    public String getInstructions() {
        return instructions;
    }
    
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
    
    public String getDoctorName() {
        return doctorName;
    }
    
    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }
    
    public boolean isActive() {
        return isActive;
    }
    
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
    
    // Helper method to get formatted dates
    public String getFormattedStartDate() {
        if (startDate == null || startDate.isEmpty()) {
            return "";
        }
        
        try {
            java.time.LocalDate date = java.time.LocalDate.parse(startDate);
            return date.format(java.time.format.DateTimeFormatter.ofPattern("dd MMM yyyy"));
        } catch (Exception e) {
            return startDate;
        }
    }
    
    public String getFormattedEndDate() {
        if (endDate == null || endDate.isEmpty()) {
            return "";
        }
        
        try {
            java.time.LocalDate date = java.time.LocalDate.parse(endDate);
            return date.format(java.time.format.DateTimeFormatter.ofPattern("dd MMM yyyy"));
        } catch (Exception e) {
            return endDate;
        }
    }
    
    // Helper method to get date range
    public String getDateRange() {
        return getFormattedStartDate() + " - " + getFormattedEndDate();
    }
}
