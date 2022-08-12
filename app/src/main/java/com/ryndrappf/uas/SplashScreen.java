package com.ryndrappf.uas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
/*
NIM : 10119105
Nama : Ryndra Putra Pratama Firdaus
Kelas : IF-3
 */
public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(5000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    startActivity(new Intent(SplashScreen.this, ViewPagerActivity.class));
                    finish();
                }
            }
        };
        thread.start();
    }
}