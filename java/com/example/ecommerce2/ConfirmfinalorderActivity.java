package com.example.ecommerce2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ConfirmfinalorderActivity extends AppCompatActivity {
    EditText name,phonenumber,homeaddress,cityname;
    Button btnconfirm;
    String Productname;
    String Productquantity;
    String Productprice;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmfinalorder);
        db = FirebaseFirestore.getInstance();
        //editText_name editText_phonenumber editText_homeaddress editText_cityname btn_confirm
        name = findViewById(R.id.editText_name);
        phonenumber = findViewById(R.id.editText_phonenumber);
        homeaddress = findViewById(R.id.editText_homeaddress);
        cityname = findViewById(R.id.editText_cityname);
        btnconfirm = findViewById(R.id.btn_confirm);
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            Productname = extras.getString("productname");
            Productquantity = extras.getString("productquantity");
            Productprice = extras.getString("productprice");
            Toast.makeText(ConfirmfinalorderActivity.this,Productname , Toast.LENGTH_SHORT).show();
            Toast.makeText(ConfirmfinalorderActivity.this,Productquantity , Toast.LENGTH_SHORT).show();
            Toast.makeText(ConfirmfinalorderActivity.this,Productprice , Toast.LENGTH_SHORT).show();
        }
        btnconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = name.getText().toString();
                String PhoneNumber = phonenumber.getText().toString();
                String Homeaddress = homeaddress.getText().toString();
                String Cityname = cityname.getText().toString();
                //uploadimage();
                Map<String,Object> Products = new HashMap<>();
                Products.put("Productname",Productname);
                Products.put("Productprice",Productprice);
                Products.put("Productquantity",Productquantity);
                Products.put("Name",Name);
                Products.put("phonenumber",PhoneNumber);
                Products.put("homeaddress",Homeaddress);
                Products.put("cityname",Cityname);
                db.collection("Productorder")
                        .add(Products)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(ConfirmfinalorderActivity.this, "sucess ", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ConfirmfinalorderActivity.this, "failed ", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}