<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>List Offers</title>
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
        <h2>Offer List</h2>
        <a href="offer?action=add" class="btn btn-primary">Add New Offer</a>
        <table class="table table-bordered">
            <thead>
                <tr class="table-dark">
                    <th>ID</th>
                    <th>Title</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>Image</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="offer" items="${offers}">
                    <tr>
                        <td>${offer.offerId}</td>
                        <td>${offer.title}</td>
                        <td>${offer.description}</td>
                        <td class="text-right">${offer.price}</td>
                        <td><img src="${offer.image}" alt="${offer.title}" /></td>
                        <td>
                            <a href="offer?action=edit&id=${offer.offerId}" class="btn btn-warning btn-sm">Edit</a>
                            <a href="offer?action=delete&id=${offer.offerId}" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this offer?');">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
