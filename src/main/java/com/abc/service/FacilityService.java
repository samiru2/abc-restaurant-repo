package com.abc.service;

import java.sql.SQLException;
import java.util.List;

import com.abc.dao.FacilityDAO;
import com.abc.model.Facility;

public class FacilityService {

    private static FacilityService instance;
    private FacilityDAO facilityDAO;

    private FacilityService() {
        this.facilityDAO = new FacilityDAO();
    }

    public static FacilityService getInstance() {
        if (instance == null) {
            synchronized (FacilityService.class) {
                if (instance == null) {
                    instance = new FacilityService();
                }
            }
        }
        return instance;
    }

    public void addFacility(Facility facility) {
        facilityDAO.addFacility(facility);
    }

    public List<Facility> getAllFacilities() throws SQLException {
        return facilityDAO.getAllFacilities();
    }
    
    public void updateFacility(Facility facility) {
        facilityDAO.updateFacility(facility);
    }

    public void deleteFacility(int facilityId) {
        facilityDAO.deleteFacility(facilityId);
    }

    public Facility getFacilityById(int facilityId) {
        return facilityDAO.getFacilityById(facilityId);
    }
}
