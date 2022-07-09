package com.example.foodiescorner;

public class cat_model {
    String cat_icon_link;
    String cat_name;

    public cat_model(String cat_icon_link, String cat_name) {
        this.cat_icon_link = cat_icon_link;
        this.cat_name = cat_name;
    }



    public String getCat_icon_link() {
        return cat_icon_link;
    }


    public void setCat_icon_link(String cat_icon_link) {
        this.cat_icon_link = cat_icon_link;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }
}
