package com.example.felixidan.session4b;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.Random;

public class NoSQLContentProviderDemoActivity extends ActionBarActivity {

    private SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_sqlcontent_provider_demo);

        ListView listView = (ListView)findViewById(R.id.nosqlcp_listview);
        adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, null,
                new String[] {NoSQLContract.NumberEntries._ID, NoSQLContract.NumberEntries.COLUMN_NUMBER},
                new int[] {android.R.id.text2, android.R.id.text1});

        listView.setAdapter(adapter);


    }

    public void insertARandomNumber(View view) {
        Random random = new Random();
        int randomNumber = random.nextInt(100);

        ContentValues newNumberValues = new ContentValues();
        newNumberValues.put(NoSQLContract.NumberEntries.COLUMN_NUMBER, randomNumber);

        getContentResolver()
                .insert(NoSQLContract.NumberEntries.CONTENT_URI,
                        newNumberValues);

        Toast.makeText(this, "Inserted '" + randomNumber + "'.", Toast.LENGTH_SHORT).show();

    }

    public void refresh(View view) {
        Cursor cursor = getContentResolver()
                            .query(NoSQLContract.NumberEntries.CONTENT_URI,
                                    null,
                                    null,
                                    null,
                                    null);
        adapter.swapCursor(cursor);
    }
}
