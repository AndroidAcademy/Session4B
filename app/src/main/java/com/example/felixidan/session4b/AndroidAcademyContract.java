package com.example.felixidan.session4b;

import android.provider.BaseColumns;

public class AndroidAcademyContract {

    public static class SessionEntry implements BaseColumns {

        public static final String TABLE_NAME = "Sessions";

        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_LECTURER = "lecturer";
        public static final String COLUMN_FLOOR = "floor";
    }
}
