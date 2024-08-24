package com.abc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.abc.model.Payment;

public class PaymentDAO {

    // Method to add a payment
    public void addPayment(Payment payment) {
        String query = "INSERT INTO payments (orderID, dateTime, paymentMethod, price) VALUES (?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, payment.getOrderID());
            statement.setString(2, payment.getDateTime());
            statement.setString(3, payment.getPaymentMethod());
            statement.setDouble(4, payment.getPrice());
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

    // Method to get a payment by ID
    public Payment getPaymentById(int paymentID) {
        String query = "SELECT * FROM payments WHERE paymentID = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Payment payment = null;

        try {
            connection = DBConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, paymentID);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                payment = new Payment(
                        resultSet.getInt("paymentID"),
                        resultSet.getInt("orderID"),
                        resultSet.getString("dateTime"),
                        resultSet.getString("paymentMethod"),
                        resultSet.getDouble("price")
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

        return payment;
    }

    // Method to update a payment
    public void updatePayment(Payment payment) {
        String query = "UPDATE payments SET orderID = ?, dateTime = ?, paymentMethod = ?, price = ? WHERE paymentID = ?";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, payment.getOrderID());
            statement.setString(2, payment.getDateTime());
            statement.setString(3, payment.getPaymentMethod());
            statement.setDouble(4, payment.getPrice());
            statement.setInt(5, payment.getPaymentID());
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

    // Method to delete a payment
    public void deletePayment(int paymentID) {
        String query = "DELETE FROM payments WHERE paymentID = ?";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, paymentID);
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

    // Method to get all payments
    public List<Payment> getAllPayments() {
        List<Payment> payments = new ArrayList<>();
        String query = "SELECT * FROM payments";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnectionFactory.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Payment payment = new Payment(
                        resultSet.getInt("paymentID"),
                        resultSet.getInt("orderID"),
                        resultSet.getString("dateTime"),
                        resultSet.getString("paymentMethod"),
                        resultSet.getDouble("price")
                );
                payments.add(payment);
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

        return payments;
    }
}
