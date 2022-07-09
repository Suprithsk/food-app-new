package com.example.foodiescorner;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Home_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home_fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Home_fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Home_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Home_fragment newInstance(String param1, String param2) {
        Home_fragment fragment = new Home_fragment();
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

    RecyclerView cat_recylerview;
    RecyclerView scroll_recycler_view;
    cat_adapter cat_adapter;
    ImageSlider imageSlider;
    TextView scroll_title;
    Button view_all_btn;
    RecyclerView horizontal_recycler_view;
    private ArrayList<cat_model> list;
    private FirebaseFirestore firebaseFirestore;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home_fragment,container,false);
        // category section
        cat_recylerview=view.findViewById(R.id.cat_rec);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        cat_recylerview.setLayoutManager(layoutManager);

        list=new ArrayList<cat_model>();

        cat_adapter=new cat_adapter(list);
        cat_recylerview.setAdapter(cat_adapter);

        firebaseFirestore=FirebaseFirestore.getInstance();
        firebaseFirestore.collection("categories2").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot documentSnapshot : task.getResult()){
                                list.add(new cat_model(documentSnapshot.get("imageUrl").toString(),documentSnapshot.get("category_name").toString()));
                            }
                            cat_adapter.notifyDataSetChanged();
                        }
                        else{
                            String error=task.getException().getMessage();
                            Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });





        imageSlider=view.findViewById(R.id.imageSlider);
        // category section
        // image slider section
        List<SlideModel> slideModels=new ArrayList<>();


        firebaseFirestore.collection("imageSlider").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot documentSnapshot : task.getResult()){
                                slideModels.add(new SlideModel(documentSnapshot.getString("imageUrl"),ScaleTypes.FIT));
                                imageSlider.setImageList(slideModels,ScaleTypes.FIT);
                            }
                        }
                        else{
                            String error=task.getException().getMessage();
                            Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });




        // image slider section
        //scroll view
        scroll_title=view.findViewById(R.id.horizontal_titlle_1);


        horizontal_recycler_view=view.findViewById(R.id.scroll_recycler_2);
        ArrayList<scroll_model> modelArrayList=new ArrayList<>();
        scroll_adapter scroll_adapter=new scroll_adapter(modelArrayList);
        LinearLayoutManager layoutManager1=new LinearLayoutManager(view.getContext());
        layoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        horizontal_recycler_view.setLayoutManager(layoutManager1);
        horizontal_recycler_view.setAdapter(scroll_adapter);
        firebaseFirestore.collection("scroll_items").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot documentSnapshot : task.getResult()){
                                modelArrayList.add(new scroll_model(documentSnapshot.get("imageUrl2").toString(),documentSnapshot.get("food_name").toString(),documentSnapshot.get("rupees").toString()));
                            }
                            scroll_adapter.notifyDataSetChanged();
                        }
                        else{
                            String error=task.getException().getMessage();
                            Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        horizontal_recycler_view=view.findViewById(R.id.scroll_recycler_21);
        ArrayList<scroll_model2> modelArrayList1=new ArrayList<>();
        scroll_adapter2 scroll_adapter2=new scroll_adapter2(modelArrayList1);
        LinearLayoutManager layoutManager2=new LinearLayoutManager(view.getContext());
        layoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        horizontal_recycler_view.setLayoutManager(layoutManager2);
        horizontal_recycler_view.setAdapter(scroll_adapter2);
        firebaseFirestore.collection("scroll_items2").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot documentSnapshot : task.getResult()){
                                modelArrayList1.add(new scroll_model2(documentSnapshot.get("imageUrl2").toString(),documentSnapshot.get("food_name").toString(),documentSnapshot.get("rupees").toString()));
                            }
                            scroll_adapter2.notifyDataSetChanged();
                        }
                        else{
                            String error=task.getException().getMessage();
                            Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        horizontal_recycler_view=view.findViewById(R.id.scroll_recycler_3);
        ArrayList<scroll_model3> modelArrayList3=new ArrayList<>();
        scroll_adapter3 scroll_adapter3=new scroll_adapter3(modelArrayList3);
        LinearLayoutManager layoutManager3=new LinearLayoutManager(view.getContext());
        layoutManager3.setOrientation(LinearLayoutManager.HORIZONTAL);
        horizontal_recycler_view.setLayoutManager(layoutManager3);
        horizontal_recycler_view.setAdapter(scroll_adapter3);
        firebaseFirestore.collection("scroll_items3").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot documentSnapshot : task.getResult()){
                                modelArrayList3.add(new scroll_model3(documentSnapshot.get("imageUrl2").toString(),documentSnapshot.get("food_name").toString(),documentSnapshot.get("rupees").toString()));
                            }
                            scroll_adapter2.notifyDataSetChanged();
                        }
                        else{
                            String error=task.getException().getMessage();
                            Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        //scroll view
        return view;



    }
}