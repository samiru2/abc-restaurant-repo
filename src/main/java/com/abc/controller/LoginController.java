package com.abc.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abc.model.User;
import com.abc.service.UserService;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService;

    public LoginController() {
        this.userService = new UserService(); // Initialize the UserService instance
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Validate the login credentials using the UserService
        User user = userService.validateLogin(username, password);

        if (user != null) {
            // Create a session and store the logged-in user
            HttpSession session = request.getSession();
            session.setAttribute("loggedUser", user);

            // Redirect based on the user's role
            if (user.getRole().equalsIgnoreCase("admin")) {
                response.sendRedirect("AdminDashboard"); // Redirect to Admin Dashboard
            } else if (user.getRole().equalsIgnoreCase("staff")) {
                response.sendRedirect("StaffDashboard"); // Redirect to a view with restricted permissions
            }
        } else {
            // Redirect back to login with an error message
            response.sendRedirect("login.jsp?error=true");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response); // Handle both GET and POST requests the same way
    }
}
