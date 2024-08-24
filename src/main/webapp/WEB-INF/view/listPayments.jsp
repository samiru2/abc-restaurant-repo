<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>List Payments</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <style>
        .container {
            margin-top: 50px;
        }
        .btn-primary {
            margin-bottom: 15px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Payments List</h2>
        <a href="payment?action=add" class="btn btn-primary">Add New Payment</a>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Payment ID</th>
                    <th>Order ID</th>
                    <th>Date and Time</th>
                    <th>Payment Method</th>
                    <th>Price</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="payment" items="${payments}">
                    <tr>
                        <td>${payment.paymentID}</td>
                        <td>${payment.orderID}</td>
                        <td>${payment.dateTime}</td>
                        <td>${payment.paymentMethod}</td>
                        <td>${payment.price}</td>
                        <td>
                            <a href="payment?action=edit&id=${payment.paymentID}" class="btn btn-warning btn-sm">Edit</a>
                            <a href="payment?action=delete&id=${payment.paymentID}" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this payment?');">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
