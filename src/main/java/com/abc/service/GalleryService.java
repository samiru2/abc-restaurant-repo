package com.abc.service;

import java.sql.SQLException;
import java.util.List;

import com.abc.dao.GalleryDAO;
import com.abc.model.Gallery;

public class GalleryService {

    private static GalleryService instance;
    private GalleryDAO galleryDAO;

    private GalleryService() {
        this.galleryDAO = new GalleryDAO();
    }

    public static GalleryService getInstance() {
        if (instance == null) {
            synchronized (GalleryService.class) {
                if (instance == null) {
                    instance = new GalleryService();
                }
            }
        }
        return instance;
    }

    public void addGallery(Gallery gallery) {
        galleryDAO.addGallery(gallery);
    }

    public List<Gallery> getAllGalleries() throws SQLException {
        return galleryDAO.getAllGalleries();
    }

    public void deleteGallery(int galleryId) {
        galleryDAO.deleteGallery(galleryId);
    }

    public Gallery getGalleryById(int galleryId) {
        return galleryDAO.getGalleryById(galleryId);
    }
}
