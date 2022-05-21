package com.example.ecommerce2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AddnewProductActivity extends AppCompatActivity {
    ImageView tshirts, shoes,bags,dresss,caps,sweater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnew_product);
        // idimagetshirt  idimageshoes  idimagebag
        // idimagedress  idimagecap  idimagesweater
        tshirts = findViewById(R.id.idimagetshirt);
        shoes = findViewById(R.id.idimageshoes);
        bags = findViewById(R.id.idimagebag);
        dresss = findViewById(R.id.idimagedress);
        caps = findViewById(R.id.idimagecap);
        sweater = findViewById(R.id.idimagesweater);
        tshirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddnewProductActivity.this,UploadProductActivity.class);
                i.putExtra("category","tshirts");
                startActivity(i);
            }
        });
        shoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddnewProductActivity.this,UploadProductActivity.class);
                i.putExtra("category","shoes");
                startActivity(i);
            }
        });
        bags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddnewProductActivity.this,UploadProductActivity.class);
                i.putExtra("category","bags");
                startActivity(i);
            }
        });
        dresss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddnewProductActivity.this,UploadProductActivity.class);
                i.putExtra("category","dresss");
                startActivity(i);
            }
        });
        caps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddnewProductActivity.this,UploadProductActivity.class);
                i.putExtra("category","caps");
                startActivity(i);
            }
        });
        sweater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddnewProductActivity.this,UploadProductActivity.class);
                i.putExtra("category","sweater");
                startActivity(i);
            }
        });

    }
}