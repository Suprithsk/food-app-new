package com.example.foodiescorner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {
    RecyclerView category_recycler_view_new;
    FirebaseFirestore firebaseFirestore;
    ArrayList<actual_category_model> actual_category_modelArrayList;
    actual_category_adapter actual_category_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        String title=getIntent().getStringExtra("category_name");
        TextView cat_text=findViewById(R.id.category_text);
        cat_text.setText(title);
        category_recycler_view_new=findViewById(R.id.category_activity_reccyler_view);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
        category_recycler_view_new.setLayoutManager(gridLayoutManager);
        actual_category_modelArrayList=new ArrayList<>();
        actual_category_adapter=new actual_category_adapter(actual_category_modelArrayList,this);
        category_recycler_view_new.setAdapter(actual_category_adapter);
        firebaseFirestore=FirebaseFirestore.getInstance();
        firebaseFirestore.collection(title).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot documentSnapshot : task.getResult()){
                                actual_category_modelArrayList.add(new actual_category_model(documentSnapshot.get("imageUrl2").toString(),documentSnapshot.get("food_name").toString(),documentSnapshot.get("rupees").toString()));
                            }
                            actual_category_adapter.notifyDataSetChanged();
                        }
                        else{
                            String error=task.getException().getMessage();
                            Toast.makeText(getBaseContext(), error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }
}