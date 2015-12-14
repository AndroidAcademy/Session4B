package com.example.felixidan.session4b;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

public class SQLContentProvider extends ContentProvider {

    SQLProviderDatabaseHelper dbHelper;

    @Override
    public boolean onCreate() {
        dbHelper = new SQLProviderDatabaseHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor =
                db.query(SQLProviderContract.NumberEntries.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);

        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return SQLProviderContract.NumberEntries.CONTENT_TYPE;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long newId = db.insert(SQLProviderContract.NumberEntries.TABLE_NAME, null, values);

        getContext().getContentResolver().notifyChange(uri, null);

        return SQLProviderContract.NumberEntries.buildLocationUri(newId);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int deleted = db.delete(SQLProviderContract.NumberEntries.TABLE_NAME, selection, selectionArgs);

        getContext().getContentResolver().notifyChange(uri, null);

        return deleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int updated = db.update(SQLProviderContract.NumberEntries.TABLE_NAME, values, selection, selectionArgs);

        getContext().getContentResolver().notifyChange(uri, null);

        return updated;
    }
}
