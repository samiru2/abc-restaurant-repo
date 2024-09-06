package com.abc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.abc.model.Reservation;
import com.abc.model.User;
import com.abc.service.ReservationService;
import com.abc.service.UserService;
import com.abc.util.EmailUtility;

@WebServlet("/reservation")
public class ReservationController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ReservationService reservationService;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        reservationService = ReservationService.getInstance();
        userService = UserService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null || action.equals("list")) {
            listReservations(request, response);
        } else if (action.equals("add")) {
            showAddForm(request, response);
        } else if (action.equals("edit")) {
            showEditForm(request, response);
        } else if (action.equals("delete")) {
            deleteReservation(request, response);
        } else if (action.equals("accept")) {
            acceptReservation(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equals("add")) {
            addReservation(request, response);
        } else if (action.equals("update")) {
            updateReservation(request, response);
        }
    }

    private void listReservations(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Reservation> reservations = new ArrayList<>();
        try {
            reservations = reservationService.getAllReservations();
            request.setAttribute("reservations", reservations);
            request.getRequestDispatcher("WEB-INF/view/listReservations.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("WEB-INF/view/error.jsp").forward(request, response);
        }
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/view/addReservation.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int reservationID = Integer.parseInt(request.getParameter("id"));
        try {
            Reservation reservation = reservationService.getReservationById(reservationID);
            request.setAttribute("reservation", reservation);
            request.getRequestDispatcher("WEB-INF/view/editReservation.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("WEB-INF/view/error.jsp").forward(request, response);
        }
    }

    private void addReservation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userID = Integer.parseInt(request.getParameter("userID"));
        String date = request.getParameter("date");
        String time = request.getParameter("time");
        int numberOfPeople = Integer.parseInt(request.getParameter("numberOfPeople"));
        String status = "pending";
        String message = request.getParameter("message");

        Reservation reservation = new Reservation(userID, date, time, numberOfPeople, status, message);

        try {
            reservationService.addReservation(reservation);
            request.setAttribute("successMessage", "Your reservation has been placed successfully.");
        } catch (Exception e) {
            request.setAttribute("errorMessage", e.getMessage());
        }
        request.getRequestDispatcher("index.jsp#book-a-table").forward(request, response);
    }

    private void updateReservation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int reservationID = Integer.parseInt(request.getParameter("id"));
        int userID = Integer.parseInt(request.getParameter("userID"));
        String date = request.getParameter("date");
        String time = request.getParameter("time");
        int numberOfPeople = Integer.parseInt(request.getParameter("numberOfPeople"));
        String status = request.getParameter("status");
        String message = request.getParameter("message");

        Reservation updatedReservation = new Reservation(reservationID, userID, date, time, numberOfPeople, status, message, null, null, null);

        try {
            reservationService.updateReservation(updatedReservation);
            response.sendRedirect("reservation?action=list");
        } catch (Exception e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("WEB-INF/view/error.jsp").forward(request, response);
        }
    }


    private void deleteReservation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int reservationID = Integer.parseInt(request.getParameter("id"));
        try {
            reservationService.deleteReservation(reservationID);
            response.sendRedirect("reservation?action=list");
        } catch (Exception e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("WEB-INF/view/error.jsp").forward(request, response);
        }
    }
    
    private void acceptReservation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int reservationID = Integer.parseInt(request.getParameter("id"));
        try {
            Reservation reservation = reservationService.getReservationById(reservationID);
            reservation.setStatus("accepted");
            reservationService.updateReservation(reservation);

            // Fetch user email
            User user = userService.getUserById(reservation.getUserID());
            String userEmail = user.getEmail();

            // Prepare email content
            String subject = "Reservation Confirmation - ABC Restaurant";
            String message = "Dear " + user.getUsername() + ",\n\n" +
                             "Your reservation has been accepted!\n\n" +
                             "Reservation Details\n" +
                             "Reservation ID: " + reservation.getReservationID() + "\n" +
                             "Date: " + reservation.getDate() + "\n" +
                             "Time: " + reservation.getTime() + "\n" +
                             "Number of People: " + reservation.getNumberOfPeople() + "\n\n" +
                             "We look forward to serving you!\n\n" +
                             "Best regards,\n" +
                             "ABC Restaurant";

            // Send confirmation email
            try {
                EmailUtility.sendEmail(userEmail, subject, message);
            } catch (Exception e) {
                e.printStackTrace();
            }

            response.sendRedirect("reservation?action=list");
        } catch (Exception e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("WEB-INF/view/error.jsp").forward(request, response);
        }
    }
}
