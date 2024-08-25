<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>List Menus</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
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
    </style>
</head>
<body>
    <div class="container">
        <h2>Menu List</h2>
        <a href="menu?action=add" class="btn btn-primary">Add New Menu</a>
        <table class="table table-bordered">
            <thead>
                <tr class="table-dark">
                    <th>ID</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>Category</th>
                    <th>Image</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="menu" items="${menus}">
                    <tr>
                        <td>${menu.menuId}</td>
                        <td>${menu.name}</td>
                        <td>${menu.description}</td>
                        <td class="text-right">${menu.price}</td>
                        <td>${menu.category}</td>
                        <td><img src="${menu.image}" alt="${menu.name}" /></td>
                        <td>
                            <a href="menu?action=edit&id=${menu.menuId}" class="btn btn-warning btn-sm">Edit</a>
                            <a href="menu?action=delete&id=${menu.menuId}" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this menu?');">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
