<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>List Reservations</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
    <style>
        .container {
            margin-top: 50px;
        }
        .btn-primary {
            margin-bottom: 15px;
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
        <h2>Reservation List</h2>
        <a href="reservation?action=add" class="btn btn-primary">Add New Reservation</a>
        <table class="table table-bordered">
            <thead>
                <tr class="table-dark">
                    <th>Reservation ID</th>
                    <th>User ID</th>
                    <th>Username</th>
                    <th>Date</th>
                    <th>Time</th>                 
                    <th>Phone</th>
                    <th>Email</th>                                     
                    <th>Number of People</th>                    
                    <th>Message</th>
                    <th>Status</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="reservation" items="${reservations}">
                    <tr>
                        <td>${reservation.reservationID}</td>
                        <td>${reservation.userID}</td>
                        <td>${reservation.username}</td>
                        <td>${reservation.date}</td>
                        <td>${reservation.time}</td>
                        <td>${reservation.phone}</td>
                        <td>${reservation.email}</td>                        
                        <td>${reservation.numberOfPeople}</td>                       
                        <td>${reservation.message}</td>
                        <td>${reservation.status}</td>
                        <td>
                            <a href="reservation?action=edit&id=${reservation.reservationID}" class="btn btn-warning btn-sm">Edit</a>
                            <a href="reservation?action=delete&id=${reservation.reservationID}" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this reservation?');">Delete</a>
                            <c:if test="${reservation.status == 'pending'}">
                                <a href="reservation?action=accept&id=${reservation.reservationID}" class="btn btn-success btn-sm">Accept</a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <a href="Dashboard" class="btn btn-secondary back-button">
        <i class="bi bi-arrow-left"></i> Back
    </a>
</body>
</html>
