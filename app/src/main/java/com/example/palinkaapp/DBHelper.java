package com.example.palinkaapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "palinka.db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "palinka";
    private static final String COL_ID = "id";
    private static final String COL_MANUFACT = "fozo";
    private static final String COL_FRUIT = "gyumolcs";
    private static final String COL_ALCOHOL = "alkohol";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_MANUFACT + " TEXT NOT NULL, " +
                COL_FRUIT + " TEXT NOT NULL, " +
                COL_ALCOHOL + " TEXT NOT NULL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean dataRecord(String manufact, String fruit, int alcohol) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_MANUFACT, manufact);
        values.put(COL_FRUIT, fruit);
        values.put(COL_ALCOHOL, alcohol);
        long result = db.insert(TABLE_NAME, null, values);

        return result != -1;
    }

    public Cursor dataGet() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_NAME, new String[]{COL_ID, COL_MANUFACT, COL_FRUIT, COL_ALCOHOL},
                null, null, null, null, null);
    }

    public Cursor dataGet(String manufact, String fruit) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT " + COL_ALCOHOL + " FROM " + TABLE_NAME +
                " WHERE " + COL_MANUFACT + " =? AND " + COL_FRUIT + " =?",
                new String[]{manufact, fruit});
    }
}
