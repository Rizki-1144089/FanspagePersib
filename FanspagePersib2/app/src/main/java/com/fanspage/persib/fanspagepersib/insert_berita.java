package com.fanspage.persib.fanspagepersib;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class insert_berita extends AppCompatActivity {


    protected Cursor cursor;
    DBHelper dbHelper;
    Button buttonSimpan, buttonKembali;
    EditText etBeritaId, etJudul, etDeskripsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_berita);

        dbHelper = new DBHelper(this);
        etBeritaId = (EditText) findViewById(R.id.etBeritaId);
        etJudul = (EditText) findViewById(R.id.etJudul);
        etDeskripsi = (EditText) findViewById(R.id.etDeskripsi);
        buttonSimpan = (Button) findViewById(R.id.button16);
        buttonKembali = (Button) findViewById(R.id.button30);


        buttonSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("insert into berita(berita_id, berita_judul, berita_dekripsi) values('" +
                        etBeritaId.getText().toString()+"','"+
                        etJudul.getText().toString() +"','" +
                        etDeskripsi.getText().toString() + "')");
                Toast.makeText(getApplicationContext(), "Data berhasil diinput", Toast.LENGTH_LONG).show();
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
