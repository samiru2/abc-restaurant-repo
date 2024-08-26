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

@WebServlet(urlPatterns = {"/login", "/Dashboard"})
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        // Initialize the UserService using the singleton pattern
        userService = UserService.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getServletPath().equals("/login")) {
            handleLogin(request, response);
        } else if (request.getServletPath().equals("/Dashboard")) {
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
            session.setAttribute("userRole", user.getRole());

            response.sendRedirect("Dashboard");
        } else {
            request.setAttribute("error", true);
            request.getRequestDispatcher("WEB-INF/view/login.jsp").forward(request, response);
        }
    }

    private void handleDashboard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User loggedUser = (User) session.getAttribute("loggedUser");

        if (loggedUser != null) {
            request.getRequestDispatcher("WEB-INF/view/Dashboard.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("WEB-INF/view/login.jsp").forward(request, response);
        }
    }
}
