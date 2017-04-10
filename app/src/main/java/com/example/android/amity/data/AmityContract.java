package com.example.android.amity.data;

import android.provider.BaseColumns;

/**
 * Created by Marilyn on 3/19/2017.
 */

public final class AmityContract {
    public static abstract class AmityEntry implements BaseColumns {
        public static final String TABLE_NAME = "users";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_GENDER = "gender";
        public static final String COLUMN_GMAIL_ID = "gmail";

        /*
        possible values for gender
         */

        public static final int GENDER_FEMALE = 1;
        public static final int GENDER_MALE = 2;
        public static final int GENDER_UNKNOWN = 0;

    }

    public static abstract  class AmityPost implements BaseColumns{
        public static final String TABLE_NAME = "posts";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_USERID = "user_id";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_CONTENT  = "content";
        public static final String COLUMN_DATE = "date";
    }
}
