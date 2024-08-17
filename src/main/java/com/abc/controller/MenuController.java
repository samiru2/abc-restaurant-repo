package com.abc.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.abc.model.Menu;
import com.abc.service.MenuService;

@WebServlet("/menu")
public class MenuController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private MenuService menuService;

    public void init() throws ServletException {
        menuService = MenuService.getInstance();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null || action.equals("list")) {
            listMenus(request, response);
        } else if (action.equals("add")) {
            showAddForm(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equals("add")) {
            addMenu(request, response);
        }
    }

    private void listMenus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Menu> menuList = new ArrayList<>();
        try {
            menuList = menuService.getAllMenus();
            request.setAttribute("menus", menuList);
        } catch (SQLException e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("WEB-INF/view/error.jsp").forward(request, response);
            return;
        }

        request.getRequestDispatcher("WEB-INF/view/listMenus.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/view/addMenu.jsp").forward(request, response);
    }

    private void addMenu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        String category = request.getParameter("category");
        String image = request.getParameter("image");

        Menu menu = new Menu();
        menu.setName(name);
        menu.setDescription(description);
        menu.setPrice(price);
        menu.setCategory(category);
        menu.setImage(image);

        menuService.addMenu(menu);
        response.sendRedirect("menu?action=list");
    }
}
