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
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText username,password;
    Button submit,newuserbutton,admin;
    ImageButton closebtn;
    TextView forgotpass;
    FirebaseAuth firebaseAuth;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username =findViewById(R.id.usernammm);
        password=findViewById(R.id.Name3);
        submit=findViewById(R.id.resetbutton);
        newuserbutton=findViewById(R.id.button2);
        closebtn=findViewById(R.id.imageButton);
        forgotpass =findViewById(R.id.textView2);
        admin=findViewById(R.id.button5);
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(MainActivity.this,admin_login.class);
                startActivity(it);
            }
        });

        firebaseAuth=FirebaseAuth.getInstance();


        username.addTextChangedListener(new TextWatcher() {
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

        password.addTextChangedListener(new TextWatcher() {
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

        newuserbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this,register_activity.class);
                startActivity(intent);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkemailpass();
            }
        });

        closebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homepage=new Intent(MainActivity.this, home_page.class);
                startActivity(homepage);
            }
        });
        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent forgot=new Intent(MainActivity.this,forgot_pass.class);
                startActivity(forgot);
            }
        });

}
    private void checkinputs(){
        if(!TextUtils.isEmpty(username.getText())){
            if(!TextUtils.isEmpty(password.getText())){
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
    private void checkemailpass(){
        if(username.getText().toString().matches(emailPattern)){
            if(password.length()>=6){
                firebaseAuth.signInWithEmailAndPassword(username.getText().toString(),password.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Intent homepage=new Intent(MainActivity.this,home_page.class);
                                    startActivity(homepage);
                                }
                                else{
                                    Toast.makeText(MainActivity.this, "incorrect email or password", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
            else{
                Toast.makeText(MainActivity.this, "incorrect email or password", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(MainActivity.this, "incorrect email or password", Toast.LENGTH_SHORT).show();
        }
    }

}