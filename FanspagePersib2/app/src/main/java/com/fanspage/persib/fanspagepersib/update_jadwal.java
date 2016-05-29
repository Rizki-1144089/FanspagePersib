package com.fanspage.persib.fanspagepersib;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Toast;

public class update_jadwal extends AppCompatActivity {
    protected Cursor cursor;
    DBHelper dbHelper;
    Button buttonUpdate, buttonKembali;
    EditText etJadwalId5, etTgl5, etWaktu5, etLawan5, etLokasi5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_jadwal);

        dbHelper = new DBHelper(this);
        etJadwalId5 = (EditText) findViewById(R.id.etJadwal5);
        etTgl5 = (EditText) findViewById(R.id.etTgl5);
        etWaktu5 = (EditText) findViewById(R.id.etWaktu5);
        etLawan5 = (EditText) findViewById(R.id.etLawan5);
        etLokasi5 = (EditText) findViewById(R.id.etLokasi5);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM jadwal WHERE tgl_tanding = '" +
                getIntent().getStringExtra("jadwal_id") + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0) {
            cursor.moveToPosition(0);
            etJadwalId5.setText(cursor.getString(0).toString());
            etTgl5.setText(cursor.getString(1).toString());
            etWaktu5.setText(cursor.getString(2).toString());
            etLawan5.setText(cursor.getString(3).toString());
            etLokasi5.setText(cursor.getString(4).toString());
        }
        buttonUpdate = (Button) findViewById(R.id.buttonUpdateJadwal);
        buttonKembali = (Button) findViewById(R.id.kembaliJadwalUpdate);

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("update jadwal set tgl_tanding='"+
                        etTgl5.getText().toString() +"', waktu='" +
                        etWaktu5.getText().toString() +"', lawan_tanding='" +
                        etLawan5.getText().toString() +"', lokasi_tanding='" +
                        etLokasi5.getText().toString() + "' where jadwal_id='" +
                        etJadwalId5.getText().toString()+"'");
                Toast.makeText(getApplicationContext(), "Data berhasil diupdate", Toast.LENGTH_LONG).show();
                data_jadwal.dj.RefreshList();
                finish();
            }
        });

        buttonKembali.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                finish();
            }
        });

    }
}
