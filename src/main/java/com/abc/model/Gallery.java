package com.abc.model;

public class Gallery {
    
    private int galleryId;
    private String image;

    public Gallery(int galleryId, String image) {
        this.galleryId = galleryId;
        this.image = image;
    }

    public Gallery(String image) {
        this.image = image;
    }

    public Gallery() {
    }

    public int getGalleryId() {
        return galleryId;
    }

    public void setGalleryId(int galleryId) {
        this.galleryId = galleryId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
