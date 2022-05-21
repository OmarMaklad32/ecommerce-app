package com.example.ecommerce2;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UploadProductActivity extends AppCompatActivity {
    // edtxt_productname  edtxt_productprice edtxt_productdescrption
    EditText productname, productprice,productdescrption;
    ImageView imageproduct;
    private String saveCurrentDate, saveCurrentTime;
    Button btn_upload;
   // Modelimage model;
    String imagerefrence;
    FirebaseFirestore db;
    FirebaseStorage storage;
    // StorageReference productimageRef;
    Uri resulturi;
    private StorageReference str;
    private String downloadImageurl;
    private String categtoryName;
    private String productRandomkey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_product);
        // imageproduct  edtxt_productname  edtxt_productprice  edtxt_productdescrption  btn_upload
        productname = findViewById(R.id.edtxt_productname);
        str = FirebaseStorage.getInstance().getReference().child("product Images");
        db = FirebaseFirestore.getInstance();
        //  productimageRef = FirebaseStorage.getInstance().getReference().child("Product images");
        productprice = findViewById(R.id.edtxt_productprice);
        productdescrption = findViewById(R.id.edtxt_productdescrption);
        storage = FirebaseStorage.getInstance();
        imageproduct = findViewById(R.id.imageproduct);
        btn_upload = findViewById(R.id.btn_upload);
        // imageproduct  btn_upload
        categtoryName = getIntent().getExtras().get("category").toString();
        Toast.makeText(UploadProductActivity.this, categtoryName, Toast.LENGTH_SHORT).show();
        imageproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mGetContent .launch("image/*");
            }
        });
        btn_upload.setOnClickListener(new View.OnClickListener() {
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
            StorageReference reference = storage.getReference().child("images/" + UUID.randomUUID().toString());
            reference.putFile(resulturi).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            downloadImageurl = uri.toString();
                            String Productname =productname.getText().toString();
                            String Productprice =productprice.getText().toString();
                            String Productdescrption =productdescrption.getText().toString();
                            if(resulturi == null){
                                Toast.makeText(UploadProductActivity.this, "product image is empty", Toast.LENGTH_SHORT).show();
                                return;
                            }if(TextUtils.isEmpty(Productname)){
                                Toast.makeText(UploadProductActivity.this, "product name is empty", Toast.LENGTH_SHORT).show();
                                return;
                            }if(TextUtils.isEmpty(Productprice)){
                                Toast.makeText(UploadProductActivity.this, "product price is empty", Toast.LENGTH_SHORT).show();
                                return;
                            }if(TextUtils.isEmpty(Productdescrption)){
                                Toast.makeText(UploadProductActivity.this, "product descrption is empty", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            //uploadimage();
                            Map<String,Object> Products = new HashMap<>();
                            Products.put("Productname",Productname);
                            Products.put("Productprice",Productprice);
                            Products.put("Productdescrption",Productdescrption);
                            Products.put("ProductImage",downloadImageurl);
                            db.collection(categtoryName)
                                    .add(Products)
                                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                        @Override
                                        public void onSuccess(DocumentReference documentReference) {
                                            Toast.makeText(UploadProductActivity.this, "sucess", Toast.LENGTH_SHORT).show();
                                            productname.setText("");
                                            productdescrption.setText("");
                                            productprice.setText("");

                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(UploadProductActivity.this, "failed ", Toast.LENGTH_SHORT).show();
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
                        imageproduct.setImageURI(result);
                        resulturi = result;
                    }
                }
            });
}