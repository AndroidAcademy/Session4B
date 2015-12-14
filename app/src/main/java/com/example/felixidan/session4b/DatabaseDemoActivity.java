package com.example.felixidan.session4b;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.Random;

public class DatabaseDemoActivity extends ActionBarActivity {

    private AndroidAcademyDatabaseHelper dbHelper;
    private ListView listView;
    private SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_demo);


        dbHelper = new AndroidAcademyDatabaseHelper(this);

        listView = (ListView)findViewById(R.id.databasedemo_listview);
        adapter = new SimpleCursorAdapter(this,
                                                              android.R.layout.simple_list_item_2,
                                                              null,
                                                              new String[]{
                                                                      AndroidAcademyContract.SessionEntry.COLUMN_TITLE,
                                                                      AndroidAcademyContract.SessionEntry.COLUMN_DATE
                                                              },
                                                              new int[]{
                                                                      android.R.id.text1,
                                                                      android.R.id.text2
                                                              });

        listView.setAdapter(adapter);
    }


    public void getAll(View view) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(AndroidAcademyContract.SessionEntry.TABLE_NAME,
                new String[]{},
                "",
                null,
                null,
                null,
                null);

        adapter.swapCursor(cursor);
        Toast.makeText(this, "Got all.", Toast.LENGTH_SHORT).show();

    }

    public void getAnds(View view) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(AndroidAcademyContract.SessionEntry.TABLE_NAME,
                new String[]{},
                AndroidAcademyContract.SessionEntry.COLUMN_TITLE + " LIKE ?",
                new String[] { "%and%" },
                null,
                null,
                null);

        adapter.swapCursor(cursor);
        Toast.makeText(this, "Got 'and's.", Toast.LENGTH_SHORT).show();
    }

    public void get29s(View view) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(AndroidAcademyContract.SessionEntry.TABLE_NAME,
                new String[]{},
                AndroidAcademyContract.SessionEntry.COLUMN_FLOOR+ " = ? ",
                new String[] { "29" },
                null,
                null,
                null);

        adapter.swapCursor(cursor);
        Toast.makeText(this, "Got 29's.", Toast.LENGTH_SHORT).show();
    }

    public void randomCap(View view) {
        Cursor c = getRandomItem();

        int id = c.getInt(c.getColumnIndex(AndroidAcademyContract.SessionEntry._ID));
        String title = c.getString(c.getColumnIndex(AndroidAcademyContract.SessionEntry.COLUMN_TITLE));
        String uppercase = title.toUpperCase();

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues whatToWrite = new ContentValues();
        whatToWrite.put(AndroidAcademyContract.SessionEntry.COLUMN_TITLE, uppercase);
        db.update(AndroidAcademyContract.SessionEntry.TABLE_NAME,
                whatToWrite,
                AndroidAcademyContract.SessionEntry._ID + " = ?",
                new String[]{Integer.toString(id)});

        Toast.makeText(this, "Updated.", Toast.LENGTH_SHORT).show();
    }

    private Cursor getRandomItem() {
        Random r = new Random();
        int random = r.nextInt(adapter.getCount());
        ;

        return (Cursor)adapter.getItem(random);
    }

    public void randomDelete(View view) {
        Cursor c = getRandomItem();

        int id = c.getInt(c.getColumnIndex(AndroidAcademyContract.SessionEntry._ID));

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(AndroidAcademyContract.SessionEntry.TABLE_NAME,
                AndroidAcademyContract.SessionEntry._ID + " = ?",
                new String[]{Integer.toString(id)});

        Toast.makeText(this, "Deleted.", Toast.LENGTH_SHORT).show();
    }

    public void reset(View view) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        dbHelper.onUpgrade(db, 1, 1);
        Toast.makeText(this, "Reset.", Toast.LENGTH_SHORT).show();
    }
}
