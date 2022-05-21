package com.example.ecommerce2;

import android.content.Context;
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

public class PopoularAdapter extends RecyclerView.Adapter<PopoularAdapter.ViewHolder> {

    private Context context;
    private List<PopModel> popModelList = new ArrayList<>();

    public PopoularAdapter(Context context, List<PopModel> popModelList) {
        this.context = context;
        this.popModelList = popModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.slideritem_homerecommended,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(popModelList.get(position).getProductimage()).into(holder.imageproduct);
        holder.productname.setText(popModelList.get(position).getProductname());
        holder.productdescption.setText(popModelList.get(position).getProductdescrption());
        holder.productprice.setText(popModelList.get(position).getProductprice());
    }

    @Override
    public int getItemCount() {
        return popModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageproduct;
        TextView productname, productdescption,productprice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageproduct = itemView.findViewById(R.id.idimageproduct_pop);
            productname = itemView.findViewById(R.id.idtextproductname_pop);
            productdescption = itemView.findViewById(R.id.idtextproductdescption_pop);
            productprice = itemView.findViewById(R.id.idtextproductprice_pop);
        }
    }
    //idimageproduct_pop idtextproductname_pop idtextproductdescption_pop idtextproductprice_pop
}
