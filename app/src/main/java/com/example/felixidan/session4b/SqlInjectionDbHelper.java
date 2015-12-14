package com.example.felixidan.session4b;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqlInjectionDbHelper extends SQLiteOpenHelper {
    public SqlInjectionDbHelper(Context context) {
        super(context, "sqlinject.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Logins ( _ID INTEGER PRIMARY KEY, UNAME TEXT, PWORD TEXT )");
        db.execSQL("INSERT INTO Logins ( 00) VALUES ('ADMIN' , '1234')");
        db.execSQL("INSERT INTO Logins ( UNAME , PWORD ) VALUES ('GUEST' , '1234')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Logins");
        onCreate(db);
    }
}
