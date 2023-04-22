package com.example.travelbuddy;

public class Model {

    String id;
    String gender;


    public Model(String header) {
        this.id = header;
//        this.gender = content;
    }


    public String getHeader() {
        return id;
    }

//    public String getContent() {
//        return gender;
//    }

}
