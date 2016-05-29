package com.fanspage.persib.fanspagepersib;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class view_berita extends AppCompatActivity {
   protected Cursor cursor;
    DBHelper dbHelper;
    Button buttonKembali;
    TextView tvBeritaId, tvJudul, tvDeskripsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_berita);


        dbHelper = new DBHelper(this);
        tvBeritaId = (TextView) findViewById(R.id.tvBeritaId);
        tvJudul = (TextView) findViewById(R.id.tvJudul);
        tvDeskripsi = (TextView) findViewById(R.id.tvDeskripsi);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM berita WHERE berita_judul = '" +
                getIntent().getStringExtra("berita_judul") + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0) {
            cursor.moveToPosition(0);
            tvBeritaId.setText(cursor.getString(0).toString());
            tvJudul.setText(cursor.getString(1).toString());
            tvDeskripsi.setText(cursor.getString(2).toString());
        }

        buttonKembali = (Button) findViewById(R.id.button4);
        buttonKembali.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                finish();
            }
        });
    }
}
