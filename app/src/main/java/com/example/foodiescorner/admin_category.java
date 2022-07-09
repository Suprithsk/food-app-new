package com.example.foodiescorner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class admin_category extends AppCompatActivity {
    ImageView imageView,imageView2,imageView3,imageView4;
    Button savebtn,uploadBtnfood,uploadBtnfood2,uploadBtnfood4;
    EditText category_name,food_name,rupees,food_name2,rupees2,food_name3,rupees3;
    DatabaseReference root=FirebaseDatabase.getInstance().getReference().child("categories");
    StorageReference reference=FirebaseStorage.getInstance().getReference();
    FirebaseFirestore firebaseFirestore;
    Uri imageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);
        imageView = findViewById(R.id.imageView4);
        imageView2=findViewById(R.id.imageView6);
        imageView3=findViewById(R.id.imageView5);
        imageView4=findViewById(R.id.imageView7);
        savebtn = findViewById(R.id.button3);
        uploadBtnfood=findViewById(R.id.button6);
        uploadBtnfood2=findViewById(R.id.button7);
        uploadBtnfood4=findViewById(R.id.button4);
        category_name = findViewById(R.id.editTextTextPersonName);
        food_name=findViewById(R.id.editTextTextPersonName2);
        rupees=findViewById(R.id.editTextTextPersonName6);
        food_name2=findViewById(R.id.editTextTextPersonName9);
        rupees2=findViewById(R.id.editTextTextPersonName10);
        food_name3=findViewById(R.id.editTextTextPersonName11);
        rupees3=findViewById(R.id.editTextTextPersonName12);
        firebaseFirestore=FirebaseFirestore.getInstance();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent=new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent,2);
            }
        });
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imageUri!=null){
                    uploadFirebase1(imageUri);
                }else{
                    Toast.makeText(admin_category.this, "please select image", Toast.LENGTH_SHORT).show();
                }
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent();
                it.setAction(Intent.ACTION_GET_CONTENT);
                it.setType("image/*");
                startActivityForResult(it,3);
            }
        });
        uploadBtnfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imageUri!=null){
                    uploadFirebase2(imageUri);
                }
            }
        });
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent();
                it.setAction(Intent.ACTION_GET_CONTENT);
                it.setType("image/*");
                startActivityForResult(it,4);
            }
        });
        uploadBtnfood2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imageUri!=null){
                    uploadFirebase3(imageUri);
                }
            }
        });
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent();
                it.setAction(Intent.ACTION_GET_CONTENT);
                it.setType("image/*");
                startActivityForResult(it,5);
            }
        });
        uploadBtnfood4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imageUri!=null){
                    uploadFirebase4(imageUri);
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==2 && resultCode==RESULT_OK && data!=null){
            imageUri=data.getData();
            imageView.setImageURI(imageUri);
        }
        if(requestCode==3&&resultCode==RESULT_OK && data!=null){
            imageUri=data.getData();
            imageView2.setImageURI(imageUri);
        }
        if(requestCode==4&&resultCode==RESULT_OK&&data!=null){
            imageUri=data.getData();
            imageView3.setImageURI(imageUri);
        }
        if(requestCode==5&&resultCode==RESULT_OK&&data!=null){
            imageUri=data.getData();
            imageView4.setImageURI(imageUri);
        }
    }

    private void uploadFirebase1(Uri uri) {

        StorageReference fileRef=reference.child(System.currentTimeMillis()+"."+getFileExtension(uri));
        UploadTask uploadTask=fileRef.putFile(uri);
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                uploadTask.getResult().getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        uriModel urimodel=new uriModel(uri.toString(),category_name.getText().toString());
                        firebaseFirestore.collection("categories2")
                                .add(urimodel)
                                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentReference> task) {
                                        if(task.isComplete()){
                                            Toast.makeText(admin_category.this, "uploaded succesfully", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                });

            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(admin_category.this, "uploading failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private  String getFileExtension(Uri muri){
        ContentResolver cr=getContentResolver();
        MimeTypeMap mp=MimeTypeMap.getSingleton();
        return mp.getExtensionFromMimeType(cr.getType(muri));
    }
    private void uploadFirebase2(Uri uri) {

        StorageReference fileRef=reference.child(System.currentTimeMillis()+"."+getFileExtension(uri));
        UploadTask uploadTask=fileRef.putFile(uri);
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                uploadTask.getResult().getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        uriModel2 urimodel=new uriModel2(uri.toString(),food_name.getText().toString(),rupees.getText().toString());
                        firebaseFirestore.collection(category_name.getText().toString())
                                .add(urimodel)
                                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentReference> task) {
                                        if(task.isComplete()){
                                            Toast.makeText(admin_category.this, "uploaded succesfully", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                });

            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(admin_category.this, "uploading failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void uploadFirebase3(Uri uri) {

        StorageReference fileRef=reference.child(System.currentTimeMillis()+"."+getFileExtension(uri));
        UploadTask uploadTask=fileRef.putFile(uri);
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                uploadTask.getResult().getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        uriModel3 urimodel=new uriModel3(uri.toString(),food_name2.getText().toString(),rupees2.getText().toString());
                        firebaseFirestore.collection(category_name.getText().toString())
                                .add(urimodel)
                                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentReference> task) {
                                        if(task.isComplete()){
                                            Toast.makeText(admin_category.this, "uploaded succesfully", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(admin_category.this, "uploading failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void uploadFirebase4(Uri uri) {

        StorageReference fileRef=reference.child(System.currentTimeMillis()+"."+getFileExtension(uri));
        UploadTask uploadTask=fileRef.putFile(uri);
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                uploadTask.getResult().getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        uriModel4 urimodel=new uriModel4(uri.toString(),food_name3.getText().toString(),rupees3.getText().toString());
                        firebaseFirestore.collection(category_name.getText().toString())
                                .add(urimodel)
                                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentReference> task) {
                                        if(task.isComplete()){
                                            Toast.makeText(admin_category.this, "uploaded succesfully", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                });

            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(admin_category.this, "uploading failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

}