package com.ryndrappf.uas.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ryndrappf.uas.MainActivity;
import com.ryndrappf.uas.R;
/*
NIM : 10119105
Nama : Ryndra Putra Pratama Firdaus
Kelas : IF-3
 */
public class Masuk extends AppCompatActivity {
    private FirebaseAuth auth;
    final String TAG = "Masuk";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masuk);
        auth = FirebaseAuth.getInstance();

        Button button = findViewById(R.id.btn_masuk);
        final EditText username = findViewById(R.id.email);
        final EditText password = findViewById(R.id.password);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(username.getText().toString(), password.getText().toString());
            }
        });

        TextView registerLink = findViewById(R.id.keRegistrasi);
        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Masuk.this, Registrasi.class));
            }
        });

    }

    private  void login(String email, String password){

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.w(TAG, "Masuk.....");
                    FirebaseUser user = auth.getCurrentUser();

                    Toast.makeText(Masuk.this, "Login Sukses." + user.getEmail(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Masuk.this, MainActivity.class));
                    finish();

                } else {
                    Log.w(TAG, "Gagal....", task.getException());
                    Toast.makeText(Masuk.this, "Login Gagal.", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        FirebaseUser user = mAuth.getCurrentUser();
//        if (user != null){
//            startActivity(new Intent(Masuk.this, MainActivity.class));
//            finish();
//        }
//    }
}