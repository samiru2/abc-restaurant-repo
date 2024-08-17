<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            color: #333;
            text-align: center;
            padding: 50px;
        }
        h1 {
            color: #e74c3c;
        }
        p {
            font-size: 1.2em;
        }
        a {
            color: #3498db;
            text-decoration: none;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <h1>Something Went Wrong</h1>
    <p>We encountered an error while processing your request:</p>
    <p><%= request.getAttribute("errorMessage") %></p>
    <p><a href="menu?action=list">Return to Menu List</a></p>
</body>
</html>