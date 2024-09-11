package com.abc.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import com.abc.dao.GalleryDAO;
import com.abc.model.Gallery;

public class GalleryServiceTest {

    private GalleryService galleryService;
    private GalleryDAO galleryDAO;

    @Before
    public void setUp() throws Exception {
        galleryService = GalleryService.getInstance();
        galleryDAO = new GalleryDAO();
        
        clearGalleryTable();
    }

    @After
    public void tearDown() throws Exception {
        clearGalleryTable();
    }

    private void clearGalleryTable() throws SQLException {
        List<Gallery> galleries = galleryDAO.getAllGalleries();
        for (Gallery gallery : galleries) {
            galleryDAO.deleteGallery(gallery.getGalleryId());
        }
    }

    @Test
    public void testAddGallery() {
        Gallery gallery = new Gallery("test_image.jpg");
        galleryService.addGallery(gallery);

        List<Gallery> galleries;
        try {
            galleries = galleryService.getAllGalleries();
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
        assertNotNull(galleries);
        assertEquals(1, galleries.size());
        Gallery addedGallery = galleries.get(0);
        assertEquals("test_image.jpg", addedGallery.getImage());
    }

    @Test
    public void testGetGalleryById() {
        Gallery gallery = new Gallery("test_image.jpg");
        galleryService.addGallery(gallery);
        
        List<Gallery> galleries;
        try {
            galleries = galleryService.getAllGalleries();
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
        assertNotNull(galleries);
        assertEquals(1, galleries.size());
        Gallery addedGallery = galleries.get(0);

        Gallery retrievedGallery = galleryService.getGalleryById(addedGallery.getGalleryId());
        assertNotNull(retrievedGallery);
        assertEquals(addedGallery.getGalleryId(), retrievedGallery.getGalleryId());
        assertEquals(addedGallery.getImage(), retrievedGallery.getImage());
    }

    @Test
    public void testDeleteGallery() {
        Gallery gallery = new Gallery("test_image.jpg");
        galleryService.addGallery(gallery);

        List<Gallery> galleries;
        try {
            galleries = galleryService.getAllGalleries();
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
        assertNotNull(galleries);
        assertEquals(1, galleries.size());
        Gallery addedGallery = galleries.get(0);

        galleryService.deleteGallery(addedGallery.getGalleryId());

        Gallery deletedGallery = galleryService.getGalleryById(addedGallery.getGalleryId());
        assertNull(deletedGallery);
    }

    @Test
    public void testGetAllGalleries() {
        Gallery gallery1 = new Gallery("test_image1.jpg");
        Gallery gallery2 = new Gallery("test_image2.jpg");
        galleryService.addGallery(gallery1);
        galleryService.addGallery(gallery2);

        List<Gallery> galleries;
        try {
            galleries = galleryService.getAllGalleries();
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
        assertNotNull(galleries);
        assertEquals(2, galleries.size());
    }
}
