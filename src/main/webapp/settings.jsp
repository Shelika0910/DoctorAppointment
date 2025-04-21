<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Settings - HealthCare</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/dashboard.css">
</head>
<body>
    <div class="dashboard-container">
        <!-- Sidebar -->
        <div class="sidebar">
            <div class="user-profile">
                <div class="avatar">
                    <i class="fas fa-user"></i>
                </div>
                <div class="user-info">
                    <h3>Anila..</h3>
                    <p>anila240c@gmail.com</p>
                </div>
            </div>
            
            <div class="logout-btn">
                <a href="logout">Log out</a>
            </div>
            
            <ul class="sidebar-menu">
                <li>
                    <a href="dashboard">
                        <i class="fas fa-th-large"></i>
                        <span>Dashboard</span>
                    </a>
                </li>
                <li>
                    <a href="appointments">
                        <i class="far fa-calendar-check"></i>
                        <span>My Appointments</span>
                    </a>
                </li>
                <li>
                    <a href="sessions">
                        <i class="fas fa-clock"></i>
                        <span>My Sessions</span>
                    </a>
                </li>
                <li>
                    <a href="patients">
                        <i class="fas fa-user-injured"></i>
                        <span>My Patients</span>
                    </a>
                </li>
                <li class="active">
                    <a href="settings">
                        <i class="fas fa-cog"></i>
                        <span>Settings</span>
                    </a>
                </li>
            </ul>
        </div>
        
        <!-- Main Content -->
        <div class="main-content">
            <div class="header">
                <div class="back-button">
                    <a href="dashboard"><i class="fas fa-arrow-left"></i> Back</a>
                </div>
                <h1>Settings</h1>
                <div class="date-display">
                    <span>Today's Date:</span>
                    <strong>2023-10-23</strong>
                    <i class="far fa-calendar-alt"></i>
                </div>
            </div>
            
            <div class="content-section">
                <div class="section-header">
                    <h2>Account Settings</h2>
                </div>
                
                <div class="settings-form">
                    <form action="#" method="post">
                        <div class="form-group">
                            <label for="name">Full Name</label>
                            <input type="text" id="name" name="name" value="Anila" class="form-control">
                        </div>
                        
                        <div class="form-group">
                            <label for="email">Email Address</label>
                            <input type="email" id="email" name="email" value="anila240c@gmail.com" class="form-control">
                        </div>
                        
                        <div class="form-group">
                            <label for="phone">Phone Number</label>
                            <input type="tel" id="phone" name="phone" value="+91 9876543210" class="form-control">
                        </div>
                        
                        <div class="form-group">
                            <label for="specialization">Specialization</label>
                            <input type="text" id="specialization" name="specialization" value="General Physician" class="form-control">
                        </div>
                        
                        <div class="form-group">
                            <label for="address">Address</label>
                            <textarea id="address" name="address" class="form-control" rows="3">123 Medical Center, Health Street, City</textarea>
                        </div>
                        
                        <div class="form-group">
                            <label for="password">Change Password</label>
                            <input type="password" id="password" name="password" placeholder="Enter new password" class="form-control">
                        </div>
                        
                        <div class="form-group">
                            <label for="confirm_password">Confirm Password</label>
                            <input type="password" id="confirm_password" name="confirm_password" placeholder="Confirm new password" class="form-control">
                        </div>
                        
                        <div class="form-actions">
                            <button type="submit" class="btn btn-primary">Save Changes</button>
                            <button type="reset" class="btn btn-outline">Cancel</button>
                        </div>
                    </form>
                </div>
            </div>
            
            <div class="content-section">
                <div class="section-header">
                    <h2>Notification Settings</h2>
                </div>
                
                <div class="notification-settings">
                    <div class="setting-item">
                        <div class="setting-info">
                            <h4>Email Notifications</h4>
                            <p>Receive email notifications for new appointments</p>
                        </div>
                        <div class="setting-toggle">
                            <label class="switch">
                                <input type="checkbox" checked>
                                <span class="slider round"></span>
                            </label>
                        </div>
                    </div>
                    
                    <div class="setting-item">
                        <div class="setting-info">
                            <h4>SMS Notifications</h4>
                            <p>Receive SMS alerts for appointment reminders</p>
                        </div>
                        <div class="setting-toggle">
                            <label class="switch">
                                <input type="checkbox" checked>
                                <span class="slider round"></span>
                            </label>
                        </div>
                    </div>
                    
                    <div class="setting-item">
                        <div class="setting-info">
                            <h4>Browser Notifications</h4>
                            <p>Show browser notifications for upcoming appointments</p>
                        </div>
                        <div class="setting-toggle">
                            <label class="switch">
                                <input type="checkbox">
                                <span class="slider round"></span>
                            </label>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="${pageContext.request.contextPath}/assets/js/script.js"></script>
    <script>
        // Get current date in YYYY-MM-DD format
        function getCurrentDate() {
            const today = new Date();
            const year = today.getFullYear();
            const month = String(today.getMonth() + 1).padStart(2, '0');
            const day = String(today.getDate()).padStart(2, '0');
            return `${year}-${month}-${day}`;
        }
        
        // Set current date in the header
        document.addEventListener('DOMContentLoaded', function() {
            const dateDisplay = document.querySelector('.date-display strong');
            if (dateDisplay) {
                dateDisplay.textContent = getCurrentDate();
            }
        });
    </script>
    
    <style>
        /* Additional styles for settings page */
        .settings-form {
            padding: 20px 0;
        }
        
        .form-group {
            margin-bottom: 20px;
        }
        
        .form-group label {
            display: block;
            margin-bottom: 8px;
            font-weight: 500;
        }
        
        .form-control {
            width: 100%;
            padding: 10px 15px;
            border: 1px solid #e0e0e0;
            border-radius: 5px;
            font-family: inherit;
            font-size: 14px;
        }
        
        textarea.form-control {
            resize: vertical;
        }
        
        .form-actions {
            display: flex;
            gap: 15px;
            margin-top: 30px;
        }
        
        .btn {
            padding: 10px 20px;
            border-radius: 5px;
            font-weight: 500;
            cursor: pointer;
            border: none;
            font-family: inherit;
            font-size: 14px;
        }
        
        .btn-primary {
            background-color: #4a90e2;
            color: white;
        }
        
        .btn-outline {
            border: 1px solid #e0e0e0;
            background-color: transparent;
            color: #555;
        }
        
        .notification-settings {
            padding: 20px 0;
        }
        
        .setting-item {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 15px 0;
            border-bottom: 1px solid #e0e0e0;
        }
        
        .setting-item:last-child {
            border-bottom: none;
        }
        
        .setting-info h4 {
            margin-bottom: 5px;
            font-size: 16px;
        }
        
        .setting-info p {
            color: #777;
            font-size: 14px;
        }
        
        /* Toggle Switch */
        .switch {
            position: relative;
            display: inline-block;
            width: 50px;
            height: 24px;
        }
        
        .switch input {
            opacity: 0;
            width: 0;
            height: 0;
        }
        
        .slider {
            position: absolute;
            cursor: pointer;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            background-color: #ccc;
            transition: .4s;
        }
        
        .slider:before {
            position: absolute;
            content: "";
            height: 16px;
            width: 16px;
            left: 4px;
            bottom: 4px;
            background-color: white;
            transition: .4s;
        }
        
        input:checked + .slider {
            background-color: #4a90e2;
        }
        
        input:focus + .slider {
            box-shadow: 0 0 1px #4a90e2;
        }
        
        input:checked + .slider:before {
            transform: translateX(26px);
        }
        
        .slider.round {
            border-radius: 34px;
        }
        
        .slider.round:before {
            border-radius: 50%;
        }
    </style>
</body>
</html>
