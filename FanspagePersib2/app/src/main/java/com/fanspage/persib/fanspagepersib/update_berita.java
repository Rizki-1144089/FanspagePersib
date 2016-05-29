package com.fanspage.persib.fanspagepersib;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class update_berita extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_berita);
        Cursor cursor;
        final DBHelper dbHelper;
        Button buttonUpdate, buttonKembali;
        final EditText etBeritaId, etJudul, etDeskripsi;

        dbHelper = new DBHelper(this);
        etBeritaId = (EditText) findViewById(R.id.etBeritaId);
        etJudul = (EditText) findViewById(R.id.etJudul);
        etDeskripsi = (EditText) findViewById(R.id.etDeskripsi);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM berita WHERE berita_judul = '" +
                getIntent().getStringExtra("berita_judul") + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0) {
            cursor.moveToPosition(0);
            etBeritaId.setText(cursor.getString(0).toString());
            etJudul.setText(cursor.getString(1).toString());
            etDeskripsi.setText(cursor.getString(2).toString());
        }
        buttonUpdate = (Button) findViewById(R.id.bsimp);
        buttonKembali = (Button) findViewById(R.id.bBatal);

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("update berita set berita_judul='"+
                        etJudul.getText().toString()+"', berita_deskripsi='"+
                        etDeskripsi.getText().toString() + "' where berita_id='" +
                        etBeritaId.getText().toString()+"'");
                Toast.makeText(getApplicationContext(), "Data berhasil diupdate", Toast.LENGTH_LONG).show();
                data_berita.brt.RefreshList();
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
