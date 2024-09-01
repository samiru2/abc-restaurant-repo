package com.abc.model;

public class Order {
    private int orderID;
    private int userID;
    private String orderDetails;
    private String orderDate;
    private double totalPrice;
    private String status;
    private String username;
    private String phone;
    private String email;

    // Constructors
    public Order(int orderID, int userID, String orderDetails, String orderDate, double totalPrice, String status, String username, String phone, String email) {
        this.orderID = orderID;
        this.userID = userID;
        this.orderDetails = orderDetails;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.status = status;
        this.username = username;
        this.phone = phone;
        this.email = email;
    }

    public Order(int userID, String orderDetails, String orderDate, double totalPrice, String status) {
        this.userID = userID;
        this.orderDetails = orderDetails;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public Order() {}

    // Getters and Setters
    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(String orderDetails) {
        this.orderDetails = orderDetails;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
