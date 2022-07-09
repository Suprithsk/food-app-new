package com.example.foodiescorner;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class cat_adapter extends RecyclerView.Adapter<cat_adapter.ViewHolder> {
    ArrayList<cat_model> list;
    public cat_adapter(ArrayList<cat_model> list) {
        this.list = list;
    }


    @NonNull
    @Override
    public cat_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cat,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull cat_adapter.ViewHolder holder, int position) {
        String icon=list.get(position).getCat_icon_link();
        String name=list.get(position).getCat_name();
        holder.setCat_name(name);
        holder.setCat_icon(icon);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView cat_icon;
        TextView cat_name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cat_icon=itemView.findViewById(R.id.cat_icon);
            cat_name=itemView.findViewById(R.id.cat_name);
        }

        private void setCat_icon(String icon_url){
            if(!icon_url.equals("null")) {
              Glide.with(itemView.getContext()).load(icon_url).apply(new RequestOptions().placeholder(R.drawable.ic_baseline_home_24)).into(cat_icon);

            }
        }
        private void setCat_name(String name){
            cat_name.setText(name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent categoryIntent=new Intent(itemView.getContext(),CategoryActivity.class);
                    categoryIntent.putExtra("category_name",name);
                    itemView.getContext().startActivity(categoryIntent);
                }
            });
        }
    }
}
