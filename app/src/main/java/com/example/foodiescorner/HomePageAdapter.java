package com.example.foodiescorner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomePageAdapter extends RecyclerView.Adapter {
    private ArrayList<HomePageModel> HomePageModelList;

    public HomePageAdapter(ArrayList<HomePageModel> HomePageModelList){
        this.HomePageModelList=HomePageModelList;
    }

    @Override
    public int getItemViewType(int position) {
        switch (HomePageModelList.get(position).getType()){
            case 0:
                return HomePageModel.SCROLL_SLIDER;
            default:
                return -1;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType){
            case HomePageModel.SCROLL_SLIDER:
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_scroll_item_layout,parent,false);


        }
        return null;
    }
}
