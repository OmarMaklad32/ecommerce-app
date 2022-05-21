package com.example.ecommerce2;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecommerce2.Adapters.CartAdapter;
import com.example.ecommerce2.Models.Cartmodel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class CartFragment extends Fragment implements RecyclerViewOnItemClickp{
    RecyclerView recyclerView;
    //RecyclerView.LayoutManager layoutManager;
    Button NextProcessbutton;
    TextView totalamount,totalprice;
    // idrecyclercart idtexttotalprice btn_nextcart
    CartAdapter cartAdapter;
    List<Cartmodel> productcartModelList;
    FirebaseFirestore db;
    private int overtotalprice = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_cart,container,false);
        db = FirebaseFirestore.getInstance();
        recyclerView = root.findViewById(R.id.idrecyclercart);
        NextProcessbutton = root.findViewById(R.id.btn_nextcart);
        totalprice = root.findViewById(R.id.idtexttotalprice);
        recyclerView.setHasFixedSize(true);
        //layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        productcartModelList = new ArrayList<>();
        CartAdapter cartAdapter= new CartAdapter(getActivity(),productcartModelList,this);
        recyclerView.setAdapter(cartAdapter);
        db.collection("Productcart")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                // Log.d(TAG, document.getId() + " => " + document.getData());
                                Cartmodel prodctModel = document.toObject(Cartmodel.class);
                                productcartModelList.add(prodctModel);
                                cartAdapter.notifyDataSetChanged();

                            }
                        } else {
                            Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                            // Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
        NextProcessbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //int ontypproductprice = Integer.valueOf(productcartModelList.get(position).getProductprice()) * Integer.valueOf(productcartModelList.get(position).getProductquantity());
                //overtotalprice = overtotalprice + ontypproductprice;
                Intent i = new Intent(getActivity(),ConfirmfinalorderActivity.class);
                startActivity(i);
            }
        });


        return  root;
    }

    @Override
    public void onItemClick(int postion) {
        String Productprice =productcartModelList.get(postion).getProductprice();
        String Productquantity =productcartModelList.get(postion).getProductquantity();
        String Productname =productcartModelList.get(postion).getProductname();
       // int ontypproductprice =   ((Integer.parseInt(productcartModelList.get(postion).getProductprice()))) * Integer.parseInt(productcartModelList.get(postion).getProductquantity());
       // Toast.makeText(getActivity(), Productprice, Toast.LENGTH_SHORT).show();
       // Toast.makeText(getActivity(), Productquantity, Toast.LENGTH_SHORT).show();
       // Toast.makeText(getActivity(),Productname, Toast.LENGTH_SHORT).show();
        Intent i = new Intent(getActivity(),ConfirmfinalorderActivity.class);
       // i.putExtra("over total",overtotalprice);
        i.putExtra("productprice",Productprice);
        i.putExtra("productquantity",Productquantity);
        i.putExtra("productname",Productname);
        startActivity(i);
    }
}