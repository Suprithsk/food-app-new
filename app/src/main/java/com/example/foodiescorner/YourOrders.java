package com.example.foodiescorner;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.WriteBatch;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link YourOrders#newInstance} factory method to
 * create an instance of this fragment.
 */
public class YourOrders extends Fragment {
   public ArrayList<cart_item_model> cart_item_modelArrayList=new ArrayList<>();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public YourOrders() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment YourOrders.
     */
    // TODO: Rename and change types and number of parameters
    public static YourOrders newInstance(String param1, String param2) {
        YourOrders fragment = new YourOrders();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    RecyclerView cart_items_recycler;
    FirebaseFirestore firebaseFirestore;
    TextView totalCartPrice;
    Button Continue,delete;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        firebaseFirestore=FirebaseFirestore.getInstance();
        View view= inflater.inflate(R.layout.fragment_your_orders, container, false);
        cart_items_recycler=view.findViewById(R.id.cart_recycler_view);

//        Continue=view.findViewById(R.id.cart_continue_btn);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        cart_items_recycler.setLayoutManager(layoutManager);

        cart_item_adapter cart_item_adapter=new cart_item_adapter(cart_item_modelArrayList);
        cart_items_recycler.setAdapter(cart_item_adapter);

        firebaseFirestore.collection("cart_details").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot documentSnapshot : task.getResult()){
                                cart_item_modelArrayList.add(new cart_item_model(documentSnapshot.get("icon_link").toString(),0,documentSnapshot.get("food_name").toString(),documentSnapshot.get("food_money").toString()));
                            }
                            if(cart_item_modelArrayList.size()>0){
                                cart_item_modelArrayList.add(new cart_item_model(cart_item_model.TOTAL_AMOUNT));
                            }
                            cart_item_adapter.notifyDataSetChanged();
                        }
                        else{
                            String error=task.getException().getMessage();
                            Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });


        return view;

    }
}