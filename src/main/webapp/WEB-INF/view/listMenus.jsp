<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Menu List</title>
</head>
<body>
    <h2>Menu List</h2>
    <table border="1">
        <tr>
            <th>Menu ID</th>
            <th>Name</th>
            <th>Description</th>
            <th>Price</th>
            <th>Category</th>
            <th>Image</th>
        </tr>
        <c:forEach var="menu" items="${menus}">
            <tr>
                <td>${menu.menuID}</td>
                <td>${menu.name}</td>
                <td>${menu.description}</td>
                <td>${menu.price}</td>
                <td>${menu.category}</td>
                <td><img src="${menu.image}" alt="Menu Image" width="100" /></td>
            </tr>
        </c:forEach>
    </table>
    <br/>
    <a href="menu?action=add">Add New Menu Item</a>
</body>
</html>
