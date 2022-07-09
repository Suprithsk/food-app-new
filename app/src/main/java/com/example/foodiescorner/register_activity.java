package com.example.foodiescorner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class register_activity extends AppCompatActivity {

    EditText username,password,rpassword,mobilenumber;
    Button submit;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username=findViewById(R.id.usernammm);
        password=findViewById(R.id.Name3);
        rpassword=findViewById(R.id.editTextTextPersonName4);
        mobilenumber=findViewById(R.id.editTextTextPersonName5);
        submit=findViewById(R.id.resetbutton);
        firebaseAuth =FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();

        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        rpassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mobilenumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        //submit button config
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //to send data to firebase
                checkemailpass();

            }
        });


    }
    private void checkInputs(){
        if (!TextUtils.isEmpty(username.getText())){
            if(!TextUtils.isEmpty(password.getText()) && password.length()>=6){
                if(!TextUtils.isEmpty(rpassword.getText()) && rpassword.length()>=6){
                    if(!TextUtils.isEmpty(mobilenumber.getText()) ){
                        submit.setEnabled(true);
                    }
                    else{
                        submit.setEnabled(false);
                    }
                }
                else{
                    submit.setEnabled(false);
                }
            }
            else{
                submit.setEnabled(false);
            }
        }
        else{
            submit.setEnabled(false);
        }
    }
    private void checkemailpass(){
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        if (username.getText().toString().matches(emailPattern)){
            if(password.getText().toString().equals(rpassword.getText().toString())){
                firebaseAuth.createUserWithEmailAndPassword(username.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Map<Object,String> userdata=new HashMap<>();
                            userdata.put("username",username.getText().toString());
                            userdata.put("phoneno",mobilenumber.getText().toString());
                            firebaseFirestore.collection("USERS").document(user.getUid()).set(userdata)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Intent mainintent=new Intent(register_activity.this, home_page.class);
                                            startActivity(mainintent);
                                        }
                                    });
                        }
                        else {
                            String error=task.getException().getMessage();
                            Toast.makeText(register_activity.this, error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
            else{
                Toast.makeText(register_activity.this, "passwords don't match try again", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(register_activity.this, "enter a valid email", Toast.LENGTH_SHORT).show();

        }
    }


}