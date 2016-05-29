package com.fanspage.persib.fanspagepersib;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class data_jadwal extends AppCompatActivity {
    String[] daftar;
    ListView listView;
    protected Cursor cursor;
    DBHelper dbHelper;
    public static data_jadwal dj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_jadwal);

        Button buttonBerita = (Button) findViewById(R.id.bBerita2);
        Button buttonMember = (Button) findViewById(R.id.button5);
        Button buttonTambahJadwal = (Button) findViewById(R.id.bTambahJadwal);

        buttonBerita.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent ib = new Intent(data_jadwal.this, data_berita.class);
                startActivity(ib);
            }
        });

        buttonMember.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent im = new Intent(data_jadwal.this, data_member.class);
                startActivity(im);
            }
        });

        buttonTambahJadwal.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i = new Intent(data_jadwal.this, insert_jadwal.class);
                startActivity(i);
            }
        });


        dj = this;
        dbHelper = new DBHelper(this);
        RefreshList();
    }

    public void RefreshList(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM jadwal",null);
        daftar = new String [cursor.getCount()];
        cursor.moveToFirst();
        for (int cc=0; cc < cursor.getCount(); cc++){
            cursor.moveToPosition(cc);
            daftar[cc] =cursor.getString(1).toString()+" | Persib vs "+ cursor.getString(3).toString();
        }
        listView = (ListView)findViewById(R.id.lvJadwal);
        listView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftar));
        listView.setSelected(true);
        listView.setOnItemClickListener(new OnItemClickListener() {


            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = daftar[arg2];
                final CharSequence[] dialogitem = {"Lihat Jadwal", "Update Jadwal", "Hapus Jadwal"};
                AlertDialog.Builder builder = new AlertDialog.Builder(data_jadwal.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        switch(item){
                            case 0 :
                                Intent c = new Intent(getApplicationContext(), view_jadwal.class);
                                c.putExtra("lawan_tanding", selection);
                                startActivity(c);
                                break;
                            case 1 :
                                Intent in = new Intent(getApplicationContext(), update_jadwal.class);
                                in.putExtra("lawan_tanding", selection);
                                startActivity(in);
                                break;
                            case 2 :
                                SQLiteDatabase db = dbHelper.getWritableDatabase();
                                db.execSQL("delete from jadwal where lawan_tanding = '"+selection+"'");
                                RefreshList();
                                break;
                        }
                    }
                });
                builder.create().show();
            }});
        ((ArrayAdapter)listView.getAdapter()).notifyDataSetInvalidated();
    }
}
