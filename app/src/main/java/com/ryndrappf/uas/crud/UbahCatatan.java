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
/*
NIM : 10119105
Nama : Ryndra Putra Pratama Firdaus
Kelas : IF-3
 */
public class UbahCatatan extends AppCompatActivity {

    protected Cursor cursor;
    Database database;
    Button btn_simpan;
    EditText judul,deskripsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_catatan);
        database = new Database(this);
        judul = findViewById(R.id.judul);
        deskripsi = findViewById(R.id.deskripsi);
        btn_simpan = findViewById(R.id.btn_simpan);
        SQLiteDatabase db = database.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM catatan WHERE judul = '" +
                getIntent().getStringExtra("judul") + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0){
            cursor.moveToPosition(0);
            judul.setText(cursor.getString(0).toString());
            deskripsi.setText(cursor.getString(1).toString());
        }
        btn_simpan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                SQLiteDatabase db = database.getWritableDatabase();
                db.execSQL("UPDATE catatan SET  judul= '"+ judul.getText().toString() + "', deskripsi= '" +
                        deskripsi.getText().toString() + "' where judul = '" +
                        getIntent().getStringExtra("judul")+"'");
                Toast.makeText(UbahCatatan.this, "Data Berhasil di Update", Toast.LENGTH_SHORT).show();
                CatatanFragment.catatan.RefreshList();
                finish();
            }
        });
    }
}