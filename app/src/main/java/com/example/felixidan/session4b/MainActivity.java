package com.example.felixidan.session4b;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void goToDatabaseDemo(View view) {
        goToActivity(DatabaseDemoActivity.class);
    }

    public void goToSqlInjectionDemo(View view) {
        goToActivity(SqlInjectionDemoActivity.class);
    }

    public void goToNoSQLContentProviderDemo(View view) {
        goToActivity(NoSQLContentProviderDemoActivity.class);
    }

    public void goToSQLContentProviderDemo(View view) {
        goToActivity(SQLContentProviderDemoActivity.class);
    }



    private void goToActivity(Class<?> target){
        Intent i = new Intent(this, target);
        startActivity(i);
    }


}
