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

@WebServlet(urlPatterns = {"/login", "/AdminDashboard", "/StaffDashboard"})
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService;

    @Override
    public void init() throws ServletException {
    	//Initialize singleton pattern
        userService = UserService.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getServletPath().equals("/login")) {
            handleLogin(request, response);
        } else {
            handleDashboard(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userService.validateLogin(username, password);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("loggedUser", user);

            if (user.getRole().equalsIgnoreCase("admin")) {
                response.sendRedirect("AdminDashboard");
            } else if (user.getRole().equalsIgnoreCase("staff")) {
                response.sendRedirect("StaffDashboard");
            }
        } else {
            response.sendRedirect("login.jsp?error=true");
        }
    }

    private void handleDashboard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User loggedUser = (User) session.getAttribute("loggedUser");

        if (loggedUser != null) {
            if (request.getServletPath().equals("/AdminDashboard")) {
                if (loggedUser.getRole().equalsIgnoreCase("admin")) {
                    request.getRequestDispatcher("WEB-INF/view/AdminDashboard.jsp").forward(request, response);
                } else {
                    response.sendRedirect("StaffDashboard");
                }
            } else if (request.getServletPath().equals("/StaffDashboard")) {
                if (loggedUser.getRole().equalsIgnoreCase("staff")) {
                    request.getRequestDispatcher("WEB-INF/view/StaffDashboard.jsp").forward(request, response);
                } else {
                    response.sendRedirect("AdminDashboard");
                }
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
