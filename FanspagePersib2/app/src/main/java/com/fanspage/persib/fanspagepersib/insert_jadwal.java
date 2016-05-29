package com.fanspage.persib.fanspagepersib;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class insert_jadwal extends AppCompatActivity {
    protected Cursor cursor;
    DBHelper dbHelper;
    Button buttonSimpan, buttonKembali;
    TextView tvJadwalId, tvTglTanding, tvWaktuTanding, tvLawanTanding, tvLokasiTanding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_jadwal);

        dbHelper = new DBHelper(this);
        tvJadwalId = (TextView) findViewById(R.id.JadwalId);
        tvTglTanding = (TextView) findViewById(R.id.InsTglTdg);
        tvWaktuTanding = (TextView) findViewById(R.id.InsWktTdg);
        tvLawanTanding = (TextView) findViewById(R.id.InsLwnTdg);
        tvLokasiTanding = (TextView) findViewById(R.id.InsLksTdg);
        buttonSimpan = (Button) findViewById(R.id.bSimpan2);
        buttonKembali = (Button) findViewById(R.id.bBatal2);

        buttonSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("insert into jadwal(jadwal_id, tgl_tanding, waktu, lawan_tanding, lokasi_tanding) values('" +
                        tvJadwalId.getText().toString()+"','"+
                        tvTglTanding.getText().toString()+"','"+
                        tvWaktuTanding.getText().toString() +"','" +
                        tvLawanTanding.getText().toString()+"','"+
                        tvLokasiTanding.getText().toString() + "')");
                Toast.makeText(getApplicationContext(), "Data berhasil diinput", Toast.LENGTH_LONG).show();
                data_member.dm.RefreshList();
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
