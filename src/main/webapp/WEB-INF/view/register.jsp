<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
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
        .register-container {
            width: 100%;
            max-width: 500px; 
            padding: 30px;
            background: #8aff84;
            border-radius: 10px;
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.2);
            text-align: center;
        }
        .register-container h1 {
            margin-bottom: 1.5rem;
        }
        .form-label {
            font-weight: bold;
        }
        .register-btn {
            width: 100%;
            margin-top: 20px;
        }
        .login-link {
            margin-top: 20px;
        }
        .login-link a {
            text-decoration: none;
            color: #007bff;
        }
        .login-link a:hover {
            text-decoration: underline;
        }
        .error-message {
            color: red;
            font-size: 0.875rem;
        }
    </style>
</head>
<body>
    <div class="register-container">
        <h1 class="mb-4">Register</h1>
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger error-message mb-3">
                ${errorMessage}
            </div>
        </c:if>
        <form id="registrationForm" action="${pageContext.request.contextPath}/user" method="post">
            <input type="hidden" name="action" value="add">
            <input type="hidden" name="role" value="customer">
            <input type="hidden" name="fromRegistrationPage" value="true">
            <div class="mb-3">
                <label for="username" class="form-label">Username</label>
                <input type="text" class="form-control" id="username" name="username" placeholder="Enter your username" required>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input type="password" class="form-control" id="password" name="password" placeholder="Enter your password" required>
            </div>
            <div class="mb-3">
                <label for="retypePassword" class="form-label">Re-type Password</label>
                <input type="password" class="form-control" id="retypePassword" name="retypePassword" placeholder="Re-type your password" required>
            </div>
            <div class="mb-3">
                <label for="phone" class="form-label">Phone</label>
                <input type="text" class="form-control" id="phone" name="phone" placeholder="Enter your phone number" required>
            </div>
            <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <input type="email" class="form-control" id="email" name="email" placeholder="Enter your email address" required>
            </div>
            <button type="submit" class="btn btn-primary register-btn">Register</button>
            <div class="login-link">
                <p class="mb-0">Already have an account?</p>
                <a href="customerLogin">Log In</a>
            </div>
        </form>
    </div>

    <script>
        document.getElementById('registrationForm').addEventListener('submit', function(event) {
            var password = document.getElementById('password').value;
            var retypePassword = document.getElementById('retypePassword').value;
            
            if (password !== retypePassword) {
                event.preventDefault(); // Prevent form submission
                alert('Passwords do not match. Please try again.');
            }
        });
    </script>
</body>
</html>
