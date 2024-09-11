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

    public User validateLogin(String username, String password) {
        return userDAO.getUserByUsernameAndPassword(username, password);
    }
    
    public boolean isPhoneNumberExists(String phoneNumber) throws Exception {
        return userDAO.getUserByPhoneNumber(phoneNumber) != null;
    }

    public boolean isEmailExists(String email) throws Exception {
        return userDAO.getUserByEmail(email) != null;
    }
    
    public String getUserEmailById(int userId) {
        User user = userDAO.getUserById(userId);
        return user != null ? user.getEmail() : null;
    }
}
