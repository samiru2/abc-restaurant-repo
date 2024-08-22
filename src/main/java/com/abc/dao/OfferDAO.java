package com.abc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.abc.model.Offer;

public class OfferDAO {

    // Method to add an offer
    public void addOffer(Offer offer) {
        String query = "INSERT INTO offers (title, description, price, image) VALUES (?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, offer.getTitle());
            statement.setString(2, offer.getDescription());
            statement.setInt(3, offer.getPrice());
            statement.setString(4, offer.getImage());
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

    // Method to get all offers
    public List<Offer> getAllOffers() throws SQLException {
        List<Offer> offers = new ArrayList<>();
        String query = "SELECT * FROM offers";

        Connection connection = DBConnectionFactory.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            int id = resultSet.getInt("offerID");
            String title = resultSet.getString("title");
            String description = resultSet.getString("description");
            int price = resultSet.getInt("price");
            String image = resultSet.getString("image");
            offers.add(new Offer(id, title, description, price, image));
        }

        resultSet.close();
        statement.close();

        return offers;
    }

    // Method to update an offer
    public void updateOffer(Offer offer) {
        String query = "UPDATE offers SET title = ?, description = ?, price = ?, image = ? WHERE offerID = ?";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, offer.getTitle());
            statement.setString(2, offer.getDescription());
            statement.setInt(3, offer.getPrice());
            statement.setString(4, offer.getImage());
            statement.setInt(5, offer.getOfferId()); // Ensure this matches your model's field name
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

    // Method to get an offer by ID
    public Offer getOfferById(int offerId) {
        String query = "SELECT * FROM offers WHERE offerID = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Offer offer = null;

        try {
            connection = DBConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, offerId);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                int price = resultSet.getInt("price");
                String image = resultSet.getString("image");
                offer = new Offer(offerId, title, description, price, image);
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

        return offer;
    }

    // Method to delete an offer
    public void deleteOffer(int offerId) {
        String query = "DELETE FROM offers WHERE offerID = ?";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, offerId);
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
