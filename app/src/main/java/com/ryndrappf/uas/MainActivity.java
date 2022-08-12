package com.ryndrappf.uas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.ryndrappf.uas.auth.Masuk;
import com.ryndrappf.uas.databinding.ActivityMainBinding;
import com.ryndrappf.uas.fragment.CatatanFragment;
import com.ryndrappf.uas.fragment.ProfileFragment;
/*
NIM : 10119105
Nama : Ryndra Putra Pratama Firdaus
Kelas : IF-3
 */
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new CatatanFragment());

        binding.bottomView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.note:
                    replaceFragment(new CatatanFragment());
                    break;
                case R.id.profile:
                    replaceFragment(new ProfileFragment());
                    break;
                case R.id.logout:
                    mAuth.signOut();
                    startActivity(new Intent(MainActivity.this, Masuk.class));
                    break;
            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentLayout,fragment);
        fragmentTransaction.commit();
    }
}