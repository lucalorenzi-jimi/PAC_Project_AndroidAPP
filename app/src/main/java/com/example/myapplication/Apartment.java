package com.example.myapplication;

public class Apartment {

    public String ownerEmail;

    private String name;

    public int numGuests;

    public String location;

    private String imageURL;

    public Double pricePerNight;

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public void setNumGuests(int numGuests) {
        this.numGuests = numGuests;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public int getNumGuests() {
        return numGuests;
    }

    public String getLocation() {
        return location;
    }

    public Double getPricePerNight() {
        return pricePerNight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return this.getImageURL();
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Apartment(String name, String location, String imageURL, double pricePerNight) {
        this.name = name;
        this.location = location;
        this.imageURL = imageURL;
        this.ownerEmail = null;
        this.pricePerNight = pricePerNight;
        this.numGuests = 0;
    }
}
