package com.fanspage.persib.fanspagepersib;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class update_member extends AppCompatActivity {
    protected Cursor cursor;
    DBHelper dbHelper;
    Button buttonUpdate, buttonKembali;
    EditText etMemberId, etNama, etAlamat, etTelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_member);

        dbHelper = new DBHelper(this);
        etMemberId = (EditText) findViewById(R.id.editText14);
        etNama = (EditText) findViewById(R.id.editText15);
        etAlamat = (EditText) findViewById(R.id.editText16);
        etTelp = (EditText) findViewById(R.id.editTextTelp);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM member WHERE member_nama = '" +
                getIntent().getStringExtra("member_nama") + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0) {
            cursor.moveToPosition(0);
            etMemberId.setText(cursor.getString(0).toString());
            etNama.setText(cursor.getString(1).toString());
            etAlamat.setText(cursor.getString(3).toString());
            etTelp.setText(cursor.getString(2).toString());
        }
        buttonUpdate = (Button) findViewById(R.id.button15);
        buttonKembali = (Button) findViewById(R.id.button16);

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("update member set member_nama='"+
                        etNama.getText().toString() +"', member_alamat='" +
                        etAlamat.getText().toString() +"', member_telp='" +
                        etTelp.getText().toString() + "' where member_id='" +
                        etMemberId.getText().toString()+"'");
                Toast.makeText(getApplicationContext(), "Data berhasil diupdate", Toast.LENGTH_LONG).show();
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
