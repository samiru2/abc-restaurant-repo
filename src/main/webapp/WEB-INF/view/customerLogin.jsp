<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Customer Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <style>
        body {
            background: linear-gradient(135deg, #242424, #373737);
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .login-container {
            width: 100%;
            max-width: 360px;
            padding: 30px;
            background: #8aff84;
            border-radius: 10px;
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.2);
            text-align: center;
        }
        .login-container h2 {
            margin-bottom: 1.5rem;
        }
        .form-label {
            font-weight: bold;
        }
        .login-btn {
            width: 100%;
            margin-top: 20px;
        }
        .create-account {
            margin-top: 20px;
        }
        .create-account a {
            text-decoration: none;
            color: #007bff;
        }
        .create-account a:hover {
            text-decoration: underline;
        }
        .error-message {
            color: red;
            font-size: 0.875rem;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <h2 class="mb-4">Customer Sign In</h2>
        <form action="customerLogin" method="post">
            <div class="mb-3">
                <label for="username" class="form-label">Username</label>
                <input type="text" class="form-control" id="username" name="username" placeholder="Enter your username" required>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input type="password" class="form-control" id="password" name="password" placeholder="Enter your password" required>
            </div>
            <% if (request.getAttribute("error") != null) { %>
                <div class="error-message mb-3"><%= request.getAttribute("error") %></div>
            <% } %>
            <button type="submit" class="btn btn-primary login-btn">Log In</button>
            <div class="create-account">
                <p class="mb-0">Don't have an account?</p>
                <a href="register">Sign Up</a>
            </div>
        </form>
    </div>
</body>
</html>
