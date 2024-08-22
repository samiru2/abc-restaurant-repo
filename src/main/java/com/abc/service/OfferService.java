package com.abc.service;

import java.sql.SQLException;
import java.util.List;

import com.abc.dao.OfferDAO;
import com.abc.model.Offer;

public class OfferService {

    private static OfferService instance;
    private OfferDAO offerDAO;

    private OfferService() {
        this.offerDAO = new OfferDAO();
    }

    public static OfferService getInstance() {
        if (instance == null) {
            synchronized (OfferService.class) {
                if (instance == null) {
                    instance = new OfferService();
                }
            }
        }
        return instance;
    }

    public void addOffer(Offer offer) {
        offerDAO.addOffer(offer);
    }

    public List<Offer> getAllOffers() throws SQLException {
        return offerDAO.getAllOffers();
    }
    
    public void updateOffer(Offer offer) {
        offerDAO.updateOffer(offer);
    }

    public void deleteOffer(int offerId) {
        offerDAO.deleteOffer(offerId);
    }

    public Offer getOfferById(int offerId) {
        return offerDAO.getOfferById(offerId);
    }
}
