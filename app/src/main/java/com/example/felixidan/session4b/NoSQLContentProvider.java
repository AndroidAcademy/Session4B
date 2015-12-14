package com.example.felixidan.session4b;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.support.annotation.Nullable;

import java.util.ArrayList;

public class NoSQLContentProvider extends ContentProvider {

    ArrayList<Integer> dataStore;

    @Override
    public boolean onCreate() {
        dataStore = new ArrayList<>();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        MatrixCursor cursor = new MatrixCursor(new String[]{
                NoSQLContract.NumberEntries._ID, NoSQLContract.NumberEntries.COLUMN_NUMBER});

        for (int i = 0; i < dataStore.size(); i++) {
            MatrixCursor.RowBuilder builder = cursor.newRow();
            builder.add(NoSQLContract.NumberEntries._ID, new Integer(i));
            builder.add(NoSQLContract.NumberEntries.COLUMN_NUMBER, new Integer(dataStore.get(i)));
        }

        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return NoSQLContract.NumberEntries.CONTENT_TYPE;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int value = values.getAsInteger(NoSQLContract.NumberEntries.COLUMN_NUMBER);
        int newIndex = dataStore.size();
        dataStore.add(new Integer(value));

        Uri itemUri = NoSQLContract.NumberEntries.buildLocationUri(newIndex);

        getContext().getContentResolver().notifyChange(uri, null);

        return itemUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        throw new UnsupportedOperationException("delete operation not supported.");
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        throw new UnsupportedOperationException("update operation not supported.");
    }
}
