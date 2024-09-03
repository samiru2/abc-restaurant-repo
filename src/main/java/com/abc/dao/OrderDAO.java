package com.abc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.abc.model.Order;

public class OrderDAO {

    public void addOrder(Order order) {
        String query = "INSERT INTO orders (userID, orderDetails, orderDate, totalPrice, status) VALUES (?, ?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, order.getUserID());
            statement.setString(2, order.getOrderDetails());
            statement.setString(3, order.getOrderDate());
            statement.setDouble(4, order.getTotalPrice());
            statement.setString(5, order.getStatus());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Order getOrderById(int orderID) {
        String query = "SELECT o.*, u.username, u.phone, u.email FROM orders o JOIN users u ON o.userID = u.userID WHERE o.orderID = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Order order = null;

        try {
            connection = DBConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, orderID);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                order = new Order(
                        resultSet.getInt("orderID"),
                        resultSet.getInt("userID"),
                        resultSet.getString("orderDetails"),
                        resultSet.getString("orderDate"),
                        resultSet.getDouble("totalPrice"),
                        resultSet.getString("status"),
                        resultSet.getString("username"),
                        resultSet.getString("phone"),
                        resultSet.getString("email")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return order;
    }

    public void updateOrder(Order order) {
        String query = "UPDATE orders SET userID = ?, orderDetails = ?, orderDate = ?, totalPrice = ?, status = ? WHERE orderID = ?";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, order.getUserID());
            statement.setString(2, order.getOrderDetails());
            statement.setString(3, order.getOrderDate());
            statement.setDouble(4, order.getTotalPrice());
            statement.setString(5, order.getStatus());
            statement.setInt(6, order.getOrderID());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteOrder(int orderID) {
        String query = "DELETE FROM orders WHERE orderID = ?";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, orderID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT o.*, u.username, u.phone, u.email FROM orders o JOIN users u ON o.userID = u.userID ORDER BY o.orderID DESC";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnectionFactory.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Order order = new Order(
                        resultSet.getInt("orderID"),
                        resultSet.getInt("userID"),
                        resultSet.getString("orderDetails"),
                        resultSet.getString("orderDate"),
                        resultSet.getDouble("totalPrice"),
                        resultSet.getString("status"),
                        resultSet.getString("username"),
                        resultSet.getString("phone"),
                        resultSet.getString("email")
                );
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return orders;
    }
    
    public void acceptOrder(int orderID) {
        String query = "UPDATE orders SET status = 'accepted' WHERE orderID = ?";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, orderID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
