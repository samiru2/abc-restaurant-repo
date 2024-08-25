<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta http-equiv="refresh" content="3">
<!DOCTYPE html>
<html>
<head>
    <title>List Facilities</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet"> <!-- Bootstrap Icons -->
    <style>
        .container {
            margin-top: 50px;
        }
        .btn-primary {
            margin-bottom: 15px;
        }
        .table img {
            max-width: 100px;
        }
        .back-button {
            position: fixed;
            bottom: 20px;
            right: 20px;
            z-index: 1000;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Facility List</h2>
        <a href="facility?action=add" class="btn btn-primary">Add New Facility</a>
        <table class="table table-bordered">
            <thead>
                <tr class="table-dark">
                    <th>ID</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Image</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="facility" items="${facilities}">
                    <tr>
                        <td>${facility.facilityId}</td>
                        <td>${facility.name}</td>
                        <td>${facility.description}</td>
                        <td><img src="${facility.image}" alt="${facility.name}" /></td>
                        <td>
                            <a href="facility?action=edit&id=${facility.facilityId}" class="btn btn-warning btn-sm">Edit</a>
                            <a href="facility?action=delete&id=${facility.facilityId}" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this facility?');">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <a href="AdminDashboard.jsp" class="btn btn-secondary back-button">
        <i class="bi bi-arrow-left"></i> Back
    </a>
</body>
</html>
