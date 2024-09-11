package com.abc.service;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.abc.model.Menu;

import java.sql.SQLException;
import java.util.List;

public class MenuServiceTest {

    private MenuService menuService;

    @Before
    public void setUp() {
        menuService = MenuService.getInstance();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testAddMenu() {
        Menu menu = new Menu("Pizza", "Delicious cheese pizza", 10, "Main Course", "pizza.jpg");
        menuService.addMenu(menu);

        Menu fetchedMenu = menuService.getMenuById(menu.getMenuId());
        assertNotNull(fetchedMenu);
        assertEquals("Pizza", fetchedMenu.getName());
        assertEquals("Delicious cheese pizza", fetchedMenu.getDescription());
        assertEquals(10, fetchedMenu.getPrice(), 0);
        assertEquals("Main Course", fetchedMenu.getCategory());
        assertEquals("pizza.jpg", fetchedMenu.getImage());
    }

    @Test
    public void testGetAllMenus() throws SQLException {
        List<Menu> menus = menuService.getAllMenus();
        assertNotNull(menus);
        assertTrue(menus.size() > 0);
    }

    @Test
    public void testUpdateMenu() {
        Menu menu = new Menu("Burger", "Juicy beef burger", 9, "Main Course", "burger.jpg");
        menuService.addMenu(menu);

        Menu fetchedMenu = menuService.getMenuById(menu.getMenuId());
        fetchedMenu.setPrice(11);
        menuService.updateMenu(fetchedMenu);

        Menu updatedMenu = menuService.getMenuById(menu.getMenuId());
        assertNotNull(updatedMenu);
        assertEquals(11, updatedMenu.getPrice(), 0);
    }

    @Test
    public void testDeleteMenu() {
        Menu menu = new Menu("Salad", "Fresh garden salad", 7, "Appetizer", "salad.jpg");
        menuService.addMenu(menu);
        int menuId = menu.getMenuId();

        menuService.deleteMenu(menuId);

        Menu deletedMenu = menuService.getMenuById(menuId);
        assertNull(deletedMenu);
    }

    @Test
    public void testGetMenuById() {
        Menu menu = new Menu("Pasta", "Creamy Alfredo pasta", 13, "Main Course", "pasta.jpg");
        menuService.addMenu(menu);

        Menu fetchedMenu = menuService.getMenuById(menu.getMenuId());
        assertNotNull(fetchedMenu);
        assertEquals("Pasta", fetchedMenu.getName());
    }
}
