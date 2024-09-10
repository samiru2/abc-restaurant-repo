<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <style>
        body {
            background-color: #f8f9fa;
            text-align: center;
            padding-top: 50px;
            position: relative;
        }
        .container {
            max-width: 450px;
            margin: auto;
            padding-top: 50px;
        }
        .btn {
            margin-top: 20px;
        }
        .logout-btn,
        .download-btn {
            position: absolute;
            top: 10px;
            background: transparent;
            border: none;
            color: #dc3545;
        }
        .logout-btn {
            right: 10px;
        }
        .download-btn {
            left: 10px;
            background-color: #b71d1d;
            color: #fff;
            border-radius: 5px;
            padding: 5px 10px;
        }
        .download-btn:hover,
        .logout-btn:hover {
            color: #c82333;;
        }
    </style>
</head>
<body>
    <form action="order" method="get" class="d-inline">
        <button type="submit" name="action" value="downloadPdf" class="btn download-btn">
            Download Sales Report
        </button>
    </form>
    
    <form action="login" method="get" class="d-inline">
        <button type="submit" class="btn logout-btn">
            <i class="bi bi-box-arrow-right"></i>
        </button>
    </form>
    
    <div class="container">
        <c:choose>
            <c:when test="${userRole == 'admin'}">
                <h1 class="display-4">ABC Restaurant Admin Dashboard</h1>
                <a href="menu?action=list" class="btn btn-primary btn-lg">Menus</a>
                <a href="user?action=list" class="btn btn-primary btn-lg">Users</a>
                <a href="facility?action=list" class="btn btn-primary btn-lg">Facilities</a>
                <a href="offer?action=list" class="btn btn-primary btn-lg">Offers</a>
                <a href="gallery?action=list" class="btn btn-primary btn-lg">Gallery</a>
                <a href="reservation?action=list" class="btn btn-primary btn-lg">Reservations</a>
                <a href="order?action=list" class="btn btn-primary btn-lg">Orders</a>
            </c:when>
            <c:otherwise>
                <h1 class="display-4">ABC Restaurant Staff Dashboard</h1>
                <a href="reservation?action=list" class="btn btn-primary btn-lg">Reservations</a>
                <a href="order?action=list" class="btn btn-primary btn-lg">Orders</a>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>
