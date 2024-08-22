package com.abc.model;

public class Offer {

    private int offerId;
    private String title;
    private String description;
    private int price;
    private String image;

    public Offer(int offerId, String title, String description, int price, String image) {
        this.offerId = offerId;
        this.title = title;
        this.description = description;
        this.price = price;
        this.image = image;
    }

    public Offer(String title, String description, int price, String image) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.image = image;
    }

    public Offer() {
    }

    public int getOfferId() {
        return offerId;
    }

    public void setOfferId(int offerId) {
        this.offerId = offerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
