package com.example.ecommerce2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>{
    //idproductname idimageproduct idtextproductdescrption idtextproductprice
    private Context context;
    private List<ProductModel> productModelList = new ArrayList<>();
    private RecyclerViewOnItemClickp recyclerViewOnItemClickp;

    public ProductAdapter(Context context, List<ProductModel> productModelList, RecyclerViewOnItemClickp recyclerViewOnItemClickp) {
        this.context = context;
        this.productModelList = productModelList;
        this.recyclerViewOnItemClickp = recyclerViewOnItemClickp;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(productModelList.get(position).getProductImage()).into(holder.imageproduct);
        holder.productname.setText(productModelList.get(position).getProductname());
        holder.productdescption.setText(productModelList.get(position).getProductdescrption());
        holder.productprice.setText(productModelList.get(position).getProductprice());


    }

    @Override
    public int getItemCount() {
        return productModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageproduct;
        TextView productname, productdescption,productprice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageproduct = itemView.findViewById(R.id.idimageproduct);
            productname = itemView.findViewById(R.id.idproductname);
            productdescption = itemView.findViewById(R.id.idtextproductdescrption);
            productprice = itemView.findViewById(R.id.idtextproductprice);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recyclerViewOnItemClickp.onItemClick(getAdapterPosition());
                }
            });
        }
    }
}
