package com.example.ecommerce2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText email,password;
    Button btn_login;
    FirebaseAuth auth;
    TextView idtextgotoadmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //edtxt_emaillogin  edtxt_passwordlogin  btn_login
        email = findViewById(R.id.edtxt_emaillogin);
        password = findViewById(R.id.edtxt_passwordlogin);
        btn_login = findViewById(R.id.btn_login);
        idtextgotoadmin = findViewById(R.id.idtextgotoadmin);
        auth = FirebaseAuth.getInstance();
        idtextgotoadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,AdminRegisterationActivity.class);
                startActivity(intent);
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Eamils = email.getText().toString();
                String Password = password.getText().toString();
                if (TextUtils.isEmpty(Eamils)){
                    Toast.makeText(LoginActivity.this, "enter your email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(Password)){
                    Toast.makeText(LoginActivity.this, "enter your password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (Password.length()<6){
                    Toast.makeText(LoginActivity.this, "password lenth must be grater than 6 letter", Toast.LENGTH_SHORT).show();
                    return;
                }
                auth.signInWithEmailAndPassword(Eamils,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "sucess", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this,Home3Activity.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(LoginActivity.this, "sucess", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

    }
}