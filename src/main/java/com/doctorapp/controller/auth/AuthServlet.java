package com.doctorapp.controller.auth;

import com.doctorapp.model.User;
import com.doctorapp.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(urlPatterns = {"/register", "/login", "/logout"})
public class AuthServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService;

    public void init() {
        userService = new UserService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action) {
            case "/register":
                showRegisterForm(request, response);
                break;
            case "/login":
                showLoginForm(request, response);
                break;
            case "/logout":
                logout(request, response);
                break;
            default:
                response.sendRedirect("index.jsp");
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action) {
            case "/register":
                registerUser(request, response);
                break;
            case "/login":
                loginUser(request, response);
                break;
            default:
                response.sendRedirect("index.jsp");
                break;
        }
    }

    private void showRegisterForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/register.jsp").forward(request, response);
    }

    private void showLoginForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    private void registerUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone"); // Will be used for patient/doctor details
        String role = request.getParameter("role") != null ? request.getParameter("role") : "PATIENT";

        User user = new User();
        user.setUsername(name);
        user.setEmail(email);
        user.setPassword(password); // Password will be hashed in the DAO
        user.setRole(role);

        boolean registrationSuccess = userService.registerUser(user);

        if (registrationSuccess) {
            // Get the user ID for additional details
            User registeredUser = userService.getUserByEmail(email);

            if (registeredUser != null) {
                int userId = registeredUser.getId();

                if ("PATIENT".equals(role)) {
                    // Handle patient-specific fields
                    String dateOfBirth = request.getParameter("dateOfBirth");
                    String gender = request.getParameter("gender");
                    String address = request.getParameter("address");
                    String bloodGroup = request.getParameter("bloodGroup");
                    String allergies = request.getParameter("allergies");

                    // Save patient details to database
                    registrationSuccess = userService.savePatientDetails(userId, dateOfBirth, gender, address, bloodGroup, allergies);
                } else if ("DOCTOR".equals(role)) {
                    // Handle doctor-specific fields
                    String specialization = request.getParameter("specialization");
                    String qualification = request.getParameter("qualification");
                    String experience = request.getParameter("experience");
                    String address = request.getParameter("address");
                    String bio = request.getParameter("bio");

                    // Save doctor details to database
                    registrationSuccess = userService.saveDoctorDetails(userId, specialization, qualification, experience, address, bio);
                }
            }

            if (registrationSuccess) {
                request.setAttribute("message", "Registration successful! Please login.");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                return;
            }
        }

        // If we get here, something went wrong
        request.setAttribute("error", "Registration failed. Please try again.");
        request.getRequestDispatcher("/register.jsp").forward(request, response);
    }

    private void loginUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            User user = userService.login(email, password);

            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);

                // Redirect to the appropriate dashboard based on role
                String role = user.getRole();
                String dashboardUrl = request.getContextPath() + "/dashboard";

                switch (role) {
                    case "ADMIN":
                    case "DOCTOR":
                    case "PATIENT":
                        response.sendRedirect(dashboardUrl);
                        break;
                    default:
                        // Invalid role, redirect to login
                        session.invalidate();
                        request.setAttribute("error", "Invalid user role: " + role);
                        request.getRequestDispatcher("/login.jsp").forward(request, response);
                        break;
                }
            } else {
                request.setAttribute("error", "Invalid email or password");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("error", "An error occurred during login: " + e.getMessage());
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect("index.jsp");
    }
}
