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

    // Method to add a reservation
    public void addReservation(Reservation reservation) {
        String query = "INSERT INTO reservations (userID, date, time, numberOfPeople, status) VALUES (?, ?, ?, ?, ?)";
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

    // Method to get a reservation by ID
    public Reservation getReservationById(int reservationID) {
        String query = "SELECT * FROM reservations WHERE reservationID = ?";
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

        return reservation;
    }

    // Method to update a reservation
    public void updateReservation(Reservation reservation) {
        String query = "UPDATE reservations SET userID = ?, date = ?, time = ?, numberOfPeople = ?, status = ? WHERE reservationID = ?";
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
            statement.setInt(6, reservation.getReservationID());
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

    // Method to delete a reservation
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

    // Method to get all reservations
    public List<Reservation> getAllReservations() {
        List<Reservation> reservations = new ArrayList<>();
        String query = "SELECT * FROM reservations";
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
                        resultSet.getString("status")
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
}
