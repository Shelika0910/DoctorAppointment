<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Patients - HealthCare</title>
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
                <li class="active">
                    <a href="patients">
                        <i class="fas fa-user-injured"></i>
                        <span>My Patients</span>
                    </a>
                </li>
                <li>
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
                <h1>My Patients</h1>
                <div class="date-display">
                    <span>Today's Date:</span>
                    <strong>2023-10-23</strong>
                    <i class="far fa-calendar-alt"></i>
                </div>
            </div>
            
            <div class="content-section">
                <div class="section-header">
                    <h2>Patient List</h2>
                </div>
                
                <div class="filter-section">
                    <div class="date-filter">
                        <span>Search:</span>
                        <input type="text" placeholder="Search patients..." class="date-input">
                    </div>
                    <div class="filter-buttons">
                        <button class="filter-btn"><i class="fas fa-filter"></i> Filter</button>
                    </div>
                </div>
                
                <div class="table-container">
                    <table class="data-table">
                        <thead>
                            <tr>
                                <th>Patient ID</th>
                                <th>Patient Name</th>
                                <th>Contact</th>
                                <th>Last Visit</th>
                                <th>Status</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>P001</td>
                                <td>Raj PV</td>
                                <td>raj@example.com</td>
                                <td>2023-10-18</td>
                                <td>Active</td>
                                <td>
                                    <button class="view-btn"><i class="fas fa-eye"></i> View</button>
                                </td>
                            </tr>
                            <tr>
                                <td>P002</td>
                                <td>Madhu Mathi</td>
                                <td>madhu@example.com</td>
                                <td>2023-10-18</td>
                                <td>Active</td>
                                <td>
                                    <button class="view-btn"><i class="fas fa-eye"></i> View</button>
                                </td>
                            </tr>
                            <tr>
                                <td>P003</td>
                                <td>Anjana P</td>
                                <td>anjana@example.com</td>
                                <td>2023-10-23</td>
                                <td>Active</td>
                                <td>
                                    <button class="view-btn"><i class="fas fa-eye"></i> View</button>
                                </td>
                            </tr>
                            <tr>
                                <td>P004</td>
                                <td>Hari S</td>
                                <td>hari@example.com</td>
                                <td>2023-10-23</td>
                                <td>Active</td>
                                <td>
                                    <button class="view-btn"><i class="fas fa-eye"></i> View</button>
                                </td>
                            </tr>
                            <tr>
                                <td>P005</td>
                                <td>Keerthi V</td>
                                <td>keerthi@example.com</td>
                                <td>2023-10-23</td>
                                <td>Active</td>
                                <td>
                                    <button class="view-btn"><i class="fas fa-eye"></i> View</button>
                                </td>
                            </tr>
                            <tr>
                                <td>P006</td>
                                <td>Aswin S</td>
                                <td>aswin@example.com</td>
                                <td>2023-10-23</td>
                                <td>Active</td>
                                <td>
                                    <button class="view-btn"><i class="fas fa-eye"></i> View</button>
                                </td>
                            </tr>
                        </tbody>
                    </table>
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
</body>
</html>
