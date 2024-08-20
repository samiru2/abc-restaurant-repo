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

    // Method to add a menu item
	public void addMenu(Menu menu) {
	    String query = "INSERT INTO menu (name, description, price, category, image) VALUES (?, ?, ?, ?, ?)";
	    Connection connection = null;
	    PreparedStatement statement = null;

	    try {
	        connection = DBConnectionFactory.getConnection();
	        statement = connection.prepareStatement(query);
	        statement.setString(1, menu.getName());
	        statement.setString(2, menu.getDescription());
	        statement.setDouble(3, menu.getPrice());
	        statement.setString(4, menu.getCategory());
	        statement.setString(5, menu.getImage());
	        statement.executeUpdate();
	    } 
	    catch (SQLException e) 
        {
            e.printStackTrace();
        }
        finally
        {
        	try {
				statement.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
        }
	}

    // Method to get all menu items
    public List<Menu> getAllMenus() throws SQLException {
        List<Menu> menus = new ArrayList<>();
        String query = "SELECT * FROM menu";

        Connection connection = DBConnectionFactory.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            int id = resultSet.getInt("menuID");
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            double price = resultSet.getDouble("price");
            String category = resultSet.getString("category");
            String image = resultSet.getString("image");
            menus.add(new Menu(id, name, description, price, category, image));
        }

        resultSet.close();
        statement.close();

        return menus;
    }
    
 // Method to update a menu item
    public void updateMenu(Menu menu) {
        String query = "UPDATE menu SET name = ?, description = ?, price = ?, category = ?, image = ? WHERE menuID = ?";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, menu.getName());
            statement.setString(2, menu.getDescription());
            statement.setDouble(3, menu.getPrice());
            statement.setString(4, menu.getCategory());
            statement.setString(5, menu.getImage());
            statement.setInt(6, menu.getMenuId()); // Ensure this matches your model's field name
            statement.executeUpdate();
        } catch (SQLException e) {
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

    // Method to get a single menu item by ID (for editing)
    public Menu getMenuById(int menuId) {
        String query = "SELECT * FROM menu WHERE menuID = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Menu menu = null;

        try {
            connection = DBConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, menuId);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                double price = resultSet.getDouble("price");
                String category = resultSet.getString("category");
                String image = resultSet.getString("image");
                menu = new Menu(menuId, name, description, price, category, image);
            }
        } catch (SQLException e) {
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

        return menu;
    }
    
    // Method to delete a menu item
    public void deleteMenu(int menuId) {
        String query = "DELETE FROM menu WHERE menuID = ?";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, menuId);
            statement.executeUpdate();
        } 
        catch (SQLException e) {
            e.printStackTrace();
        } 
        finally {
            try {
                if (statement != null) statement.close();
            } 
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
