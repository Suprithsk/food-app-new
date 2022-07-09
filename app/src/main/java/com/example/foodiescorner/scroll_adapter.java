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

public class scroll_adapter extends RecyclerView.Adapter<scroll_adapter.ViewHolder> {

    ArrayList<scroll_model> modelArrayList;

    public scroll_adapter(ArrayList<scroll_model> modelArrayList) {
        this.modelArrayList = modelArrayList;
    }

    @NonNull
    @Override
    public scroll_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_scroll_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull scroll_adapter.ViewHolder holder, int position) {
        String resource=modelArrayList.get(position).getProductImage();
        String rupees=modelArrayList.get(position).getProductMoney();
        String product_title=modelArrayList.get(position).getProductTitle();

        holder.setProductImage(resource,rupees,product_title);
        holder.setRupeesText(rupees);
        holder.setProductTitle(product_title);
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView productImage;
        TextView rupeesText;
        TextView productTitle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage=itemView.findViewById(R.id.product_image_h_s_2);
            rupeesText=itemView.findViewById(R.id.rupees_text_h_s_2);
            productTitle=itemView.findViewById(R.id.product_text1_h_s_2);
        }
        private void setProductImage(String resource,String new_rupees,String product_title){
            Glide.with(itemView.getContext()).load(resource).apply(new RequestOptions().placeholder(R.drawable.ic_baseline_home_24)).into(productImage);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent productIntent=new Intent(itemView.getContext(),product_details.class);
                    productIntent.putExtra("image",resource);
                    productIntent.putExtra("description",R.string.food_description);
                    productIntent.putExtra("money",new_rupees);
                    productIntent.putExtra("product_title",product_title);
                    itemView.getContext().startActivity(productIntent);
                }
            });


        }
        private void setRupeesText(String rupees){
            rupeesText.setText(rupees);
        }
        private void setProductTitle(String product_title){
            productTitle.setText(product_title);
        }
    }
}
