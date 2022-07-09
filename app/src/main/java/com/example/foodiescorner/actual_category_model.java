package com.example.foodiescorner;

public class actual_category_model {
    String image_icon_link;
    String text_value1;
    String text_value_rupees;

    public actual_category_model(String image_icon_link, String text_value1, String text_value_rupees) {
        this.image_icon_link = image_icon_link;
        this.text_value1 = text_value1;
        this.text_value_rupees = text_value_rupees;
    }

    public String getImage_icon() {
        return image_icon_link;
    }

    public void setImage_icon(String image_icon_link) {
        this.image_icon_link = image_icon_link;
    }

    public String getText_value1() {
        return text_value1;
    }

    public void setText_value1(String text_value1) {
        this.text_value1 = text_value1;
    }

    public String getText_value_rupees() {
        return text_value_rupees;
    }

    public void setText_value_rupees(String text_value_rupees) {
        this.text_value_rupees = text_value_rupees;
    }
}
