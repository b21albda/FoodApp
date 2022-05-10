package com.example.foodapp.data;

public class Auxdata {
    private final String description;
    private final String img;
    private final String type;

    public Auxdata(String description, String img, String type) {
        this.description = description;
        this.img = img;
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public String getImg() {
        return img;
    }

    public String getType() {
        return type;
    }
}
