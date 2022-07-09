package com.example.foodiescorner;

import java.util.ArrayList;

public class HomePageModel {
    public static final int SCROLL_SLIDER=0;
    ArrayList<scroll_model> modelArrayList;
    private int type;

    public HomePageModel(ArrayList<scroll_model> modelArrayList, int type) {
        this.modelArrayList = modelArrayList;
        this.type = type;
    }

    public ArrayList<scroll_model> getModelArrayList() {
        return modelArrayList;
    }

    public void setModelArrayList(ArrayList<scroll_model> modelArrayList) {
        this.modelArrayList = modelArrayList;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
