package com.example.ecommerce2.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.ecommerce2.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.concurrent.Executor;


public class UserFragment extends Fragment {
    TextView text_gallery;
    //iduserprofile idusername iduserphone
    ImageView userprofile;
    TextView usernamem ,userphone;
    FirebaseAuth fauth;
    FirebaseFirestore fstore;
    String userid;
    //idtextpersonname idtextpersonphone
    EditText edname,edphone;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_user,container,false);
        usernamem = root.findViewById(R.id.idusername);
        userphone = root.findViewById(R.id.iduserphone);
        edname = root.findViewById(R.id.idtextpersonname);
        edphone = root.findViewById(R.id.idtextpersonphone);
       // userphone.setText(value.getString("Emails"));
        //usernamem.setText(value.getString("Fullname"));
        //Glide.with(getActivity()).load(value.getString("Userprofile")).into(userprofile);
        userprofile = root.findViewById(R.id.iduserprofile);
        fauth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        userid =fauth.getCurrentUser().getUid();
        DocumentReference documentReference = fstore.collection("Users1").document(userid);
       documentReference.addSnapshotListener(getActivity(), new EventListener<DocumentSnapshot>() {
           @Override
           public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
               userphone.setText(value.getString("Emails"));
               usernamem.setText(value.getString("Fullname"));
               Glide.with(getActivity()).load(value.getString("Userprofile")).into(userprofile);
               edname.setText(value.getString("Emails"));
               edphone.setText(value.getString("Fullname"));
           }
       });
        return root;
    }


}