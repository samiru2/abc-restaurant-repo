package com.abc.service;

import java.sql.SQLException;
import java.util.List;

import com.abc.dao.MenuDAO;
import com.abc.model.Menu;

public class MenuService {

    private static MenuService instance;
    private MenuDAO menuDAO;

    private MenuService() {
        this.menuDAO = new MenuDAO();
    }

    public static MenuService getInstance() {
        if (instance == null) {
            synchronized (MenuService.class) {
                if (instance == null) {
                    instance = new MenuService();
                }
            }
        }
        return instance;
    }

    public void addMenu(Menu menu) {
        menuDAO.addMenu(menu);
    }

    public List<Menu> getAllMenus() throws SQLException {
        return menuDAO.getAllMenus();
    }
}
