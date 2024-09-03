package com.abc.model;

public class Reservation {
    private int reservationID;
    private int userID;
    private String date;
    private String time;
    private int numberOfPeople;
    private String status;
    private String message;
    private String username;  
    private String phone;     
    private String email;     


    public Reservation(int reservationID, int userID, String date, String time, int numberOfPeople, String status, String message, String username, String phone, String email) {
        this.reservationID = reservationID;
        this.userID = userID;
        this.date = date;
        this.time = time;
        this.numberOfPeople = numberOfPeople;
        this.status = status;
        this.message = message;
        this.username = username;
        this.phone = phone;
        this.email = email;
    }


    public Reservation(int userID, String date, String time, int numberOfPeople, String status, String message) {
        this.userID = userID;
        this.date = date;
        this.time = time;
        this.numberOfPeople = numberOfPeople;
        this.status = status;
        this.message = message;
    }


    public Reservation() {}


    public int getReservationID() {
        return reservationID;
    }

    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
