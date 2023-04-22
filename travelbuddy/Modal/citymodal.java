package com.example.travelbuddy.Modal;

public class citymodal {
    private String image,name;

    public citymodal(){}

    public citymodal(String img, String name) {
        this.image = img;
        this.name = name;
    }

    public String getImage() {
        return image;
    }
    public String getName() {
        return name;
    }
}
