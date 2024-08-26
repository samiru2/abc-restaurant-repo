<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <style>
        body {
            background-color: #f8f9fa;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .login-container {
            width: 100%;
            max-width: 400px;
            padding: 20px;
            background: white;
            border-radius: 8px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        .error-message {
            color: red;
        }
        .login-btn {
            width: 100%;
            margin-top: 20px;
        }
        .create-account {
            margin-top: 15px;
        }
        .create-account a {
            text-decoration: none;
            color: #007bff;
        }
        .create-account a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <h2 class="mb-4">Login</h2>
        <form action="login" method="post">
            <div class="mb-3">
                <input type="text" class="form-control" id="username" name="username" placeholder="Username" required>
            </div>
            <div class="mb-3">
                <input type="password" class="form-control" id="password" name="password" placeholder="Password" required>
            </div>
            <% if ("true".equals(request.getParameter("error"))) { %>
                <div class="error-message mb-3">Invalid username or password. Please try again.</div>
            <% } %>
            <button type="submit" class="btn btn-primary login-btn">Login</button>
            <div class="create-account">
                <p class="mb-0">Don't have an account?</p>
                <a href="register.jsp">Create Account</a>
            </div>
        </form>
    </div>
</body>
</html>
