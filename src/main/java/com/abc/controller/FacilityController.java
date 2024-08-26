package com.abc.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.abc.model.Facility;
import com.abc.service.FacilityService;

@WebServlet("/facility")
@MultipartConfig
public class FacilityController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private FacilityService facilityService;

    @Override
    public void init() throws ServletException {
        facilityService = FacilityService.getInstance();
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
            listFacilities(request, response);
        } else if (action.equals("add")) {
            showAddForm(request, response);
        } else if (action.equals("edit")) {
            showEditForm(request, response);
        } else if (action.equals("delete")) {
            deleteFacility(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equals("add")) {
            addFacility(request, response);
        } else if (action.equals("update")) {
            updateFacility(request, response);
        }
    }

    private void listFacilities(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Facility> facilityList = new ArrayList<>();
        try {
            facilityList = facilityService.getAllFacilities();
            request.setAttribute("facilities", facilityList);
        } catch (SQLException e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("WEB-INF/view/error.jsp").forward(request, response);
            return;
        }

        request.getRequestDispatcher("WEB-INF/view/listFacilities.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/view/addFacility.jsp").forward(request, response);
    }

    private void addFacility(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");

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

        Facility facility = new Facility();
        facility.setName(name);
        facility.setDescription(description);
        facility.setImage(imageUrl);

        facilityService.addFacility(facility);

        response.sendRedirect("facility?action=list");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int facilityId = Integer.parseInt(request.getParameter("id"));
        Facility existingFacility = facilityService.getFacilityById(facilityId);
        request.setAttribute("facility", existingFacility);
        request.getRequestDispatcher("WEB-INF/view/editFacility.jsp").forward(request, response);
    }

    private void updateFacility(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int facilityId = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        
        Part imagePart = request.getPart("image");
        String imageUrl = null;

        Facility existingFacility = facilityService.getFacilityById(facilityId);

        if (existingFacility == null) {
            request.setAttribute("errorMessage", "Facility item not found.");
            request.getRequestDispatcher("WEB-INF/view/error.jsp").forward(request, response);
            return;
        }

        
        String oldImageUrl = existingFacility.getImage();

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

            if (oldImageUrl != null && !oldImageUrl.isEmpty()) {
                File oldImageFile = new File(uploadPath + File.separator + Paths.get(oldImageUrl).getFileName());
                if (oldImageFile.exists()) {
                    boolean deleted = oldImageFile.delete();
                    if (!deleted) {
                        System.err.println("Failed to delete the old image file: " + oldImageFile.getAbsolutePath());
                    }
                }
            }
        } else {
            
            imageUrl = oldImageUrl;
        }

        Facility facility = new Facility(facilityId, name, description, imageUrl);
        facilityService.updateFacility(facility);

        response.sendRedirect("facility?action=list");
    }

    private void deleteFacility(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int facilityId = Integer.parseInt(request.getParameter("id"));

        Facility facility = facilityService.getFacilityById(facilityId);
        if (facility == null) {
            request.setAttribute("errorMessage", "Facility item not found.");
            request.getRequestDispatcher("WEB-INF/view/error.jsp").forward(request, response);
            return;
        }

        String imageUrl = facility.getImage();
        
        facilityService.deleteFacility(facilityId);

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

        response.sendRedirect("facility?action=list");
    }
}
