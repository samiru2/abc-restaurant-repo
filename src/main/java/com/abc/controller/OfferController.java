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

import com.abc.model.Offer;
import com.abc.service.OfferService;

@WebServlet("/offer")
@MultipartConfig
public class OfferController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private OfferService offerService;

    @Override
    public void init() throws ServletException {
        offerService = OfferService.getInstance();
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
            listOffers(request, response);
        } else if (action.equals("add")) {
            showAddForm(request, response);
        } else if (action.equals("edit")) {
            showEditForm(request, response);
        } else if (action.equals("delete")) {
            deleteOffer(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equals("add")) {
            addOffer(request, response);
        } else if (action.equals("update")) {
            updateOffer(request, response);
        }
    }

    private void listOffers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Offer> offerList = new ArrayList<>();
        try {
            offerList = offerService.getAllOffers();
            request.setAttribute("offers", offerList);
        } catch (SQLException e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("WEB-INF/view/error.jsp").forward(request, response);
            return;
        }

        request.getRequestDispatcher("WEB-INF/view/listOffers.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/view/addOffer.jsp").forward(request, response);
    }

    private void addOffer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        int price = Integer.parseInt(request.getParameter("price"));
        
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

        Offer offer = new Offer();
        offer.setTitle(title);
        offer.setDescription(description);
        offer.setPrice(price);
        offer.setImage(imageUrl);

        offerService.addOffer(offer);

        response.sendRedirect("offer?action=list");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int offerId = Integer.parseInt(request.getParameter("id"));
        Offer existingOffer = offerService.getOfferById(offerId);
        request.setAttribute("offer", existingOffer);
        request.getRequestDispatcher("WEB-INF/view/editOffer.jsp").forward(request, response);
    }

    private void updateOffer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int offerId = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        int price = Integer.parseInt(request.getParameter("price"));
        
        Part imagePart = request.getPart("image");
        String imageUrl = null;

        Offer existingOffer = offerService.getOfferById(offerId);

        if (existingOffer == null) {
            request.setAttribute("errorMessage", "Offer not found.");
            request.getRequestDispatcher("WEB-INF/view/error.jsp").forward(request, response);
            return;
        }

        String oldImageUrl = existingOffer.getImage();

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

        Offer offer = new Offer(offerId, title, description, price, imageUrl);
        offerService.updateOffer(offer);

        response.sendRedirect("offer?action=list");
    }

    private void deleteOffer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int offerId = Integer.parseInt(request.getParameter("id"));

        Offer offer = offerService.getOfferById(offerId);
        if (offer == null) {
            request.setAttribute("errorMessage", "Offer not found.");
            request.getRequestDispatcher("WEB-INF/view/error.jsp").forward(request, response);
            return;
        }

        String imageUrl = offer.getImage();
        
        offerService.deleteOffer(offerId);

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

        response.sendRedirect("offer?action=list");
    }
}

