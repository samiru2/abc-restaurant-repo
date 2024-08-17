package com.abc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.abc.model.Menu;

public class MenuDAO {

    // Method to add a new menu item to the database
    public void addMenu(Menu menu) {
        String query = "INSERT INTO Menu (name, description, price, category, image) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DBConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, menu.getName());
            statement.setString(2, menu.getDescription());
            statement.setDouble(3, menu.getPrice());
            statement.setString(4, menu.getCategory());
            statement.setString(5, menu.getImage());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to retrieve all menus from the database
    public List<Menu> getAllMenus() throws SQLException {
        List<Menu> menus = new ArrayList<>();
        String query = "SELECT * FROM Menu";

        try (Connection connection = DBConnectionFactory.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int menuID = resultSet.getInt("menuID");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                double price = resultSet.getDouble("price");
                String category = resultSet.getString("category");
                String image = resultSet.getString("image");

                menus.add(new Menu(menuID, name, description, price, category, image));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return menus;
    }
}
