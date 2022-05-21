package com.example.ecommerce2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ProductDeatilActivity extends AppCompatActivity  {
    ImageView imageproduct,imageplus,imageminus;
    TextView txtproductname, txtproductdescrption,txtproductprice,txtamount,total;
    //idimageplus idimageminus idtextamount
    int amount = 1;
    //idtxttotal
    //idbtnaddtocart
    Button btnaddtocart;
    TextView txttotal;
    String Productprice;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_deatil);
        db = FirebaseFirestore.getInstance();
        imageplus = findViewById(R.id.idimageplus);
        imageminus = findViewById(R.id.idimageminus);
        txtamount = findViewById(R.id.idtextamount);
        txttotal = findViewById(R.id.idtxttotal);
        btnaddtocart = findViewById(R.id.idbtnaddtocart);
        imageproduct = findViewById(R.id.idimageproductdeatil);
        txtproductname = findViewById(R.id.idtextproductnamedeatal);
        txtproductdescrption = findViewById(R.id.idtextproductndescrptiondeatal);
        txtproductprice = findViewById(R.id.idtextproductnpricedeatal);
        //idimageproductdeatil  idtextproductnamedeatal idtextproductndescrptiondeatal idtextproductnpricedeatal
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            String Productname = extras.getString("productname");
            String Productdescrption = extras.getString("productdescrption");
            Productprice = extras.getString("productprice");
            String Productimage = extras.getString("productimage");
            txtproductname.setText(Productname);
            txtproductdescrption.setText(Productdescrption);
            txtproductprice.setText(Productprice);
           // int totals = Integer.valueOf(Productprice);
           // total.setText(Productprice);
            Glide.with(this).load(Productimage).into(imageproduct);


        }
        imageplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(amount < 10){
                    amount++;
                    txtamount.setText(String.valueOf(amount));
                }
            }
        });
        imageminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(amount > 0 ){
                    amount--;
                    txtamount.setText(String.valueOf(amount));
                }
            }
        });
        btnaddtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Productname = txtproductname.getText().toString();
                String Productprice = txtproductprice.getText().toString();
                String Productquantity =txtamount.getText().toString();

                //uploadimage();
                Map<String,Object> Products = new HashMap<>();
                Products.put("Productname",Productname);
                Products.put("Productprice",Productprice);
                Products.put("Productquantity",Productquantity);
                db.collection("Productcart")
                        .add(Products)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(ProductDeatilActivity.this, "added to cart", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ProductDeatilActivity.this, "failed ", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}