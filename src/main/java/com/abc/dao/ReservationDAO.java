package com.abc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.abc.model.Reservation;

public class ReservationDAO {

    public void addReservation(Reservation reservation) {
        String query = "INSERT INTO reservations (userID, date, time, numberOfPeople, status, message) VALUES (?, ?, ?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, reservation.getUserID());
            statement.setString(2, reservation.getDate());
            statement.setString(3, reservation.getTime());
            statement.setInt(4, reservation.getNumberOfPeople());
            statement.setString(5, reservation.getStatus());
            statement.setString(6, reservation.getMessage());
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

    public Reservation getReservationById(int reservationID) {
        String query = "SELECT r.*, u.username, u.phone, u.email FROM reservations r JOIN users u ON r.userID = u.userID WHERE r.reservationID = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Reservation reservation = null;

        try {
            connection = DBConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, reservationID);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                reservation = new Reservation(
                        resultSet.getInt("reservationID"),
                        resultSet.getInt("userID"),
                        resultSet.getString("date"),
                        resultSet.getString("time"),
                        resultSet.getInt("numberOfPeople"),
                        resultSet.getString("status"),
                        resultSet.getString("message"),
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

        return reservation;
    }

    public List<Reservation> getAllReservations() {
        List<Reservation> reservations = new ArrayList<>();
        String query = "SELECT r.*, u.username, u.phone, u.email FROM reservations r JOIN users u ON r.userID = u.userID ORDER BY r.reservationID DESC";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnectionFactory.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Reservation reservation = new Reservation(
                        resultSet.getInt("reservationID"),
                        resultSet.getInt("userID"),
                        resultSet.getString("date"),
                        resultSet.getString("time"),
                        resultSet.getInt("numberOfPeople"),
                        resultSet.getString("status"),
                        resultSet.getString("message"),
                        resultSet.getString("username"),
                        resultSet.getString("phone"),
                        resultSet.getString("email")
                );
                reservations.add(reservation);
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

        return reservations;
    }

    public void updateReservation(Reservation reservation) {
        String query = "UPDATE reservations SET userID = ?, date = ?, time = ?, numberOfPeople = ?, status = ?, message = ? WHERE reservationID = ?";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, reservation.getUserID());
            statement.setString(2, reservation.getDate());
            statement.setString(3, reservation.getTime());
            statement.setInt(4, reservation.getNumberOfPeople());
            statement.setString(5, reservation.getStatus());
            statement.setString(6, reservation.getMessage());
            statement.setInt(7, reservation.getReservationID());
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

    public void deleteReservation(int reservationID) {
        String query = "DELETE FROM reservations WHERE reservationID = ?";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, reservationID);
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
