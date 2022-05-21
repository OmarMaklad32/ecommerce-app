package com.example.ecommerce2.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerce2.CartFragment;
import com.example.ecommerce2.Models.Cartmodel;
import com.example.ecommerce2.ProductAdapter;
import com.example.ecommerce2.ProductDeatilActivity;
import com.example.ecommerce2.ProductModel;
import com.example.ecommerce2.R;
import com.example.ecommerce2.RecyclerViewOnItemClickp;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private Context context;
    private List<Cartmodel> productcartModelList = new ArrayList<>();
    private RecyclerViewOnItemClickp recyclerViewOnItemClickp;

    public CartAdapter(Context context, List<Cartmodel> productcartModelList, RecyclerViewOnItemClickp recyclerViewOnItemClickp) {
        this.context = context;
        this.productcartModelList = productcartModelList;
        this.recyclerViewOnItemClickp = recyclerViewOnItemClickp;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.carts_item_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.product_name.setText(productcartModelList.get(position).getProductname());
        holder.product_price.setText(productcartModelList.get(position).getProductprice());
        holder.product_quantity.setText(productcartModelList.get(position).getProductquantity());

    }

    @Override
    public int getItemCount() {
        return productcartModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView product_name,product_quantity,product_price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            product_name =itemView.findViewById(R.id.cart_product_name);
            product_quantity =itemView.findViewById(R.id.cart_product_quantity);
            product_price =itemView.findViewById(R.id.cart_product_price);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recyclerViewOnItemClickp.onItemClick(getAdapterPosition());
                }
            });
        }
    }
    //cart_product_name cart_product_quantity cart_product_price

}
