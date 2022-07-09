package com.example.foodiescorner;

public class scroll_model3 {
    String productImage;
    String productTitle;
    String productMoney;

    public scroll_model3(String productImage,  String productTitle, String productMoney) {
        this.productImage = productImage;
        this.productTitle = productTitle;
        this.productMoney = productMoney;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductMoney() {
        return productMoney;
    }

    public void setProductMoney(String productMoney) {
        this.productMoney = productMoney;
    }
}


