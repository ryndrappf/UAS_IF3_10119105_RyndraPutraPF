package com.ryndrappf.uas.auth;

/*
NIM : 10119105
Nama : Ryndra Putra Pratama Firdaus
Kelas : IF-3
 */
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ryndrappf.uas.R;

public class Registrasi extends AppCompatActivity {
    private FirebaseAuth auth;
    final String TAG = "Registrasi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);

        setContentView(R.layout.activity_registrasi);

        auth = FirebaseAuth.getInstance();

        Button button = findViewById(R.id.btn_registrasi);
        final EditText username = findViewById(R.id.email);
        final EditText password = findViewById(R.id.password);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp(username.getText().toString(), password.getText().toString());
            }
        });
    }

    private void signUp(String email, String password)
    {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.d(TAG, "Mendaftar Sukses....");
                    FirebaseUser user = auth.getCurrentUser();

                    Log.d(TAG, "Mendaftar Gagal...", task.getException());
                    Toast.makeText(Registrasi.this, "Pendaftaran Sukses." + user.getEmail(), Toast.LENGTH_SHORT).show();
                    finish();

                } else {
                    Log.d(TAG, "createUserWithEmail:failure", task.getException());
                    Toast.makeText(Registrasi.this, "Pendaftaran Gagal.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}