package com.abc.model;

public class Facility {
    
    private int facilityId;
    private String name;
    private String description;
    private String image;

    public Facility(int facilityId, String name, String description, String image) {
        this.facilityId = facilityId;
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public Facility(String name, String description, String image) {
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public Facility() {
    }

    public int getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(int facilityId) {
        this.facilityId = facilityId;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
