package com.fanspage.persib.fanspagepersib;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class view_member extends AppCompatActivity {
    protected Cursor cursor;
    DBHelper dbHelper;
    Button buttonKembali;
    TextView tvMemberId, tvNama, tvAlamat, tvTelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_member);

        dbHelper = new DBHelper(this);
        tvMemberId = (TextView) findViewById(R.id.tvIsiIdMember);
        tvNama = (TextView) findViewById(R.id.tvIsiNama);
        tvAlamat = (TextView) findViewById(R.id.tvIsiAlamat);
        tvTelp = (TextView) findViewById(R.id.tvIsiTelpon);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM member WHERE member_nama = '" +
                getIntent().getStringExtra("member_nama") + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0) {
            cursor.moveToPosition(0);
            tvMemberId.setText(cursor.getString(0).toString());
            tvNama.setText(cursor.getString(1).toString());
            tvAlamat.setText(cursor.getString(3).toString());
            tvTelp.setText(cursor.getString(2).toString());
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
