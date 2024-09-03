<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="com.abc.model.Order" %>
<!DOCTYPE html>
<html>
<head>
    <title>List Orders</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <style>
        body {
            margin-top: 20px;
        }
        .container {
            margin-top: 30px;
        }
        .btn-primary {
            margin-bottom: 15px;
        }
        .btn-actions a {
            margin-right: 10px;
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
        <h1 class="mb-4">Order List</h1>
        
        <c:choose>
            <c:when test="${userRole == 'admin'}">
                <a href="order?action=add" class="btn btn-primary mb-3">Add New Order</a>
            </c:when>
        </c:choose>
        
        <div class="table-responsive">
            <table class="table table-bordered table-hover">
                <thead class="table-dark">
                    <tr>
                        <th>Order ID</th>
                        <th>User ID</th>
                        <th>Order Date</th>
                        <th>Username</th>
                        <th>Phone</th>
                        <th>Email</th>
                        <th>Order Details</th>
                        <th>Total Price</th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="order" items="${orders}">
                    <tr>
                        <td>${order.orderID}</td>
                        <td>${order.userID}</td>
						<td>${order.orderDate}</td>
                        <td>${order.username}</td>
                        <td>${order.phone}</td>
                        <td>${order.email}</td>
                        <td>${order.orderDetails}</td>
                        <td>${order.totalPrice}</td>
                        <td>${order.status}</td>
                        <td class="btn-actions">
                            <a href="order?action=edit&orderID=${order.orderID}" class="btn btn-warning btn-sm">Edit</a>
                            <a href="order?action=delete&orderID=${order.orderID}" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this order?');">Delete</a>
                            <c:if test="${order.status == 'pending'}">
                                <form action="order" method="get" style="display:inline;">
                                    <input type="hidden" name="orderID" value="${order.orderID}" />
                                    <input type="hidden" name="action" value="accept" />
                                    <button type="submit" class="btn btn-success btn-sm">Accept</button>
                                </form>
                            </c:if>
                        </td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        
    </div>
    <a href="Dashboard" class="btn btn-secondary back-button">
        <i class="bi bi-arrow-left"></i> Back
    </a>
</body>
</html>
