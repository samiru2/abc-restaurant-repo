package com.abc.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.abc.model.Reservation;
import com.abc.dao.ReservationDAO;

public class ReservationServiceTest {

    private ReservationService reservationService;
    private ReservationDAO reservationDAO;

    @Test
    public void testAddReservation() {
        Reservation reservation = new Reservation(201, "2024-09-15", "19:00", 13, "pending", "Test reservation");

        reservationService.addReservation(reservation);

        Reservation fetchedReservation = reservationDAO.getReservationById(201);
        assertNotNull(fetchedReservation);
        assertEquals(201, fetchedReservation.getReservationID());
        assertEquals(reservation.getUserID(), fetchedReservation.getUserID());
        assertEquals(reservation.getDate(), fetchedReservation.getDate());
        assertEquals(reservation.getTime(), fetchedReservation.getTime());
        assertEquals(reservation.getNumberOfPeople(), fetchedReservation.getNumberOfPeople());
        assertEquals(reservation.getStatus(), fetchedReservation.getStatus());
        assertEquals(reservation.getMessage(), fetchedReservation.getMessage());
    }

    @Test
    public void testUpdateReservation() {
        Reservation reservation = new Reservation(201, "2024-09-15", "19:00", 13, "pending", "Test reservation");
        reservationService.addReservation(reservation);

        reservation.setStatus("accepted");
        reservation.setMessage("Reservation updated");

        reservationService.updateReservation(reservation);

        Reservation updatedReservation = reservationDAO.getReservationById(201);
        assertNotNull(updatedReservation);
        assertEquals("accepted", updatedReservation.getStatus());
        assertEquals("Reservation updated", updatedReservation.getMessage());
    }

    @Test
    public void testDeleteReservation() {
        Reservation reservation = new Reservation(201, "2024-09-15", "19:00", 4, "pending", "Test reservation");
        reservationService.addReservation(reservation);

        reservationService.deleteReservation(201);

        Reservation deletedReservation = reservationDAO.getReservationById(201);
        assertNull(deletedReservation);
    }

    @Test
    public void testGetAllReservations() {
        Reservation reservation1 = new Reservation(1, "2024-09-15", "19:00", 4, "pending", "Test reservation 1");
        Reservation reservation2 = new Reservation(2, "2024-09-16", "20:00", 2, "pending", "Test reservation 2");
        reservationService.addReservation(reservation1);
        reservationService.addReservation(reservation2);

        List<Reservation> reservations = reservationService.getAllReservations();
        assertTrue(reservations.size() >= 2);
        assertTrue(reservations.stream().anyMatch(r -> r.getReservationID() == 1));
        assertTrue(reservations.stream().anyMatch(r -> r.getReservationID() == 2));
    }

    @Test
    public void testGetReservationById() {
        Reservation reservation = new Reservation(1, "2024-09-15", "19:00", 4, "pending", "Test reservation");
        reservationService.addReservation(reservation);

        Reservation fetchedReservation = reservationService.getReservationById(1);
        assertNotNull(fetchedReservation);
        assertEquals(1, fetchedReservation.getReservationID());
    }
}
