package com.abc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.abc.model.Payment;
import com.abc.service.PaymentService;

@WebServlet("/payment")
public class PaymentController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private PaymentService paymentService;

    @Override
    public void init() throws ServletException {
        paymentService = PaymentService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null || action.equals("list")) {
            listPayments(request, response);
        } else if (action.equals("add")) {
            showAddForm(request, response);
        } else if (action.equals("edit")) {
            showEditForm(request, response);
        } else if (action.equals("delete")) {
            deletePayment(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action.equals("add")) {
            addPayment(request, response);
        } else if (action.equals("update")) {
            updatePayment(request, response);
        }
    }

    private void listPayments(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Payment> payments = new ArrayList<>();
        try {
            payments = paymentService.getAllPayments();
            request.setAttribute("payments", payments);
            request.getRequestDispatcher("WEB-INF/view/listPayments.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("WEB-INF/view/error.jsp").forward(request, response);
        }
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/view/addPayment.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int paymentID = Integer.parseInt(request.getParameter("id"));
        Payment existingPayment = paymentService.getPaymentById(paymentID);
        request.setAttribute("payment", existingPayment);
        request.getRequestDispatcher("WEB-INF/view/editPayment.jsp").forward(request, response);
    }

    private void addPayment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderID = Integer.parseInt(request.getParameter("orderID"));
        String dateTime = request.getParameter("dateTime");
        String paymentMethod = request.getParameter("paymentMethod");
        double price = Double.parseDouble(request.getParameter("price"));

        Payment newPayment = new Payment(orderID, dateTime, paymentMethod, price);
        paymentService.addPayment(newPayment);
        response.sendRedirect("payment?action=list");
    }

    private void updatePayment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int paymentID = Integer.parseInt(request.getParameter("paymentID"));
        int orderID = Integer.parseInt(request.getParameter("orderID"));
        String dateTime = request.getParameter("dateTime");
        String paymentMethod = request.getParameter("paymentMethod");
        double price = Double.parseDouble(request.getParameter("price"));

        Payment payment = new Payment(paymentID, orderID, dateTime, paymentMethod, price);
        paymentService.updatePayment(payment);
        response.sendRedirect("payment?action=list");
    }

    private void deletePayment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int paymentID = Integer.parseInt(request.getParameter("id"));
        paymentService.deletePayment(paymentID);
        response.sendRedirect("payment?action=list");
    }
}
