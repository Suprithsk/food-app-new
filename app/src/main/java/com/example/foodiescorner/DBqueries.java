package com.example.foodiescorner;

import android.content.Context;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class DBqueries {
        public static FirebaseFirestore firebaseFirestore=FirebaseFirestore.getInstance();
        public static ArrayList<String> cartList=new ArrayList<>();
        public static ArrayList<ArrayList<cart_item_model>> cartModelArrayList=new ArrayList<>();

        public static void removeFromWishList(final int index,final Context context){
                final String removedProductId=cartList.get(index);
        }

}
