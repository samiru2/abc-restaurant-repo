<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Confirmation</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <div class="card">
            <div class="card-header bg-success text-white">
                <h4>Order Confirmation</h4>
            </div>
            <div class="card-body">
                <p>Thank you <strong>${order.username}</strong>, for your order!</p>
                <p>Your order has been placed successfully. Here are the details:</p>
                
                <h5>Order Details</h5>
                <ul>
                    <li><strong>Order ID:</strong> ${order.orderID}</li>
                    <li><strong>Order Date:</strong> ${order.orderDate}</li>
                </ul>

                <h5>Items Ordered</h5>
                <ul>
                    <c:forEach var="item" items="${order.orderDetails}">
                        <li>${item}</li>
                    </c:forEach>
                </ul>
                <h5>Bill Summary</h5>
                <table class="table table-bordered">
                    <tr>
                        <th>Total</th>
                        <td>Rs. ${order.totalPrice}</td>
                    </tr>
                </table>
            </div>
            <div class="card-footer text-center">
                <a href="index" class="btn btn-primary">Continue Shopping</a>
                <a href="downloadBill?orderID=${order.orderID}" class="btn btn-info">Download Bill</a>
            </div>
        </div>
    </div>
</body>
</html>
