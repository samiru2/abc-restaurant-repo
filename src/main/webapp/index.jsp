<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <title>Management Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <style>
        body {
            background-color: #f8f9fa;
            text-align: center;
            padding-top: 50px;
        }
        .container {
            max-width: 400px;
            margin: auto;
        }
        .btn {
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1 class="display-5">Welcome to Management Dashboard</h1>
        <p class="lead">Efficiently manage your menus and users with our tool.</p>
        <a href="menu?action=list" class="btn btn-primary btn-lg">View Menus</a>
        <a href="menu?action=add" class="btn btn-secondary btn-lg">Add New Menu</a>
        <a href="user?action=list" class="btn btn-success btn-lg">View Users</a>
        <a href="user?action=add" class="btn btn-info btn-lg">Add New User</a>
        <a href="facility?action=list" class="btn btn-warning btn-lg">View Facilities</a>
        <a href="facility?action=add" class="btn btn-danger btn-lg">Add New Facility</a>
        <a href="offer?action=list" class="btn btn-dark btn-lg">View Offers</a>
        <a href="offer?action=add" class="btn btn-light btn-lg">Add New Offer</a>
        <a href="reservation?action=list" class="btn btn-primary btn-lg">View Reservations</a>
        <a href="reservation?action=add" class="btn btn-secondary btn-lg">Add New Reservation</a>
        <a href="order?action=list" class="btn btn-dark btn-lg">View Orders</a>
        <a href="order?action=add" class="btn btn-light btn-lg">Add New Order</a>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
</body>
</html>
