package com.abc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.abc.model.Gallery;

public class GalleryDAO {

    public void addGallery(Gallery gallery) {
        String query = "INSERT INTO gallery (image) VALUES (?)";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, gallery.getImage());
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

    public List<Gallery> getAllGalleries() throws SQLException {
        List<Gallery> galleries = new ArrayList<>();
        String query = "SELECT * FROM gallery ORDER BY galleryID DESC";

        Connection connection = DBConnectionFactory.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            int id = resultSet.getInt("galleryID");
            String image = resultSet.getString("image");
            galleries.add(new Gallery(id, image));
        }

        resultSet.close();
        statement.close();

        return galleries;
    }
    
    public Gallery getGalleryById(int galleryId) {
        String query = "SELECT * FROM gallery WHERE galleryID = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Gallery gallery = null;

        try {
            connection = DBConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, galleryId);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String image = resultSet.getString("image");
                gallery = new Gallery(galleryId, image);
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

        return gallery;
    }

    public void deleteGallery(int galleryId) {
        String query = "DELETE FROM gallery WHERE galleryID = ?";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, galleryId);
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
