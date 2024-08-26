package com.abc.service;

import java.util.List;
import com.abc.dao.UserDAO;
import com.abc.model.User;

public class UserService {

    private static UserService instance;
    private UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    public static UserService getInstance() {
        if (instance == null) {
            synchronized (UserService.class) {
                if (instance == null) {
                    instance = new UserService();
                }
            }
        }
        return instance;
    }

    public void addUser(User user) {
        userDAO.addUser(user);
    }

    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    public void deleteUser(int userId) {
        userDAO.deleteUser(userId);
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public User getUserById(int userId) {
        return userDAO.getUserById(userId);
    }

    /**
     * Validates the login credentials.
     * @param username The username entered by the user.
     * @param password The password entered by the user.
     * @return A User object if the credentials are valid; otherwise, null.
     */
    public User validateLogin(String username, String password) {
        // Call the DAO to get the user by username and password
        return userDAO.getUserByUsernameAndPassword(username, password);
    }
}
