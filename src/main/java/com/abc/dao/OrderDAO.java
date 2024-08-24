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

    // Method to add an order
    public void addOrder(Order order) {
        String query = "INSERT INTO orders (userID, menuID, type, totalPrice, status) VALUES (?, ?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, order.getUserID());
            statement.setInt(2, order.getMenuID());
            statement.setString(3, order.getType());
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

    // Method to get an order by ID
    public Order getOrderById(int orderID) {
        String query = "SELECT * FROM orders WHERE orderID = ?";
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
                        resultSet.getInt("menuID"),
                        resultSet.getString("type"),
                        resultSet.getDouble("totalPrice"),
                        resultSet.getString("status")
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

    // Method to update an order
    public void updateOrder(Order order) {
        String query = "UPDATE orders SET userID = ?, menuID = ?, type = ?, totalPrice = ?, status = ? WHERE orderID = ?";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, order.getUserID());
            statement.setInt(2, order.getMenuID());
            statement.setString(3, order.getType());
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

    // Method to delete an order
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

    // Method to get all orders
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM orders";
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
                        resultSet.getInt("menuID"),
                        resultSet.getString("type"),
                        resultSet.getDouble("totalPrice"),
                        resultSet.getString("status")
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
}
