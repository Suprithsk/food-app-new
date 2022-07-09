package com.example.foodiescorner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgot_pass extends AppCompatActivity {
    EditText email;
    Button forgotbtn;
    TextView goback;
    FirebaseAuth firebaseAuth;
    ViewGroup viewGroup;
    ImageView gmail;
    TextView textshow;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);
        email=findViewById(R.id.usernammm);
        forgotbtn=findViewById(R.id.resetbutton);
        firebaseAuth=FirebaseAuth.getInstance();
        goback=findViewById(R.id.textView6);
        gmail=findViewById(R.id.imageView2);
        textshow=findViewById(R.id.textView7);
        progressBar=findViewById(R.id.progressBar);
        viewGroup=findViewById(R.id.viewgrp);

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkinputs();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        forgotbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forgotbtn.setEnabled(false);
                progressBar.setVisibility(View.VISIBLE);
                firebaseAuth.sendPasswordResetEmail(email.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    progressBar.setVisibility(View.GONE);
                                    textshow.setText("recovery email sent to your email successfully\n check inbox");
                                    textshow.setTextColor(getResources().getColor(R.color.teal_200));
                                    textshow.setVisibility(View.VISIBLE);
                                }
                                else{
                                    String error=task.getException().getMessage();
                                    progressBar.setVisibility(View.GONE);
                                    textshow.setText(error);
                                    textshow.setTextColor(getResources().getColor(R.color.purple_200));
                                    TransitionManager.beginDelayedTransition(viewGroup);
                                    textshow.setVisibility(View.VISIBLE);
                                }
                                forgotbtn.setEnabled(true);
                            }
                        });
            }
        });
        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(forgot_pass.this,MainActivity.class);
                startActivity(intent);
            }
        });


    }
    private void checkinputs(){
        if(!TextUtils.isEmpty(email.getText())){
            forgotbtn.setEnabled(true);
        }
        else{
            forgotbtn.setEnabled(false);
        }
    }
}