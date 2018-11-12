package com.example.ankita.downloadwebcontent;

public class Hero {

    String name,  imageUrl;

    public Hero(String imageUrl, String name) {
        this.name = name;
        this.imageUrl = imageUrl;

    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}

