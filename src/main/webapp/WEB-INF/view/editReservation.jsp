<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Reservation</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <style>
        .container {
            margin-top: 50px;
            max-width: 600px;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .btn-primary {
            width: 100%;
        }
    </style>
</head>
<body>
    <div class="container">
        <form action="reservation?action=update" method="post">
            <input type="hidden" name="id" value="${reservation.reservationID}">
            <div class="form-group">
                <label for="userID">User ID:</label>
                <input type="number" class="form-control" id="userID" name="userID" value="${reservation.userID}" required>
            </div>
            <div class="form-group">
                <label for="date">Date:</label>
                <input type="text" class="form-control" id="date" name="date" value="${reservation.date}" required>
            </div>
            <div class="form-group">
                <label for="time">Time:</label>
                <input type="text" class="form-control" id="time" name="time" value="${reservation.time}" required>
            </div>
            <div class="form-group">
                <label for="numberOfPeople">Number of People:</label>
                <input type="number" class="form-control" id="numberOfPeople" name="numberOfPeople" value="${reservation.numberOfPeople}" required>
            </div>
            <div class="form-group">
                <label for="status">Status:</label>
                <input type="text" class="form-control" id="status" name="status" value="${reservation.status}">
            </div>
            <div class="form-group">
                <label for="message">Message:</label>
                <textarea class="form-control" id="message" name="message">${reservation.message}</textarea>
            </div>
            <button type="submit" class="btn btn-primary mt-3">Update Reservation</button>
        </form>
    </div>
</body>
</html>
