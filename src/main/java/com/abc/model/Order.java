package com.abc.model;

public class Order {
    private int orderID;
    private int userID;
    private int menuID;
    private String type;
    private double totalPrice;
    private String status;

    public Order(int orderID, int userID, int menuID, String type, double totalPrice, String status) {
        this.orderID = orderID;
        this.userID = userID;
        this.menuID = menuID;
        this.type = type;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public Order(int userID, int menuID, String type, double totalPrice, String status) {
        this.userID = userID;
        this.menuID = menuID;
        this.type = type;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public Order() {}

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

    public int getMenuID() {
        return menuID;
    }

    public void setMenuID(int menuID) {
        this.menuID = menuID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
}
