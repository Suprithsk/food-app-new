package com.example.foodiescorner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.foodiescorner.databinding.ActivityHomePageBinding;

public class home_page extends AppCompatActivity {


    ActivityHomePageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replacefragment(new Home_fragment());

        binding.bottomNavigationView.setOnItemSelectedListener( item -> {
            switch(item.getItemId()){
                case R.id.homeicon:
                    replacefragment(new Home_fragment());
                    break;
                case R.id.searchicon:
                    replacefragment(new YourOrders());
                    break;
                case R.id.playlisticon:
                    replacefragment(new Profilefrag());
                    break;
            }

            return true;
        });

    }
    private void replacefragment(Fragment fragment){
        FragmentManager fmangaer = getSupportFragmentManager();
        FragmentTransaction ftransac = getSupportFragmentManager().beginTransaction();
        ftransac.replace(R.id.flayout,fragment);
        ftransac.commit();
    }



}