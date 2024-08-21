package com.abc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.abc.model.User;

public class UserDAO {

    // Method to add a user
    public void addUser(User user) {
        String query = "INSERT INTO users (username, password, phone, email, role) VALUES (?, ?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getPhone());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getRole());
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

    // Method to get a user by Id
    public User getUserById(int userId) {
        String query = "SELECT * FROM users WHERE userId = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user = null;

        try {
            connection = DBConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = new User(
                        resultSet.getInt("userId"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("phone"),
                        resultSet.getString("email"),
                        resultSet.getString("role")
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

        return user;
    }

    // Method to update a user
    public void updateUser(User user) {
        String query = "UPDATE users SET username = ?, password = ?, phone = ?, email = ?, role = ? WHERE userId = ?";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getPhone());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getRole());
            statement.setInt(6, user.getUserId());
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

    // Method to delete a user
    public void deleteUser(int userId) {
        String query = "DELETE FROM users WHERE userId = ?";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
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

    // Method to get all users
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnectionFactory.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                User user = new User(
                        resultSet.getInt("userId"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("phone"),
                        resultSet.getString("email"),
                        resultSet.getString("role")
                );
                users.add(user);
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

        return users;
    }
}
