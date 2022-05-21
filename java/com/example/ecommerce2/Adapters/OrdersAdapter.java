package com.example.ecommerce2.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerce2.Models.Cartmodel;
import com.example.ecommerce2.Models.OrdersModel;
import com.example.ecommerce2.R;

import java.util.ArrayList;
import java.util.List;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.ViewHolder>{
    private Context context;
    private List<OrdersModel> ordersModelsModelList = new ArrayList<>();

    public OrdersAdapter(Context context, List<OrdersModel> ordersModelsModelList) {
        this.context = context;
        this.ordersModelsModelList = ordersModelsModelList;
    }

    @NonNull
    @Override
    public OrdersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.orders_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersAdapter.ViewHolder holder, int position) {
        holder.ordername.setText(ordersModelsModelList.get(position).getProductname());
        holder.orderprice.setText("price =" + ordersModelsModelList.get(position).getProductprice());
        holder.quantityorder.setText("qunatiyy =" +ordersModelsModelList.get(position).getProductquantity());
        holder.nameofperson.setText(ordersModelsModelList.get(position).getName());
        holder.cityname.setText(ordersModelsModelList.get(position).getCityname());
        holder.homeaddres.setText(ordersModelsModelList.get(position).getHomeaddress());
        holder.phonenumber.setText(ordersModelsModelList.get(position).getPhonenumber());

    }

    @Override
    public int getItemCount() {
        return ordersModelsModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // idtextordername idtxtpriceorder idtxtquantity  idnameofperson idcitynameofperson idhomeaddressofperson idphonenumberperson
        TextView ordername,orderprice,quantityorder,nameofperson,cityname,homeaddres,phonenumber;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ordername =itemView.findViewById(R.id.idtextordername);
            orderprice =itemView.findViewById(R.id.idtxtpriceorder);
            quantityorder =itemView.findViewById(R.id.idtxtquantity);
            nameofperson =itemView.findViewById(R.id.idnameofperson);
            cityname =itemView.findViewById(R.id.idcitynameofperson);
            homeaddres =itemView.findViewById(R.id.idhomeaddressofperson);
            phonenumber =itemView.findViewById(R.id.idphonenumberperson);
        }
    }
}
