package com.abc.service;

import java.util.List;
import com.abc.dao.ReservationDAO;
import com.abc.model.Reservation;

public class ReservationService {

    private static ReservationService instance;
    private ReservationDAO reservationDAO;

    private ReservationService() {
        this.reservationDAO = new ReservationDAO();
    }

    public static ReservationService getInstance() {
        if (instance == null) {
            synchronized (ReservationService.class) {
                if (instance == null) {
                    instance = new ReservationService();
                }
            }
        }
        return instance;
    }

    public void addReservation(Reservation reservation) {
        reservationDAO.addReservation(reservation);
    }

    public void updateReservation(Reservation reservation) {
        reservationDAO.updateReservation(reservation);
    }

    public void deleteReservation(int reservationID) {
        reservationDAO.deleteReservation(reservationID);
    }

    public List<Reservation> getAllReservations() {
        return reservationDAO.getAllReservations();
    }

    public Reservation getReservationById(int reservationID) {
        return reservationDAO.getReservationById(reservationID);
    }
}
