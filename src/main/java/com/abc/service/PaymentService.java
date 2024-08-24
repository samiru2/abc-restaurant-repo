package com.abc.service;

import java.util.List;
import com.abc.dao.PaymentDAO;
import com.abc.model.Payment;

public class PaymentService {

    private static PaymentService instance;
    private PaymentDAO paymentDAO;

    private PaymentService() {
        this.paymentDAO = new PaymentDAO();
    }

    public static PaymentService getInstance() {
        if (instance == null) {
            synchronized (PaymentService.class) {
                if (instance == null) {
                    instance = new PaymentService();
                }
            }
        }
        return instance;
    }

    public void addPayment(Payment payment) {
        paymentDAO.addPayment(payment);
    }

    public void updatePayment(Payment payment) {
        paymentDAO.updatePayment(payment);
    }

    public void deletePayment(int paymentID) {
        paymentDAO.deletePayment(paymentID);
    }

    public List<Payment> getAllPayments() {
        return paymentDAO.getAllPayments();
    }

    public Payment getPaymentById(int paymentID) {
        return paymentDAO.getPaymentById(paymentID);
    }
}
