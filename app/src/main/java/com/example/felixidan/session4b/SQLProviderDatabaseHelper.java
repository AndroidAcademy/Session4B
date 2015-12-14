package com.example.felixidan.session4b;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLProviderDatabaseHelper extends SQLiteOpenHelper {

    public SQLProviderDatabaseHelper(Context context){
        super(context, "numbers.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " +
                SQLProviderContract.NumberEntries.TABLE_NAME +
                " ( " +
                SQLProviderContract.NumberEntries._ID +
                " INTEGER PRIMARY KEY , " +
                SQLProviderContract.NumberEntries.COLUMN_NUMBER +
                " INTEGER )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + SQLProviderContract.NumberEntries.TABLE_NAME);
        onCreate(db);
    }
}
