package com.example.android.amity.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.amity.data.AmityContract.AmityEntry;

/**
 * Database helper for Pets app. Manages database creation and version management.
 */
public class AmityDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = AmityDbHelper.class.getSimpleName();

    /** Name of the database file */
    private static final String DATABASE_NAME = "amity.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Constructs a new instance of {@link AmityDbHelper}.
     *
     * @param context of the app
     */
    public AmityDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is created for the first time.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the pets table
        String SQL_CREATE_USERS_TABLE =  "CREATE TABLE " + AmityEntry.TABLE_NAME + " ("
                + AmityEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + AmityEntry.COLUMN_NAME + " TEXT NOT NULL, "
                + AmityEntry.COLUMN_EMAIL + " TEXT, "
                + AmityEntry.COLUMN_GENDER + " INTEGER NOT NULL,"
                + AmityEntry.COLUMN_GMAIL_ID + " INTEGER NOT NULL);";


        // Execute the SQL statement
        db.execSQL(SQL_CREATE_USERS_TABLE);
    }

    /**
     * This is called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
    }
}