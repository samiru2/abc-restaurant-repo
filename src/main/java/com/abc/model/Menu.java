package com.abc.model;

public class Menu {

    private int menuID;
    private String name;
    private String description;
    private double price;
    private String category;
    private String image;

    public Menu(int menuID, String name, String description, double price, String category, String image) {
        this.menuID = menuID;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.image = image;
    }

    public Menu(int menuID, String name, double price, String category) {
        this.menuID = menuID;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public Menu(int menuID, String name, double price) {
        this.menuID = menuID;
        this.name = name;
        this.price = price;
    }

    public Menu(int menuID, double price) {
        this.menuID = menuID;
        this.price = price;
    }

    public Menu() {
    }

    // Getters and setters
    public int getMenuID() {
        return menuID;
    }

    public void setMenuID(int menuID) {
        this.menuID = menuID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
