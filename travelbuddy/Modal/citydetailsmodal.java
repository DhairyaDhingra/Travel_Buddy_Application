package com.example.travelbuddy.Modal;

public class citydetailsmodal {

    private String image,name,location,price,website;

    public citydetailsmodal(){}

    public citydetailsmodal(String image, String name, String location, String price, String website) {
        this.image = image;
        this.name = name;
        this.location = location;
        this.price = price;
        this.website = website;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getPrice() {
        return price;
    }

    public String getWebsite() {
        return website;
    }
}
