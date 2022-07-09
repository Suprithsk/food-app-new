package com.example.foodiescorner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class check_out_activity extends AppCompatActivity {
    TextView totalItemAmount,totalAmount;
    Button submit;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        String total_item_amount=getIntent().getStringExtra("total_item_amount");
        String delivery_charge=getIntent().getStringExtra("delivery_charge");
        String total_amount=getIntent().getStringExtra("total_amount");
        totalItemAmount=findViewById(R.id.textView20);
        totalAmount=findViewById(R.id.textView21);
        submit=findViewById(R.id.button9);
        totalItemAmount.setText(total_item_amount);
        totalAmount.setText(total_amount);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(check_out_activity.this, "order successful", Toast.LENGTH_SHORT).show();
            }
        });
        
    }
}