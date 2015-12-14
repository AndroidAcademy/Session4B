package com.example.felixidan.session4b;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SqlInjectionDemoActivity extends ActionBarActivity {

    private SqlInjectionDbHelper dbHelper;
    private EditText username;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql_injection_demo);

        dbHelper = new SqlInjectionDbHelper(this);

        username = (EditText)findViewById(R.id.sqlinjection_username);
        password = (EditText)findViewById(R.id.sqlinjection_password);
    }


    public void attemptLogin(View view) {

        try {
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            String uname = username.getText().toString();
            String pword = password.getText().toString();
            String query = "SELECT * FROM LOGINS WHERE ((UNAME = '" + uname + "') AND (PWORD = '" + pword + "'))";

            Cursor c = db.rawQuery(query, null);

            int numberOfResults = c.getCount();

            Toast.makeText(this, "Found " + numberOfResults + " Results", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Toast.makeText(this, "Got an Exception", Toast.LENGTH_SHORT).show();
        }
    }

    public void attemptLoginWithoutInjection(View view) {

        try {
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            String uname = username.getText().toString();
            String pword = password.getText().toString();
            String query = "SELECT * FROM LOGINS WHERE ((UNAME = ?) AND (PWORD = ?))";

            Cursor c = db.rawQuery(query, new String[]{uname, pword});

            int numberOfResults = c.getCount();

            Toast.makeText(this, "Found " + numberOfResults + " Results", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Toast.makeText(this, "Got an Exception", Toast.LENGTH_SHORT).show();
        }
    }

}
