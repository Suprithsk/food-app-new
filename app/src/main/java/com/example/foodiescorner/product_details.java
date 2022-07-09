package com.example.foodiescorner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class
product_details extends AppCompatActivity {
    TextView food_name;
    TextView price;
    ImageView food_icon;
    Button add_to_cart;
    FirebaseFirestore firebaseFirestore;
    DocumentReference documentReference;
    String value1;
    int val=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        food_name=findViewById(R.id.food_name);
        food_icon=findViewById(R.id.detail_image);
        price=findViewById(R.id.amount);
        add_to_cart=findViewById(R.id.order_now_btn);
        firebaseFirestore=FirebaseFirestore.getInstance();
        String icon_link=getIntent().getStringExtra("image");
        String food_name_string=getIntent().getStringExtra("product_title");
        String food_money_string=getIntent().getStringExtra("money");
        Glide.with(getBaseContext()).load(icon_link).apply(new RequestOptions().placeholder(R.drawable.ic_baseline_home_24)).into(food_icon);
        food_name.setText(food_name_string);
        price.setText(food_money_string);
        add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                val=Integer.parseInt(food_money_string);
                Map<String,Object> value=new HashMap<>();
                value.put("value_money",val);
                firebaseFirestore.collection("total_amount").add(value);
                Map<String,Object> cart=new HashMap<>();
                cart.put("icon_link",icon_link);
                cart.put("food_name",food_name_string);
                cart.put("food_money",food_money_string);
                cart.put("food_money_int",val);
                firebaseFirestore.collection("cart_details").add(cart)
                        .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentReference> task) {
                                if(task.isSuccessful()) {
                                    Toast.makeText(product_details.this, "added to your cart", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}