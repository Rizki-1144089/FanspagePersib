package com.fanspage.persib.fanspagepersib;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class view_jadwal extends AppCompatActivity {
    protected Cursor cursor;
    DBHelper dbHelper;
    Button buttonKembali;
    TextView tvJadwalId,tvTglTanding, tvWaktuTanding, tvLawanTanding, tvLokasiTanding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_jadwal);

        dbHelper = new DBHelper(this);
        tvJadwalId = (TextView) findViewById(R.id.tvIdJadwal);
        tvTglTanding = (TextView) findViewById(R.id.tvIsiTglTanding);
        tvWaktuTanding = (TextView) findViewById(R.id.tvIsiWaktuTanding);
        tvLawanTanding = (TextView) findViewById(R.id.tvIsiLawanTanding);
        tvLokasiTanding = (TextView) findViewById(R.id.tvIsiLokasiTanding);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM jadwal WHERE jadwal_id = '" +
                getIntent().getStringExtra("jadwal_id") + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0) {
            cursor.moveToPosition(0);
            tvJadwalId.setText(cursor.getString(0).toString());
            tvTglTanding.setText(cursor.getString(1).toString());
            tvWaktuTanding.setText(cursor.getString(2).toString());
            tvLawanTanding.setText(cursor.getString(3).toString());
            tvLokasiTanding.setText(cursor.getString(4).toString());

        }

        buttonKembali = (Button) findViewById(R.id.bKembali);
        buttonKembali.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                finish();
            }
        });
    }
}

