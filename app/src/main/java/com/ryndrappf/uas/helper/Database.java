package com.ryndrappf.uas.helper;
/*
NIM : 10119105
Nama : Ryndra Putra Pratama Firdaus
Kelas : IF-3
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
public class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "catatan1.db";
    private static final int DATABASE_VERSION = 1;

    public Database(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table catatan(judul text null, deskripsi text null, tgl text null);";
        Log.d("Data", "onCreate" + sql);
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db0, int db1, int db2) {

    }
}
