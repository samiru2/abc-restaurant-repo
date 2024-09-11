package com.abc.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.abc.model.Facility;

public class FacilityServiceTest {

    private FacilityService facilityService;

    @Before
    public void setUp() {
        facilityService = FacilityService.getInstance();
    }

    @Test
    public void testAddFacility() {
        Facility facility = new Facility("Conference Room", "A large room for conferences", "conference_room.png");
        facilityService.addFacility(facility);

        Facility addedFacility = facilityService.getFacilityById(facility.getFacilityId());
        assertNotNull(addedFacility);
        assertEquals("Conference Room", addedFacility.getName());
        assertEquals("A large room for conferences", addedFacility.getDescription());
        assertEquals("conference_room.png", addedFacility.getImage());
    }

    @Test
    public void testGetAllFacilities() throws SQLException {
        List<Facility> facilities = facilityService.getAllFacilities();
        assertNotNull(facilities);
    }

    @Test
    public void testUpdateFacility() {
        Facility facility = new Facility("Meeting Room", "A small room for meetings", "meeting_room.png");
        facilityService.addFacility(facility);

        facility.setName("Updated Meeting Room");
        facility.setDescription("An updated description for the meeting room");
        facility.setImage("updated_meeting_room.png");
        facilityService.updateFacility(facility);

        Facility updatedFacility = facilityService.getFacilityById(facility.getFacilityId());
        assertNotNull(updatedFacility);
        assertEquals("Updated Meeting Room", updatedFacility.getName());
        assertEquals("An updated description for the meeting room", updatedFacility.getDescription());
        assertEquals("updated_meeting_room.png", updatedFacility.getImage());
    }

    @Test
    public void testDeleteFacility() {
        Facility facility = new Facility("Test Facility", "Description for test facility", "test_facility.png");
        facilityService.addFacility(facility);

        int facilityId = facility.getFacilityId();
        facilityService.deleteFacility(facilityId);

        Facility deletedFacility = facilityService.getFacilityById(facilityId);
        assertNull(deletedFacility);
    }

    @Test
    public void testGetFacilityById() {
        Facility facility = new Facility("Unique Facility", "Unique description", "unique.png");
        facilityService.addFacility(facility);

        Facility fetchedFacility = facilityService.getFacilityById(facility.getFacilityId());
        assertNotNull(fetchedFacility);
        assertEquals("Unique Facility", fetchedFacility.getName());
        assertEquals("Unique description", fetchedFacility.getDescription());
        assertEquals("unique.png", fetchedFacility.getImage());
    }
}
