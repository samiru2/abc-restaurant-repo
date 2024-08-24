package com.abc.model;

public class Reservation {
    private int reservationID;
    private int userID;
    private String date;
    private String time;
    private int numberOfPeople;
    private String status;

    public Reservation(int reservationID, int userID, String date, String time, int numberOfPeople, String status) {
        this.reservationID = reservationID;
        this.userID = userID;
        this.date = date;
        this.time = time;
        this.numberOfPeople = numberOfPeople;
        this.status = status;
    }

    public Reservation(int userID, String date, String time, int numberOfPeople, String status) {
        this.userID = userID;
        this.date = date;
        this.time = time;
        this.numberOfPeople = numberOfPeople;
        this.status = status;
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
}
