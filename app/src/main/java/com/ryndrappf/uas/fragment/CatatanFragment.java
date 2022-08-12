package com.ryndrappf.uas.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ryndrappf.uas.R;
import com.ryndrappf.uas.crud.BuatCatatan;
import com.ryndrappf.uas.crud.DetailCatatan;
import com.ryndrappf.uas.crud.UbahCatatan;
import com.ryndrappf.uas.helper.Database;
/*
NIM : 10119105
Nama : Ryndra Putra Pratama Firdaus
Kelas : IF-3
 */
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CatatanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CatatanFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CatatanFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CatatanFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CatatanFragment newInstance(String param1, String param2) {
        CatatanFragment fragment = new CatatanFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    String[] daftar;
    String[] tgl;
    Menu menu;
    protected Cursor cursor;
    Database database;
    public static CatatanFragment catatan;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_catatan, container, false);

        FloatingActionButton floatingActionButton = view.findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindah = new Intent(getActivity(), BuatCatatan.class);
                startActivity(pindah);

            }
        });
        catatan = this;
        database = new Database(getContext());
        RefreshList();
        return view;
    }



    public void RefreshList() {
        SQLiteDatabase db = database.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM catatan", null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            daftar[i] = cursor.getString(0).toString();

        }
        ListView listView = (ListView) view.findViewById(R.id.ListView);
        listView.setAdapter(new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, daftar));
        listView.setSelected(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                final String selection = daftar[arg2];
                final CharSequence[] dialogitem = {"Lihat Catatan", "Update Catatan", "Hapus Catatan"};
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int item) {
                        switch(item){
                            case 0 :
                                Intent i = new Intent(getContext(), DetailCatatan.class);
                                i.putExtra("judul", selection);
                                startActivity(i);
                                break;
                            case 1 :
                                Intent in = new Intent(getContext(), UbahCatatan.class);
                                in.putExtra("judul", selection);
                                startActivity(in);
                                break;
                            case 2 :
                                SQLiteDatabase db = database.getWritableDatabase();
                                db.execSQL("DELETE FROM catatan where judul = '" + selection + "'");
                                RefreshList();
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
        ((ArrayAdapter) listView.getAdapter()).notifyDataSetInvalidated();
    }
}