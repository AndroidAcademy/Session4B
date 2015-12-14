package com.example.felixidan.session4b;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

public final class NoSQLContract {

    public static final String CONTENT_AUTHORITY = "com.example.felixidan.session4b.nosql.provider";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String NUMBERS_LOCATION = "Numbers";

    public static final class NumberEntries implements BaseColumns{
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(NUMBERS_LOCATION).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + NUMBERS_LOCATION;
        public static final String COLUMN_NUMBER = "number";

        public static Uri buildLocationUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }
}
