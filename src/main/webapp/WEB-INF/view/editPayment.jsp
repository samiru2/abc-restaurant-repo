<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Payment</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
    <div class="container mt-4">
        <h2>Edit Payment</h2>
        <form action="payment?action=update" method="post">
            <input type="hidden" name="paymentID" value="${payment.paymentID}">
            <div class="form-group">
                <label for="orderID">Order ID:</label>
                <input type="number" class="form-control" id="orderID" name="orderID" value="${payment.orderID}" required>
            </div>
            <div class="form-group">
                <label for="dateTime">DateTime:</label>
                <input type="text" class="form-control" id="dateTime" name="dateTime" value="${payment.dateTime}" required>
            </div>
            <div class="form-group">
                <label for="paymentMethod">Payment Method:</label>
                <input type="text" class="form-control" id="paymentMethod" name="paymentMethod" value="${payment.paymentMethod}" required>
            </div>
            <div class="form-group">
                <label for="price">Price:</label>
                <input type="number" step="0.01" class="form-control" id="price" name="price" value="${payment.price}" required>
            </div>
            <button type="submit" class="btn btn-primary mt-3">Update Payment</button>
        </form>
    </div>
</body>
</html>
