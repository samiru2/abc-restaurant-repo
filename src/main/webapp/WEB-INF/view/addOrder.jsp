<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Order</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
    <div class="container mt-5">
        <h2>Add Order</h2>
        <form action="order?action=add" method="post">
            <div class="mb-3">
                <label for="userID" class="form-label">User ID:</label>
                <input type="number" class="form-control" id="userID" name="userID" required>
            </div>
            <div class="mb-3">
                <label for="menuID" class="form-label">Menu ID:</label>
                <input type="number" class="form-control" id="menuID" name="menuID" required>
            </div>
            <div class="mb-3">
                <label for="type" class="form-label">Type:</label>
                <input type="text" class="form-control" id="type" name="type" required>
            </div>
            <div class="mb-3">
                <label for="totalPrice" class="form-label">Total Price:</label>
                <input type="number" step="0.01" class="form-control" id="totalPrice" name="totalPrice" required>
            </div>
            <div class="mb-3">
                <label for="status" class="form-label">Status:</label>
                <input type="text" class="form-control" id="status" name="status">
            </div>
            <button type="submit" class="btn btn-primary">Add Order</button>
        </form>
    </div>
</body>
</html>
