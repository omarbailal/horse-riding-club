package com.example.clientemploiequitationapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDB extends SQLiteOpenHelper {


    private static final int DB_VERSION = 1;
    private static final String TABLE_REQ = "remarque";
    private static final String COL_SEANCEID = "seanceId";
    private static final String COL_REMARQUE = "remarque";


    public MyDB(@Nullable Context context ) { super(context, "remarque.db", null, DB_VERSION);}
    public void insertRemarque(int seanceId, String remarque){
        ContentValues row = new ContentValues();
        row.put(COL_SEANCEID, seanceId);
        row.put(COL_REMARQUE, remarque);
        getReadableDatabase().insertWithOnConflict(TABLE_REQ, null, row, SQLiteDatabase.CONFLICT_REPLACE);
    }
    public String readRemarque(int seanceId){
        Cursor c = getReadableDatabase().rawQuery("SELECT " + COL_REMARQUE + " FROM " + TABLE_REQ + " WHERE " + COL_SEANCEID + " = " + seanceId, null);
        if(c != null && c.moveToFirst()){
            return c.getString(c.getColumnIndex(COL_REMARQUE));
        }
        return "";
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_REQ+" ("+
                COL_SEANCEID+" INTEGER PRIMARY KEY,"+
                COL_REMARQUE+" TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
