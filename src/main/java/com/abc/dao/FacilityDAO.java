package com.abc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.abc.model.Facility;

public class FacilityDAO {

    // Method to add a facility item
    public void addFacility(Facility facility) {
        String query = "INSERT INTO facilities (name, description, image) VALUES (?, ?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, facility.getName());
            statement.setString(2, facility.getDescription());
            statement.setString(3, facility.getImage());
            statement.executeUpdate();
        } 
        catch (SQLException e) {
            e.printStackTrace();
        } 
        finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Method to get all facility items
    public List<Facility> getAllFacilities() throws SQLException {
        List<Facility> facilities = new ArrayList<>();
        String query = "SELECT * FROM facilities";

        Connection connection = DBConnectionFactory.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            int id = resultSet.getInt("facilityID");
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            String image = resultSet.getString("image");
            facilities.add(new Facility(id, name, description, image));
        }

        resultSet.close();
        statement.close();

        return facilities;
    }

    // Method to update a facility item
    public void updateFacility(Facility facility) {
        String query = "UPDATE facilities SET name = ?, description = ?, image = ? WHERE facilityID = ?";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, facility.getName());
            statement.setString(2, facility.getDescription());
            statement.setString(3, facility.getImage());
            statement.setInt(4, facility.getFacilityId());
            statement.executeUpdate();
        } 
        catch (SQLException e) {
            e.printStackTrace();
        } 
        finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Method to get a single facility item by ID (for editing)
    public Facility getFacilityById(int facilityId) {
        String query = "SELECT * FROM facilities WHERE facilityID = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Facility facility = null;

        try {
            connection = DBConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, facilityId);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                String image = resultSet.getString("image");
                facility = new Facility(facilityId, name, description, image);
            }
        } 
        catch (SQLException e) {
            e.printStackTrace();
        } 
        finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return facility;
    }

    // Method to delete a facility item
    public void deleteFacility(int facilityId) {
        String query = "DELETE FROM facilities WHERE facilityID = ?";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, facilityId);
            statement.executeUpdate();
        } 
        catch (SQLException e) {
            e.printStackTrace();
        } 
        finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
