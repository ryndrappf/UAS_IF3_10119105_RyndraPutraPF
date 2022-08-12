package com.ryndrappf.uas.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.ryndrappf.uas.helper.Database;
import com.ryndrappf.uas.R;
/*
NIM : 10119105
Nama : Ryndra Putra Pratama Firdaus
Kelas : IF-3
 */
public class DetailCatatan extends AppCompatActivity {protected Cursor cursor;
    Database database;
    Button btn_simpan;
    TextView judul,deskripsi, tgl_dibuat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_catatan);
        database = new Database(this);
        judul = findViewById(R.id.judul);
        deskripsi = findViewById(R.id.deskripsi);
        tgl_dibuat = findViewById(R.id.tgl_dibuat);
        btn_simpan = findViewById(R.id.btn_simpan);
        SQLiteDatabase db = database.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM catatan WHERE judul = '" +
                getIntent().getStringExtra("judul") + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            judul.setText(cursor.getString(0).toString());
            deskripsi.setText(cursor.getString(1).toString());
            tgl_dibuat.setText(cursor.getString(2).toString());

        }
    }
}