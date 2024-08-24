<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Payment</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        function setCurrentDateTime() {
            var now = new Date();
            var year = now.getFullYear();
            var month = ("0" + (now.getMonth() + 1)).slice(-2);
            var day = ("0" + now.getDate()).slice(-2);
            var hours = ("0" + now.getHours()).slice(-2);
            var minutes = ("0" + now.getMinutes()).slice(-2);
            var seconds = ("0" + now.getSeconds()).slice(-2);
            
            var dateTime = year + "-" + month + "-" + day + " " + hours + ":" + minutes + ":" + seconds;
            document.getElementById("dateTime").value = dateTime;
        }
    </script>
</head>
<body>
    <div class="container mt-4">
        <h2>Add Payment</h2>
        <form action="payment?action=add" method="post" onsubmit="setCurrentDateTime()">
            <div class="form-group">
                <label for="orderID">Order ID:</label>
                <input type="number" class="form-control" id="orderID" name="orderID" required>
            </div>
            <div class="form-group">
                <label for="dateTime">DateTime:</label>
                <input type="hidden" id="dateTime" name="dateTime">
            </div>
            <div class="form-group">
                <label for="paymentMethod">Payment Method:</label>
                <input type="text" class="form-control" id="paymentMethod" name="paymentMethod" required>
            </div>
            <div class="form-group">
                <label for="price">Price:</label>
                <input type="number" step="0.01" class="form-control" id="price" name="price" required>
            </div>
            <button type="submit" class="btn btn-primary mt-3">Add Payment</button>
        </form>
    </div>
</body>
</html>
