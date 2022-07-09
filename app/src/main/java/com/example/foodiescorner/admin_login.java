package com.example.foodiescorner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class admin_login extends AppCompatActivity {
    EditText editTex1,editText2;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        editTex1=findViewById(R.id.usernammm);
        editText2=findViewById(R.id.Name3);
        button=findViewById(R.id.button8);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editTex1.getText().toString().equals("suprith")&&editText2.getText().toString().equals("suprith") || editTex1.getText().toString().equals("taksheel") && editText2.getText().toString().equals("taksheel")){
                    Intent it=new Intent(admin_login.this,admin_actvity.class);
                    startActivity(it);
                }
                else{
                    Toast.makeText(admin_login.this, "wrong credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}