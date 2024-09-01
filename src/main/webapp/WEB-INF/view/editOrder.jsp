<%@ page import="com.abc.model.Order" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Order</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <style>
        .container {
            margin-top: 50px;
            max-width: 600px;
        }
        .btn-primary {
            margin-top: 20px;
        }
        .form-group {
            margin-bottom: 15px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1 class="mb-4">Edit Order</h1>
        <form action="order" method="post">
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="orderID" value="<%= ((Order)request.getAttribute("order")).getOrderID() %>">
            
            <div class="form-group">
                <label for="userID" class="form-label">User ID:</label>
                <input type="text" id="userID" name="userID" class="form-control" value="<%= ((Order)request.getAttribute("order")).getUserID() %>" required>
            </div>
            
            <div class="form-group">
                <label for="orderDetails" class="form-label">Order Details:</label>
                <input type="text" id="orderDetails" name="orderDetails" class="form-control" value="<%= ((Order)request.getAttribute("order")).getOrderDetails() %>" required>
            </div>
            
            <div class="form-group">
                <label for="orderDate" class="form-label">Order Date:</label>
                <input type="date" id="orderDate" name="orderDate" class="form-control" value="<%= ((Order)request.getAttribute("order")).getOrderDate() %>" required>
            </div>
            
            <div class="form-group">
                <label for="totalPrice" class="form-label">Total Price:</label>
                <input type="number" id="totalPrice" name="totalPrice" class="form-control" step="0.01" value="<%= ((Order)request.getAttribute("order")).getTotalPrice() %>" required>
            </div>
            
            <div class="form-group">
                <label for="status" class="form-label">Status:</label>
                <input type="text" id="status" name="status" class="form-control" value="<%= ((Order)request.getAttribute("order")).getStatus() %>" required>
            </div>
            
            <button type="submit" class="btn btn-primary">Update Order</button>
        </form>
        <a href="order?action=list" class="btn btn-secondary mt-3">Back to Order List</a>
    </div>
</body>
</html>
