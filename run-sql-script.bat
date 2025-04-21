@echo off
echo Running SQL script...
mysql --host=localhost --user=root --password= doctor_appointment < add-phone-column.sql
if %ERRORLEVEL% EQU 0 (
    echo SQL script executed successfully.
) else (
    echo Failed to execute SQL script.
)
