-- Add phone column to users table if it doesn't exist
ALTER TABLE doctor_appointment.users ADD COLUMN IF NOT EXISTS phone VARCHAR(20) AFTER password;
