package com.fanspage.persib.fanspagepersib;

/**
 * Created by ASUS on 25/05/2016.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "fanspage.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_MEMBER = "member";
    private static final String TABLE_BERITA = "berita";
    private static final String TABLE_JADWAL = "jadwal";

    private static final String CREATE_TABLE_MEMBER = "create table " + TABLE_MEMBER +
            "(member_id varchar(5) primary key, " +
            "member_nama varchar(50) not null, " +
            "member_alamat varchar(100) not null," +
            "member_telp varchar(12) not null);";

    private static final String CREATE_TABLE_BERITA = "create table " + TABLE_BERITA +
            "(berita_id varchar(5) primary key, " +
            "berita_judul varchar(50) not null, " +
            "berita_deskripsi varchar(150) not null);";

    private static final String CREATE_TABLE_JADWAL = "create table " + TABLE_JADWAL +
            "(jadwal_id varchar(5) primary key, " +
            "tgl_tanding varchar(15) not null, " +
            "waktu varchar(15) not null," +
            "lawan_tanding varchar(35) not null," +
            "lokasi_tanding varchar(40) not null);";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_MEMBER);
        db.execSQL(CREATE_TABLE_BERITA);
        db.execSQL(CREATE_TABLE_JADWAL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DBHelper.class.getName(), "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEMBER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BERITA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_JADWAL);
        onCreate(db);

    }

}
