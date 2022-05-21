package com.example.ecommerce2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.ecommerce2.Adapters.CartAdapter;
import com.example.ecommerce2.Adapters.OrdersAdapter;
import com.example.ecommerce2.Models.Cartmodel;
import com.example.ecommerce2.Models.OrdersModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class OrdersAdminActivity extends AppCompatActivity {
    //idrecyclerorder
    FirebaseFirestore db;
    RecyclerView recyclerView;
    List<OrdersModel> ordersModelList;
    OrdersAdapter ordersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_admin);
        db = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.idrecyclerorder);
        recyclerView.setHasFixedSize(true);
        //layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(OrdersAdminActivity.this,RecyclerView.VERTICAL,false));
        ordersModelList = new ArrayList<>();
        ordersAdapter= new OrdersAdapter(this,ordersModelList);
        recyclerView.setAdapter(ordersAdapter);
        db.collection("Productorder")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                // Log.d(TAG, document.getId() + " => " + document.getData());
                                OrdersModel ordersModel = document.toObject(OrdersModel.class);
                                ordersModelList.add(ordersModel);
                                ordersAdapter.notifyDataSetChanged();

                            }
                        } else {
                            Toast.makeText(OrdersAdminActivity.this, "error", Toast.LENGTH_SHORT).show();
                            // Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }
}