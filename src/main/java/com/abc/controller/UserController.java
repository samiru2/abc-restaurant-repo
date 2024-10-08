package com.abc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.abc.model.User;
import com.abc.service.UserService;
import com.abc.util.EmailUtility; 

@WebServlet(urlPatterns = {"/user", "/register"})
public class UserController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = UserService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String servletPath = request.getServletPath();

        if ("/register".equals(servletPath)) {
            showRegisterForm(request, response);
        } else if (action == null || action.equals("list")) {
            listUsers(request, response);
        } else if (action.equals("add")) {
            showAddForm(request, response);
        } else if (action.equals("edit")) {
            showEditForm(request, response);
        } else if (action.equals("delete")) {
            deleteUser(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action.equals("add")) {
            addUser(request, response);
        } else if (action.equals("update")) {
            updateUser(request, response);
        }
    }

    private void showRegisterForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/view/register.jsp").forward(request, response);
    }

    private void listUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> userList = new ArrayList<>();
        try {
            userList = userService.getAllUsers();
            request.setAttribute("users", userList);
            request.getRequestDispatcher("WEB-INF/view/listUsers.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("WEB-INF/view/error.jsp").forward(request, response);
        }
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/view/addUser.jsp").forward(request, response);
    }

    private void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String role = request.getParameter("role");
        String fromRegistrationPage = request.getParameter("fromRegistrationPage");

        try {
            if (userService.isPhoneNumberExists(phone)) {
                request.setAttribute("errorMessage", "Account already exists with this phone number.");
                showRegisterForm(request, response);
                return;
            }

            if (userService.isEmailExists(email)) {
                request.setAttribute("errorMessage", "Account already exists with this email.");
                showRegisterForm(request, response);
                return;
            }

            User user = new User(username, password, phone, email, role);
            userService.addUser(user);

            String subject = "Welcome to ABC Restaurant!";
            String message = "Dear " + username + ",\n\n"
                           + "You have successfully registered at ABC Restaurant.\n"
                           + "We are excited to have you as our customer.\n\n"
                           + "Best regards,\n"
                           + "ABC Restaurant Team";

            try {
                EmailUtility.sendEmail(email, subject, message);
                System.out.println("Registration email sent to " + email);
            } catch (Exception e) {
                System.out.println("Failed to send email: " + e.getMessage());
            }

            if ("true".equals(fromRegistrationPage) && "customer".equals(role)) {
                response.sendRedirect(request.getContextPath() + "/customerLogin");
            } else {
                response.sendRedirect(request.getContextPath() + "/user?action=list");
            }
        } catch (Exception e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("WEB-INF/view/error.jsp").forward(request, response);
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId;
        try {
            userId = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid User ID format.");
            request.getRequestDispatcher("WEB-INF/view/error.jsp").forward(request, response);
            return;
        }

        User existingUser = userService.getUserById(userId);
        if (existingUser == null) {
            request.setAttribute("errorMessage", "User not found.");
            request.getRequestDispatcher("WEB-INF/view/error.jsp").forward(request, response);
            return;
        }

        request.setAttribute("user", existingUser);
        request.getRequestDispatcher("WEB-INF/view/editUser.jsp").forward(request, response);
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId;
        try {
            userId = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid User ID format.");
            request.getRequestDispatcher("WEB-INF/view/error.jsp").forward(request, response);
            return;
        }

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String role = request.getParameter("role");

        User user = new User(userId, username, password, phone, email, role);

        try {
            userService.updateUser(user);
            response.sendRedirect("user?action=list");
        } catch (Exception e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("WEB-INF/view/error.jsp").forward(request, response);
        }
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId;
        try {
            userId = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid User ID format.");
            request.getRequestDispatcher("WEB-INF/view/error.jsp").forward(request, response);
            return;
        }

        try {
            userService.deleteUser(userId);
            response.sendRedirect("user?action=list");
        } catch (Exception e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("WEB-INF/view/error.jsp").forward(request, response);
        }
    }
}
