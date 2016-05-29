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



public class data_berita extends AppCompatActivity {
    String[] daftar;
    ListView listView;
    protected Cursor cursor;
    DBHelper dbHelper;
    public static data_berita brt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_berita);

        Button buttonMember = (Button) findViewById(R.id.bMember2);
        Button buttonJadwal = (Button) findViewById(R.id.bJadwal4);
        Button buttonTambahBerita = (Button) findViewById(R.id.bTambahBerita);

        buttonMember.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent ib = new Intent(data_berita.this, data_member.class);
                startActivity(ib);
            }
        });

        buttonJadwal.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent ij = new Intent(data_berita.this, data_jadwal.class);
                startActivity(ij);
            }
        });

        buttonTambahBerita.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i = new Intent(data_berita.this, insert_berita.class);
                startActivity(i);
            }
        });


        brt = this;
        dbHelper = new DBHelper(this);
        RefreshList();
    }

    public void RefreshList(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM berita",null);
        daftar = new String [cursor.getCount()];
        cursor.moveToFirst();
        for (int cc=0; cc < cursor.getCount(); cc++){
            cursor.moveToPosition(cc);
            daftar[cc] = cursor.getString(1).toString();
        }
        listView = (ListView)findViewById(R.id.lvBerita);
        listView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftar));
        listView.setSelected(true);
        listView.setOnItemClickListener(new OnItemClickListener() {


            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = daftar[arg2];
                final CharSequence[] dialogitem = {"Lihat Berita", "Update Berita", "Hapus Berita"};
                AlertDialog.Builder builder = new AlertDialog.Builder(data_berita.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        switch(item){
                            case 0 :
                                Intent i = new Intent(getApplicationContext(), view_berita.class);
                                i.putExtra("berita_judul", selection);
                                startActivity(i);
                                break;
                            case 1 :
                                Intent in = new Intent(getApplicationContext(), update_berita.class);
                                in.putExtra("berita_judul", selection);
                                startActivity(in);
                                break;
                            case 2 :
                                SQLiteDatabase db = dbHelper.getWritableDatabase();
                                db.execSQL("delete from berita where berita_judul = '"+selection+"'");
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
