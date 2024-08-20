package com.abc.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.abc.model.Menu;
import com.abc.service.MenuService;

@WebServlet("/menu")
@MultipartConfig
public class MenuController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private MenuService menuService;

    @Override
    public void init() throws ServletException {
        menuService = MenuService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null || action.equals("list")) {
            listMenus(request, response);
        } else if (action.equals("add")) {
            showAddForm(request, response);
        } else if (action.equals("edit")) {
            showEditForm(request, response);
        } else if (action.equals("delete")) {
            deleteMenu(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equals("add")) {
            addMenu(request, response);
        } else if (action.equals("update")) {
            updateMenu(request, response);
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
        // Retrieve form parameters
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        String category = request.getParameter("category");
        
        // Handling file upload
        Part imagePart = request.getPart("image");
        String imageUrl = null;
        
        if (imagePart != null && imagePart.getSize() > 0) {
            // Get the original file name
            String imageFileName = Paths.get(imagePart.getSubmittedFileName()).getFileName().toString();

            // Define the path for saving the image files
            String uploadPath = getServletContext().getRealPath("/images");
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs(); // Create the directory if it doesn't exist
            }

            // Save the uploaded file
            try {
                File file = new File(uploadPath + File.separator + imageFileName);
                imagePart.write(file.getAbsolutePath());
                imageUrl = "images/" + imageFileName; // Save URL relative to the web root
            } catch (IOException e) {
                e.printStackTrace();
                throw new ServletException("File upload failed.");
            }
        }

        // Create Menu object
        Menu menu = new Menu();
        menu.setName(name);
        menu.setDescription(description);
        menu.setPrice(price);
        menu.setCategory(category);
        menu.setImage(imageUrl); // Save the file URL in the database

        // Add Menu to the service
        menuService.addMenu(menu);

        // Redirect to the list page
        response.sendRedirect("menu?action=list");
    }


    
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int menuId = Integer.parseInt(request.getParameter("id"));
        Menu existingMenu = menuService.getMenuById(menuId);
        request.setAttribute("menu", existingMenu);
        request.getRequestDispatcher("WEB-INF/view/editMenu.jsp").forward(request, response);
    }

    private void updateMenu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int menuId = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        String category = request.getParameter("category");
        String image = request.getParameter("image");

        Menu menu = new Menu(menuId, name, description, price, category, image);
        menuService.updateMenu(menu);

        response.sendRedirect("menu?action=list");
    }

    private void deleteMenu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int menuId = Integer.parseInt(request.getParameter("id"));
        menuService.deleteMenu(menuId);
        response.sendRedirect("menu?action=list");
    }

}
