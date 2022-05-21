package com.example.ecommerce2;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SignActivity extends AppCompatActivity {
    EditText fullnamesign,emailsign,passwordsign,phonenumber;
    Button btn_sign;
    FirebaseAuth auth;
    ImageView userprofile;
    Uri resulturi;
    String downloadImageurls;
    String userId;
    FirebaseFirestore db;
    FirebaseStorage storage;

    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        storage = FirebaseStorage.getInstance();
        //str = FirebaseStorage.getInstance().getReference().child("user Images");
        db = FirebaseFirestore.getInstance();
        // edtxt_fullname  edtxt_email edtxt_passwordsign  btn_sign
        fullnamesign = findViewById(R.id.edtxt_fullname);
        emailsign = findViewById(R.id.edtxt_email);
        userprofile = findViewById(R.id.roundedImageViewprofile);
       // userprofile
        passwordsign = findViewById(R.id.edtxt_passwordsign);
        btn_sign = findViewById(R.id.btn_signsign);
        phonenumber = findViewById(R.id.edtxt_phonenumber);
        auth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        userprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGetContent .launch("image/*");
            }
        });
        btn_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadimage();
            }
        });

    }
    private void uploadimage() {
        // Calendar calendar = Calendar.getInstance();

        //SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyy");
        //saveCurrentDate = currentDate.format(calendar.getTime());

        // SimpleDateFormat currTime = new SimpleDateFormat("HH:mm:ss a");
        //saveCurrentTime = currTime.format(calendar.getTime());
        // productRandomkey = saveCurrentDate + saveCurrentTime;
        if(resulturi != null){
            StorageReference reference = storage.getReference().child("userimageprofile/" + UUID.randomUUID().toString());
            reference.putFile(resulturi).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            downloadImageurls = uri.toString();
                            String Fullname = fullnamesign.getText().toString();
                            String Eamils = emailsign.getText().toString();
                            String Password = passwordsign.getText().toString();
                            String Phonenumber = phonenumber.getText().toString();
                            if (TextUtils.isEmpty(Fullname)){
                                Toast.makeText(SignActivity.this, "enter your full name", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if (TextUtils.isEmpty(Eamils)){
                                Toast.makeText(SignActivity.this, "enter your email", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if (TextUtils.isEmpty(Password)){
                                Toast.makeText(SignActivity.this, "enter your password", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if (TextUtils.isEmpty(Phonenumber)){
                                Toast.makeText(SignActivity.this, "enter your password", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if (Password.length()<6){
                                Toast.makeText(SignActivity.this, "password lenth must be grater than 6 letter", Toast.LENGTH_SHORT).show();
                                return;
                            }


                            auth.createUserWithEmailAndPassword(Eamils,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        //uploadimage();
                                        userId =auth.getCurrentUser().getUid();
                                        DocumentReference documentReference = db.collection("Users1").document(userId);
                                        Map<String,Object> Products = new HashMap<>();
                                        Products.put("Fullname",Fullname);
                                        Products.put("Emails",Eamils);
                                        Products.put("Password",Password);
                                        Products.put("Userprofile",downloadImageurls);
                                        documentReference.set(Products).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                Toast.makeText(SignActivity.this, "sucess", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(SignActivity.this,Home3Activity.class);
                                                startActivity(intent);
                                            }
                                        });


                                    }else
                                    {
                                        Toast.makeText(SignActivity.this, "failed", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    });
                }
            });
        }


    }
    ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri result) {
                    if(result != null) {
                        userprofile.setImageURI(result);
                        resulturi = result;
                    }
                }
            });
}