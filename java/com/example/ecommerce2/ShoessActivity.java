package com.example.ecommerce2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ShoessActivity extends AppCompatActivity implements RecyclerViewOnItemClickp {
    ProductAdapter productAdapter;
    List<ProductModel> productModelList;
    RecyclerView productRec;
    FirebaseFirestore db;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoess);
        //idrecyclershoes
        productRec = findViewById(R.id.idrecyclershoes);
        productRec.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        productRec.setLayoutManager(layoutManager);

        productModelList = new ArrayList<>();
        db = FirebaseFirestore.getInstance();
        productAdapter = new ProductAdapter(this,productModelList,this);
        productRec.setAdapter(productAdapter);
        db.collection("shoes")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                // Log.d(TAG, document.getId() + " => " + document.getData());
                                ProductModel prodctModel = document.toObject(ProductModel.class);
                                productModelList.add(prodctModel);
                                productAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(ShoessActivity.this, "error", Toast.LENGTH_SHORT).show();
                            // Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });




    }

    @Override
    public void onItemClick(int postion) {
        Intent i = new Intent(ShoessActivity.this,ProductDeatilActivity.class);
        i.putExtra("productname",productModelList.get(postion).getProductname());
        i.putExtra("productprice",productModelList.get(postion).getProductprice());
        i.putExtra("productdescrption",productModelList.get(postion).getProductdescrption());
        i.putExtra("productimage",productModelList.get(postion).getProductImage());
        startActivity(i);
    }
}