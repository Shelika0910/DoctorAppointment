package com.doctorapp.controller.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/error-handler")
public class ErrorHandlerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processError(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processError(request, response);
    }

    private void processError(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Log the error and get error details from request
        Throwable throwable = (Throwable) request.getAttribute("jakarta.servlet.error.exception");
        if (throwable != null) {
            System.err.println("Error in application: " + throwable.getMessage());
            throwable.printStackTrace();
        }
        Integer statusCode = (Integer) request.getAttribute("jakarta.servlet.error.status_code");
        String servletName = (String) request.getAttribute("jakarta.servlet.error.servlet_name");
        String requestUri = (String) request.getAttribute("jakarta.servlet.error.request_uri");

        // Set response content type
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Error Details</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; margin: 20px; line-height: 1.6; }");
        out.println(".container { max-width: 800px; margin: 0 auto; padding: 20px; border: 1px solid #ddd; border-radius: 5px; }");
        out.println("h1 { color: #d9534f; }");
        out.println("h2 { color: #333; margin-top: 20px; }");
        out.println(".error-details { background-color: #f8f9fa; padding: 15px; border-radius: 4px; margin-top: 20px; }");
        out.println(".error-message { color: #d9534f; font-weight: bold; }");
        out.println(".stack-trace { font-family: monospace; white-space: pre-wrap; background-color: #f1f1f1; padding: 10px; border-radius: 4px; margin-top: 10px; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='container'>");
        out.println("<h1>Error Information</h1>");

        out.println("<div class='error-details'>");
        if(statusCode != null) {
            out.println("<h2>Status Code</h2>");
            out.println("<p>" + statusCode + "</p>");
        }

        if(requestUri != null) {
            out.println("<h2>Request URI</h2>");
            out.println("<p>" + requestUri + "</p>");
        }

        if(servletName != null) {
            out.println("<h2>Servlet Name</h2>");
            out.println("<p>" + servletName + "</p>");
        }

        if(throwable != null) {
            out.println("<h2>Exception</h2>");
            out.println("<p class='error-message'>" + throwable.getClass().getName() + ": " + throwable.getMessage() + "</p>");

            // Special handling for JasperException
            if (throwable.getClass().getName().contains("JasperException")) {
                out.println("<h2>Possible Solutions for JasperException</h2>");
                out.println("<ul>");
                out.println("<li>Check for syntax errors in your JSP files</li>");
                out.println("<li>Ensure all JSP tags are properly closed</li>");
                out.println("<li>Verify that all EL expressions are valid</li>");
                out.println("<li>Make sure all required JAR files are in the classpath</li>");
                out.println("<li>Clear the server's work directory and restart</li>");
                out.println("</ul>");
            }

            out.println("<h2>Stack Trace</h2>");
            out.println("<div class='stack-trace'>");
            for(StackTraceElement element : throwable.getStackTrace()) {
                out.println(element.toString());
            }
            out.println("</div>");
        }
        out.println("</div>");

        out.println("<p><a href='" + request.getContextPath() + "/index.jsp'>Go to Home</a></p>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}
