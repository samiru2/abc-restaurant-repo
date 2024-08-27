package com.abc.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.abc.model.Gallery;
import com.abc.service.GalleryService;

@WebServlet("/gallery")
@MultipartConfig
public class GalleryController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private GalleryService galleryService;

    @Override
    public void init() throws ServletException {
        galleryService = GalleryService.getInstance();
    }

    private String getUploadPath() {
        Properties properties = new Properties();
        try (InputStream input = getServletContext().getResourceAsStream("/WEB-INF/classes/config.properties")) {
            if (input == null) {
                throw new RuntimeException("Sorry, unable to find config.properties");
            }
            properties.load(input);
            return properties.getProperty("image.upload.path");
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error reading config.properties", ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null || action.equals("list")) {
            listGalleries(request, response);
        } else if (action.equals("add")) {
            showAddForm(request, response);
        } else if (action.equals("delete")) {
            deleteGallery(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equals("add")) {
            addGallery(request, response);
        }
    }

    private void listGalleries(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Gallery> galleryList = null;
        try {
            galleryList = galleryService.getAllGalleries();
            request.setAttribute("galleries", galleryList);
        } catch (SQLException e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("WEB-INF/view/error.jsp").forward(request, response);
            return;
        }

        request.getRequestDispatcher("WEB-INF/view/listGalleries.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/view/addGallery.jsp").forward(request, response);
    }

    private void addGallery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part imagePart = request.getPart("image");
        String imageUrl = null;

        if (imagePart != null && imagePart.getSize() > 0) {
            String imageFileName = Paths.get(imagePart.getSubmittedFileName()).getFileName().toString();

            String uploadPath = getUploadPath();
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            try {
                File file = new File(uploadPath + File.separator + imageFileName);
                imagePart.write(file.getAbsolutePath());
                imageUrl = "images/" + imageFileName;
            } catch (IOException e) {
                e.printStackTrace();
                throw new ServletException("File upload failed.");
            }
        }

        Gallery gallery = new Gallery(imageUrl);
        galleryService.addGallery(gallery);

        response.sendRedirect("gallery?action=list");
    }

    private void deleteGallery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int galleryId = Integer.parseInt(request.getParameter("id"));

        Gallery gallery = galleryService.getGalleryById(galleryId);
        if (gallery == null) {
            request.setAttribute("errorMessage", "Gallery item not found.");
            request.getRequestDispatcher("WEB-INF/view/error.jsp").forward(request, response);
            return;
        }

        String imageUrl = gallery.getImage();
        
        galleryService.deleteGallery(galleryId);

        if (imageUrl != null && !imageUrl.isEmpty()) {
            String uploadPath = getUploadPath();
            File file = new File(uploadPath + File.separator + Paths.get(imageUrl).getFileName());
            if (file.exists()) {
                boolean deleted = file.delete();
                if (!deleted) {
                    System.err.println("Failed to delete the image file: " + file.getAbsolutePath());
                }
            }
        }

        response.sendRedirect("gallery?action=list");
    }
}
