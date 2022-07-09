package com.example.foodiescorner;

public class cart_item_model {
    public  static final int CART_ITEM=0;
    public static final int TOTAL_AMOUNT=1;
    int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    //cart item
    String cart_icon;
    String cart_recipe;
    String cart_rupees;

    public cart_item_model( String cart_icon,int type, String cart_recipe, String cart_rupees) {
        this.type = type;
        this.cart_icon = cart_icon;
        this.cart_recipe = cart_recipe;
        this.cart_rupees = cart_rupees;
    }

    public String getCart_icon() {
        return cart_icon;
    }

    public void setCart_icon(String  cart_icon) {
        this.cart_icon = cart_icon;
    }

    public String getCart_recipe() {
        return cart_recipe;
    }

    public void setCart_recipe(String cart_recipe) {
        this.cart_recipe = cart_recipe;
    }

    public String getCart_rupees() {
        return cart_rupees;
    }

    public void setCart_rupees(String cart_rupees) {
        this.cart_rupees = cart_rupees;
    }
    //cart item


     //cart total item

    public cart_item_model(int type) {
        this.type = type;
    }


    //cart total item

}
