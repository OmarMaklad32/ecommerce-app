package com.example.ecommerce2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AdminActivity extends AppCompatActivity {
    ImageView seeusers,uploadproduct,orders;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        seeusers = findViewById(R.id.idseeusers);
        orders = findViewById(R.id.idorders);
        uploadproduct = findViewById(R.id.iduploadproduct);
        uploadproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this,AddnewProductActivity.class);
                startActivity(intent);
            }
        });
        orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this,OrdersAdminActivity.class);
                startActivity(intent);
            }
        });
    }
}