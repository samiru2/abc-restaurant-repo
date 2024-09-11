package com.abc.service;

import com.abc.dao.OfferDAO;
import com.abc.model.Offer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class OfferServiceTest {
    private OfferService offerService;
    private OfferDAO offerDAO;

    @Before
    public void setUp() {
        offerService = OfferService.getInstance();
        offerDAO = new OfferDAO();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testAddOffer() {
        Offer offer = new Offer("Special Discount", "20% off on all items", 100, "discount.jpg");
        offerService.addOffer(offer);

        Offer addedOffer = offerDAO.getOfferById(offer.getOfferId());
        assertNotNull("Offer should be added", addedOffer);
        assertEquals("Title should match", "Special Discount", addedOffer.getTitle());
        assertEquals("Description should match", "20% off on all items", addedOffer.getDescription());
        assertEquals("Price should match", 100, addedOffer.getPrice());
        assertEquals("Image should match", "discount.jpg", addedOffer.getImage());
    }

    @Test
    public void testGetAllOffers() throws SQLException {
        Offer offer1 = new Offer("Special Discount", "20% off on all items", 100, "discount.jpg");
        Offer offer2 = new Offer("Buy 1 Get 1", "Free item with purchase", 150, "buy1get1.jpg");
        offerService.addOffer(offer1);
        offerService.addOffer(offer2);

        List<Offer> offers = offerService.getAllOffers();
        assertNotNull("Offer list should not be null", offers);
        assertEquals("There should be 2 offers", 2, offers.size());
    }

    @Test
    public void testUpdateOffer() {
        Offer offer = new Offer("Special Discount", "20% off on all items", 100, "discount.jpg");
        offerService.addOffer(offer);

        Offer addedOffer = offerDAO.getOfferById(offer.getOfferId());
        assertNotNull("Offer should be added", addedOffer);

        addedOffer.setTitle("Updated Discount");
        addedOffer.setDescription("30% off on all items");
        addedOffer.setPrice(120);
        addedOffer.setImage("updated_discount.jpg");
        offerService.updateOffer(addedOffer);

        Offer updatedOffer = offerDAO.getOfferById(addedOffer.getOfferId());
        assertNotNull("Offer should be updated", updatedOffer);
        assertEquals("Title should be updated", "Updated Discount", updatedOffer.getTitle());
        assertEquals("Description should be updated", "30% off on all items", updatedOffer.getDescription());
        assertEquals("Price should be updated", 120, updatedOffer.getPrice());
        assertEquals("Image should be updated", "updated_discount.jpg", updatedOffer.getImage());
    }

    @Test
    public void testDeleteOffer() {
        Offer offer = new Offer("Special Discount", "20% off on all items", 100, "discount.jpg");
        offerService.addOffer(offer);

        Offer addedOffer = offerDAO.getOfferById(offer.getOfferId());
        assertNotNull("Offer should be added", addedOffer);

        offerService.deleteOffer(offer.getOfferId());
        Offer deletedOffer = offerDAO.getOfferById(offer.getOfferId());
        assertNull("Offer should be deleted", deletedOffer);
    }

    @Test
    public void testGetOfferById() {
        Offer offer = new Offer("Special Discount", "20% off on all items", 100, "discount.jpg");
        offerService.addOffer(offer);

        Offer fetchedOffer = offerService.getOfferById(offer.getOfferId());
        assertNotNull("Offer should be fetched", fetchedOffer);
        assertEquals("Title should match", "Special Discount", fetchedOffer.getTitle());
        assertEquals("Description should match", "20% off on all items", fetchedOffer.getDescription());
        assertEquals("Price should match", 100, fetchedOffer.getPrice());
        assertEquals("Image should match", "discount.jpg", fetchedOffer.getImage());
    }
}
