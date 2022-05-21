package com.example.ecommerce2.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerce2.Home3Activity;
import com.example.ecommerce2.MainActivity;
import com.example.ecommerce2.PopModel;
import com.example.ecommerce2.PopoularAdapter;
import com.example.ecommerce2.R;
import com.example.ecommerce2.ShoessActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
//idrecyclerpophome
    //idgototshirtActivity idgototshoesActivity idgototcapActivity idgototsweaterActivity idgotobagActivity
    //idgototdressActivity
    RoundedImageView tshirtgo,shoessgo;
    PopoularAdapter popoularAdapter;
    List<PopModel> popModelList;
    RecyclerView popularRec;
    FirebaseFirestore db;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home,container,false);
        popularRec = root.findViewById(R.id.idrecyclerpophome);
        tshirtgo = root.findViewById(R.id.idgototshirtActivity);
        shoessgo = root.findViewById(R.id.idgototshoesActivity);
        popularRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        popModelList = new ArrayList<>();
        db = FirebaseFirestore.getInstance();
        popoularAdapter = new PopoularAdapter(getActivity(),popModelList);
        popularRec.setAdapter(popoularAdapter);
        db.collection("PopoularProducts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                               // Log.d(TAG, document.getId() + " => " + document.getData());
                                PopModel popModel = document.toObject(PopModel.class);
                                popModelList.add(popModel);
                                popoularAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                           // Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
        shoessgo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ShoessActivity.class);
                startActivity(intent);
            }
        });

        return root;
    }


}