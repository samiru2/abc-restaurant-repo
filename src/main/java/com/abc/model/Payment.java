package com.abc.model;

public class Payment {
    private int paymentID;
    private int orderID;
    private String dateTime;
    private String paymentMethod;
    private double price;

    public Payment(int paymentID, int orderID, String dateTime, String paymentMethod, double price) {
        this.paymentID = paymentID;
        this.orderID = orderID;
        this.dateTime = dateTime;
        this.paymentMethod = paymentMethod;
        this.price = price;
    }

    public Payment(int orderID, String dateTime, String paymentMethod, double price) {
        this.orderID = orderID;
        this.dateTime = dateTime;
        this.paymentMethod = paymentMethod;
        this.price = price;
    }

    public Payment() {}

    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
