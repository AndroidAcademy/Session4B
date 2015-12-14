package com.example.felixidan.session4b;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class AndroidAcademyDatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "androidacademy.db";

    private static final String SQL_CREATION = "CREATE TABLE " + AndroidAcademyContract.SessionEntry.TABLE_NAME + " ( " +
            AndroidAcademyContract.SessionEntry._ID + " INTEGER PRIMARY KEY, " +
            AndroidAcademyContract.SessionEntry.COLUMN_DATE + " TEXT, " +
            AndroidAcademyContract.SessionEntry.COLUMN_TITLE + " TEXT, " +
            AndroidAcademyContract.SessionEntry.COLUMN_LECTURER + " TEXT, " +
            AndroidAcademyContract.SessionEntry.COLUMN_FLOOR + " INTEGER " +
                                               ")";
    private final Context context;


    public AndroidAcademyDatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATION);
        createInitialData(db);
    }

    private void createInitialData(SQLiteDatabase db) {
        insertSession(db, "2015-11-03", "Intro and Basics", "Yonatan", 34);
        insertSession(db, "2015-11-10", "Basics and ListViews", "Felix", 29);
        insertSession(db, "2015-11-17", "Off Threading", "Jonatan", 29);
        insertSession(db, "2015-12-01", "New activities and Intents", "Yonatan", 34);
        insertSession(db, "2015-12-08", "Lifecycles", "Jonatan", 34);
        insertSession(db, "2015-12-15", "Database and Stuff", null, null);
        insertSession(db, "2016-01-05", "Loaders", null, null);
    }

    public void insertSession(SQLiteDatabase db, String date, String title, String lecturer, Integer floor) {
        ContentValues values = new ContentValues();
        values.put(AndroidAcademyContract.SessionEntry.COLUMN_DATE, date);
        values.put(AndroidAcademyContract.SessionEntry.COLUMN_TITLE, title);
        values.put(AndroidAcademyContract.SessionEntry.COLUMN_LECTURER, lecturer);
        values.put(AndroidAcademyContract.SessionEntry.COLUMN_FLOOR, floor);
        db.insert(AndroidAcademyContract.SessionEntry.TABLE_NAME, null, values);

        Toast.makeText(this.context, "DB Created", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + AndroidAcademyContract.SessionEntry.TABLE_NAME);
        onCreate(db);
    }
}