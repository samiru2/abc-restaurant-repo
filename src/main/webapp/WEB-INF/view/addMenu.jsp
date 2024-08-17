<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Menu Item</title>
</head>
<body>
    <h2>Add New Menu Item</h2>
    <form action="menu" method="post">
        <input type="hidden" name="action" value="add">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required><br><br>
        
        <label for="description">Description:</label>
        <input type="text" id="description" name="description" required><br><br>
        
        <label for="price">Price:</label>
        <input type="number" step="0.01" id="price" name="price" required><br><br>
        
        <label for="category">Category:</label>
        <input type="text" id="category" name="category" required><br><br>
        
        <label for="image">Image URL:</label>
        <input type="text" id="image" name="image"><br><br>
        
        <input type="submit" value="Add Menu">
    </form>
    <br/>
    <a href="menu?action=list">Back to Menu List</a>
</body>
</html>
