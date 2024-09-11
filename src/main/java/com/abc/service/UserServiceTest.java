package com.abc.service;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.abc.model.User;

public class UserServiceTest {

    private UserService userService;

    @Before
    public void setUp() {
        userService = UserService.getInstance();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testAddUser() {
        User user = new User("uniqueUser", "password123", null, null, "customer");
        int userId = -1;
        try {
            userId = userService.addUser(user);
            System.out.println("User ID returned: " + userId);

            assertTrue("User ID should be greater than 0", userId > 0);

            User retrievedUser = userService.getUserById(userId);
            assertNotNull("User should be retrieved", retrievedUser);
            assertEquals("Username should match", user.getUsername(), retrievedUser.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception occurred during test: " + e.getMessage());
        }
    }

    @Test
    public void testGetUserById() {
        User user = new User("uniqueUser", "password123", null, null, "customer");
        int userId = -1;
        try {
            userId = userService.addUser(user);
            User retrievedUser = userService.getUserById(userId);
            assertNotNull("User should be retrieved", retrievedUser);
            assertEquals("User ID should match", userId, retrievedUser.getUserId());
        } catch (Exception e) {
            fail("Exception occurred during test: " + e.getMessage());
        }
    }

    @Test
    public void testUpdateUser() {
        User user = new User("uniqueUser", "password123", null, null, "customer");
        int userId = -1;
        try {
            userId = userService.addUser(user);
            user.setUserId(userId);
            user.setUsername("updateduser");
            userService.updateUser(user);

            User updatedUser = userService.getUserById(userId);
            
            System.out.println("Updated User ID: " + userId);
            System.out.println("Retrieved User Username: " + (updatedUser != null ? updatedUser.getUsername() : "null"));

            assertNotNull("Updated user should be retrieved", updatedUser);
            assertEquals("Username should be updated", "updateduser", updatedUser.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception occurred during test: " + e.getMessage());
        }
    }

    @Test
    public void testDeleteUser() {
        User user = new User("uniqueUser", "password123", null, null, "customer");
        try {
            int userId = userService.addUser(user);
            userService.deleteUser(userId);
            User deletedUser = userService.getUserById(userId);
            assertNull("Deleted user should not be retrieved", deletedUser);
        } catch (Exception e) {
            fail("Exception occurred during test: " + e.getMessage());
        }
    }

    @Test
    public void testIsPhoneNumberExists() {
        User user = new User("uniqueUser", "password123", null, null, "customer");
        try {
            userService.addUser(user);
            boolean exists = userService.isPhoneNumberExists(null); 
            assertFalse("Phone number should not exist", exists);
        } catch (Exception e) {
            fail("Exception occurred during test: " + e.getMessage());
        }
    }
}
