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

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int facilityId = Integer.parseInt(request.getParameter("facilityId"));
        Facility existingFacility = facilityService.getFacilityById(facilityId);
        request.setAttribute("facility", existingFacility);
        request.getRequestDispatcher("WEB-INF/view/editFacility.jsp").forward(request, response);
    }

    private void addFacility(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");

        // Handle file upload
        Part filePart = request.getPart("image");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String uploadPath = getUploadPath();
        if (!fileName.isEmpty()) {
            filePart.write(uploadPath + File.separator + fileName);
        }

        Facility newFacility = new Facility(name, description, fileName);
        facilityService.addFacility(newFacility);
        response.sendRedirect("facility?action=list");
    }

    private void updateFacility(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int facilityId = Integer.parseInt(request.getParameter("facilityId"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");

        // Handle file upload
        Part filePart = request.getPart("image");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String uploadPath = getUploadPath();
        if (!fileName.isEmpty()) {
            filePart.write(uploadPath + File.separator + fileName);
        } else {
            fileName = request.getParameter("existingImage"); // Keep the existing image if no new image is uploaded
        }

        Facility updatedFacility = new Facility(facilityId, name, description, fileName);
        facilityService.updateFacility(updatedFacility);
        response.sendRedirect("facility?action=list");
    }

    private void deleteFacility(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int facilityId = Integer.parseInt(request.getParameter("facilityId"));
        facilityService.deleteFacility(facilityId);
        response.sendRedirect("facility?action=list");
    }
}