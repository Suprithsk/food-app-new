package com.example.foodiescorner;

public class uriModel {
    String imageUrl;
    String category_name;

    public uriModel(String imageUrl, String category_name) {
        this.imageUrl = imageUrl;
        this.category_name = category_name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
}
