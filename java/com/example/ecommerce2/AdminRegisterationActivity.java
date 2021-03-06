package com.example.ecommerce2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AdminRegisterationActivity extends AppCompatActivity {
    EditText email, password;
    Button btn_loginadmin;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_registeration);
        //edtxt_emailadmin  edtxt_passwordloginadmin  btn_loginadmin
        auth = FirebaseAuth.getInstance();
        email = findViewById(R.id.edtxt_emailadmin);
        password = findViewById(R.id.edtxt_passwordloginadmin);
        btn_loginadmin = findViewById(R.id.btn_loginadmin);
        btn_loginadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Eamils = email.getText().toString();
                String Password = password.getText().toString();
                if (TextUtils.isEmpty(Eamils)){
                    Toast.makeText(AdminRegisterationActivity.this, "enter your email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(Password)){
                    Toast.makeText(AdminRegisterationActivity.this, "enter your password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (Password.length()<6){
                    Toast.makeText(AdminRegisterationActivity.this, "password lenth must be grater than 6 letter", Toast.LENGTH_SHORT).show();
                    return;
                }
                auth.signInWithEmailAndPassword(Eamils,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(AdminRegisterationActivity.this, "sucess", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(AdminRegisterationActivity.this,AddnewProductActivity.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(AdminRegisterationActivity.this, "sucess", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}