package com.ryndrappf.uas.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ryndrappf.uas.fragment.CatatanFragment;
import com.ryndrappf.uas.helper.Database;
import com.ryndrappf.uas.R;

import java.text.SimpleDateFormat;
import java.util.Date;
/*
NIM : 10119105
Nama : Ryndra Putra Pratama Firdaus
Kelas : IF-3
 */
public class BuatCatatan extends AppCompatActivity {
    protected Cursor cursor;
    Database database;
    Button btn_simpan;
    EditText judul,deskripsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_catatan);
        database = new Database(this);
        judul = findViewById(R.id.judul);
        deskripsi = findViewById(R.id.deskripsi);
        btn_simpan = findViewById(R.id.btn_simpan);
        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pattern = "dd-MM-yyyy";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                String date = simpleDateFormat.format(new Date());
                SQLiteDatabase db = database.getWritableDatabase();
                db.execSQL("INSERT INTO catatan(judul, deskripsi, tgl) values('" +
                        judul.getText().toString()+ "','" +
                        deskripsi.getText().toString() + "','" + date + "')");
                Toast.makeText(BuatCatatan.this, "Data Tersimpan", Toast.LENGTH_SHORT).show();
                CatatanFragment.catatan.RefreshList();
                finish();
            }
        });
    }
}