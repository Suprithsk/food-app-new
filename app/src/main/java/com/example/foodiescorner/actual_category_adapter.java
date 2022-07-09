package com.example.foodiescorner;

import android.content.Context;
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

public class actual_category_adapter extends RecyclerView.Adapter<actual_category_adapter.ViewHolder> {

    ArrayList<actual_category_model> actual_category_modelArrayList;
    Context context;

    public actual_category_adapter(ArrayList<actual_category_model> actual_category_modelArrayList, Context context) {
        this.actual_category_modelArrayList = actual_category_modelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.custom_grid_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        actual_category_model model=actual_category_modelArrayList.get(position);
        String icon=actual_category_modelArrayList.get(position).getImage_icon();
        holder.setCategory_actual_icon(icon,model.getText_value_rupees(),model.getText_value1());
        holder.text_items.setText(model.getText_value1());
        holder.text_rupees.setText(model.getText_value_rupees());
    }

    @Override
    public int getItemCount() {
        return actual_category_modelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView category_actual_icon;
        TextView text_items;
        TextView text_rupees;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            category_actual_icon=itemView.findViewById(R.id.category_image_1);
            text_items=itemView.findViewById(R.id.text_items);
            text_rupees=itemView.findViewById(R.id.text_rupees);
        }
        private void setCategory_actual_icon(String imageUrl,String new_rupees,String product_title){
            Glide.with(itemView.getContext()).load(imageUrl).apply(new RequestOptions().placeholder(R.drawable.foodexe)).into(category_actual_icon);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent productIntent=new Intent(itemView.getContext(),product_details.class);
                    productIntent.putExtra("image",imageUrl);
                    productIntent.putExtra("money",new_rupees);
                    productIntent.putExtra("product_title",product_title);
                    itemView.getContext().startActivity(productIntent);
                }
            });


        }
    }
}
