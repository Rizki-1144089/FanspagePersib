package com.fanspage.persib.fanspagepersib;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class insert_member extends AppCompatActivity {


    protected Cursor cursor;
    DBHelper dbHelper;
    Button buttonSimpan, buttonKembali;
    EditText etMemberId, etNama, etAlamat, etTelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_member);

        dbHelper = new DBHelper(this);
        etMemberId = (EditText) findViewById(R.id.etMemberId);
        etNama = (EditText) findViewById(R.id.etNama);
        etTelp = (EditText) findViewById(R.id.etTelpon);
        etAlamat = (EditText) findViewById(R.id.etAlamat);
        buttonSimpan = (Button) findViewById(R.id.button12);
        buttonKembali = (Button) findViewById(R.id.bBatal);

        buttonSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("insert into member(member_id, member_nama, member_alamat, member_telp) values('" +
                        etMemberId.getText().toString()+"','"+
                        etNama.getText().toString() +"','" +
                        etAlamat.getText().toString()+"','"+
                        etTelp.getText().toString() + "')");
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
