package com.example.foodiescorner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class admin_actvity extends AppCompatActivity {

    Button buttonForCat;
    Button buttonForScroll;
    Button buttonForScroll2;
    Button buttonForScroll3;
    Button buttonForBanner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity);
        buttonForCat=findViewById(R.id.btnforcategory);
        buttonForScroll=findViewById(R.id.btnforscroll1);
        buttonForScroll2=findViewById(R.id.btnforsccroll2);
        buttonForScroll3=findViewById(R.id.btnforcategory5);
        buttonForBanner=findViewById(R.id.btnforcategory4);
        buttonForCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(admin_actvity.this,admin_category.class);
                startActivity(it);
            }
        });
        buttonForScroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(admin_actvity.this,admin_scroll_activity.class);
                startActivity(it);
            }
        });
        buttonForScroll2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(admin_actvity.this,admin_scroll2.class);
                startActivity(it);
            }
        });
        buttonForScroll3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(admin_actvity.this, scroll_layout_admin3.class);
                startActivity(it);
            }
        });
        buttonForBanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(admin_actvity.this, AdminBanner.class);
                startActivity(it);
            }
        });

    }
}