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

@WebServlet(urlPatterns = {"/login", "/Dashboard", "/customerLogin"})
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
        String servletPath = request.getServletPath();

        if ("/login".equals(servletPath)) {
            handleLogin(request, response);
        } else if ("/Dashboard".equals(servletPath)) {
            handleDashboard(request, response);
        } else if ("/customerLogin".equals(servletPath)) {
            handleCustomerLogin(request, response);
        } else if ("/index".equals(servletPath)) {
            handleIndex(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath();
        
        if (servletPath.equals("/login")) {
            request.removeAttribute("error");
            request.getRequestDispatcher("WEB-INF/view/login.jsp").forward(request, response);
            
        } else if (servletPath.equals("/Dashboard")) {
            doPost(request, response);
            
        } else if (servletPath.equals("/customerLogin")) {
            request.removeAttribute("error");
            request.getRequestDispatcher("WEB-INF/view/customerLogin.jsp").forward(request, response);
        }
    }

    private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userService.validateLogin(username, password);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("loggedUser", user);
            session.setAttribute("userRole", user.getRole());
            session.setAttribute("userID", user.getUserId());

            if ("customer".equals(user.getRole())) {
                response.sendRedirect("index");
            } else {
                response.sendRedirect("Dashboard");
            }
        } else {
            request.setAttribute("error", "Invalid username or password. Please try again.");
            request.getRequestDispatcher("WEB-INF/view/login.jsp").forward(request, response);
        }
    }

    private void handleDashboard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User loggedUser = (User) session.getAttribute("loggedUser");

        if (loggedUser != null && ("admin".equals(loggedUser.getRole()) || "staff".equals(loggedUser.getRole()))) {
            request.getRequestDispatcher("WEB-INF/view/Dashboard.jsp").forward(request, response);
        } else {
            request.removeAttribute("error");
            response.sendRedirect("login");
        }
    }
    
    private void handleCustomerLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userService.validateLogin(username, password);

        if (user != null && "customer".equals(user.getRole())) {
            HttpSession session = request.getSession();
            session.setAttribute("loggedUser", user);
            session.setAttribute("userRole", user.getRole());
            session.setAttribute("userID", user.getUserId());

            response.sendRedirect("index");
        } else {
            request.setAttribute("error", "Invalid username or password. Please try again.");
            request.getRequestDispatcher("WEB-INF/view/customerLogin.jsp").forward(request, response);
        }
    }
    
    private void handleIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User loggedUser = (User) session.getAttribute("loggedUser");

        if (loggedUser != null && "customer".equals(loggedUser.getRole())) {
            request.getRequestDispatcher("WEB-INF/view/index.jsp").forward(request, response);
        } else {
            response.sendRedirect("login");
        }
    }
}
