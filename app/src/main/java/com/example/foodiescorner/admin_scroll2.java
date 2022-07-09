package com.example.foodiescorner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.media.Image;
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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class admin_scroll2 extends AppCompatActivity {
    ImageView food_image1,food_image2;
    EditText food_name1,food_name2;
    EditText food_rupees1,food_rupees2;
    Button food_upload1,food_upload2;
    FirebaseFirestore firebaseFirestore;
    StorageReference reference= FirebaseStorage.getInstance().getReference();
    Uri imageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_scroll2);
        food_image1=findViewById(R.id.food_image1);
        food_image2=findViewById(R.id.food_image2);
        food_name1=findViewById(R.id.food_name1);
        food_name2=findViewById(R.id.food_name2);
        food_rupees1=findViewById(R.id.food_rupees1);
        food_rupees2=findViewById(R.id.food_rupees2);
        food_upload1=findViewById(R.id.food_upload1);
        food_upload2=findViewById(R.id.food_upload2);

        firebaseFirestore=FirebaseFirestore.getInstance();

        food_image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent=new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent,1);
            }
        });
        food_upload1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imageUri!=null){
                    uploadFirebase1(imageUri);
                }
            }
        });
        food_image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent=new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent,2);
            }
        });
        food_upload2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imageUri!=null){
                    uploadFirebase2(imageUri);
                }
            }
        });

    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            food_image1.setImageURI(imageUri);
        }
        if (requestCode == 2 && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            food_image2.setImageURI(imageUri);
        }

    }

    private  String getFileExtension(Uri muri){
        ContentResolver cr=getContentResolver();
        MimeTypeMap mp=MimeTypeMap.getSingleton();
        return mp.getExtensionFromMimeType(cr.getType(muri));
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
                        uriModel2 urimodel=new uriModel2(uri.toString(),food_name1.getText().toString(),food_rupees1.getText().toString());
                        firebaseFirestore.collection("scroll_items2")
                                .add(urimodel)
                                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentReference> task) {
                                        if(task.isComplete()){
                                            Toast.makeText(admin_scroll2.this, "uploaded succesfully", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(admin_scroll2.this, "uploading failed", Toast.LENGTH_SHORT).show();
            }
        });
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
                        uriModel3 urimodel=new uriModel3(uri.toString(),food_name2.getText().toString(),food_rupees2.getText().toString());
                        firebaseFirestore.collection("scroll_items2")
                                .add(urimodel)
                                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentReference> task) {
                                        if(task.isComplete()){
                                            Toast.makeText(admin_scroll2.this, "uploaded succesfully", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(admin_scroll2.this, "uploading failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}