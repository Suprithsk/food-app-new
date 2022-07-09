package com.example.foodiescorner;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class cart_item_adapter extends RecyclerView.Adapter {
    ArrayList<cart_item_model> cart_item_modelArrayList;
    OnItemCLickListener listener;
    public cart_item_adapter(ArrayList<cart_item_model> cart_item_modelArrayList) {
        this.cart_item_modelArrayList=cart_item_modelArrayList;
    }
    public interface OnItemCLickListener{
        void onItemClick(int position);
    }
    public void SetOnItemClickListener(OnItemCLickListener cLickListener){
        listener=cLickListener;
    }

    @Override
    public int getItemViewType(int position) {
        switch (cart_item_modelArrayList.get(position).getType()){
            case 0:
                return cart_item_model.CART_ITEM;
            case 1:
                return cart_item_model.TOTAL_AMOUNT;
            default:
                return -1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType){
            case cart_item_model.CART_ITEM:
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_layout,parent,false);
                return new cartItemViewHolder(view,listener);
            case cart_item_model.TOTAL_AMOUNT:
                View view1= LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_total_amount_layout,parent,false);
                return new cartTotalAmountViewHolder(view1);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (cart_item_modelArrayList.get(position).getType()) {
            case cart_item_model.CART_ITEM:
                String resource=cart_item_modelArrayList.get(position).getCart_icon();
                String title=cart_item_modelArrayList.get(position).getCart_recipe();
                String rupees=cart_item_modelArrayList.get(position).getCart_rupees();
                ((cartItemViewHolder)holder).setItemDetails(resource,title,rupees,position);
                break;
            case cart_item_model.TOTAL_AMOUNT:
                int total_item_price=0;
                int total_item_amount;
                for(int i=0;i<cart_item_modelArrayList.size();i++){
                    if(cart_item_modelArrayList.get(i).getType()==cart_item_model.CART_ITEM){
                        total_item_price+=Integer.parseInt(cart_item_modelArrayList.get(i).getCart_rupees());
                    }
                }
                total_item_amount=total_item_price+30;

                ((cartTotalAmountViewHolder)holder).setTotalAmount(total_item_price,"30",total_item_amount );
                break;
            default:
                return;
        }
    }

    @Override
    public int getItemCount() {
        return cart_item_modelArrayList.size();
    }
    public class cartItemViewHolder extends RecyclerView.ViewHolder{
        ImageView product_image_cart;
        TextView product_title_cart;
        TextView product_amount_cart;
        private cart_item_adapter cartItemAdapter;
        public cartItemViewHolder(@NonNull View itemView,OnItemCLickListener listener) {
            super(itemView);
            product_image_cart=itemView.findViewById(R.id.product_image_cart);
            product_title_cart=itemView.findViewById(R.id.product_title_cart);
            product_amount_cart=itemView.findViewById(R.id.product_amount_cart);

        }
        public void setItemDetails(String resource,String product_title,String amount,int position){
            Glide.with(itemView.getContext()).load(resource).apply(new RequestOptions().placeholder(R.drawable.ic_baseline_home_24)).into(product_image_cart);
            product_title_cart.setText(product_title);
            product_amount_cart.setText(amount);
        }


    }
    public class cartTotalAmountViewHolder extends RecyclerView.ViewHolder{
        TextView total_item_price;
        TextView delivery_price;
        TextView total_price;
        Button button;
        public cartTotalAmountViewHolder(@NonNull View itemView) {
            super(itemView);
            total_item_price=itemView.findViewById(R.id.total_items_price);
            delivery_price=itemView.findViewById(R.id.delivery_price);
            total_price=itemView.findViewById(R.id.total_price);
            button=itemView.findViewById(R.id.continuefur);
        }
        public void setTotalAmount(int total_item_amount,String delivery_charge,int total_amount){

            total_item_price.setText(""+total_item_amount);
            delivery_price.setText(delivery_charge);
            total_price.setText(""+total_amount);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent it=new Intent(itemView.getContext(),check_out_activity.class);
                    it.putExtra("total_item_amount",""+total_item_amount);
                    it.putExtra("delivery_charge","30");
                    it.putExtra("total_amount",""+total_amount);
                    itemView.getContext().startActivity(it);
                }
            });
        }
    }
}
