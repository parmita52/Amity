package com.example.android.amity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.amity.data.AmityContract.AmityEntry;
import com.example.android.amity.data.AmityDbHelper;

/**
 * Displays list of pets that were entered and stored in the app.
 */
public class UsersActivity extends AppCompatActivity {

    /** Database helper that will provide us access to the database */
    private AmityDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        mDbHelper = new AmityDbHelper(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        insertUser();

        displayDatabaseInfo();
    }

    /**
     * Temporary helper method to display information in the onscreen TextView about the state of
     * the pets database.
     */
    private void displayDatabaseInfo() {
        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                AmityEntry._ID,
                AmityEntry.COLUMN_NAME,
                AmityEntry.COLUMN_EMAIL,
                AmityEntry.COLUMN_GENDER,
                AmityEntry.COLUMN_GMAIL_ID };

        // Perform a query on the pets table
        Cursor cursor = db.query(
                AmityEntry.TABLE_NAME,   // The table to query
                projection,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                   // The sort order

        TextView displayView = (TextView) findViewById(R.id.text_view_user);

        try {
            // Create a header in the Text View that looks like this:
            //
            // The pets table contains <number of rows in Cursor> pets.
            // _id - name - breed - gender - weight
            //
            // In the while loop below, iterate through the rows of the cursor and display
            // the information from each column in this order.
            displayView.setText("The user table contains " + cursor.getCount() + " users.\n\n");
            displayView.append(AmityEntry._ID + " - " +
                    AmityEntry.COLUMN_NAME + " - " +
                    AmityEntry.COLUMN_EMAIL + " - " +
                    AmityEntry.COLUMN_GENDER + " - " +
                    AmityEntry.COLUMN_GMAIL_ID + "\n");

            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(AmityEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(AmityEntry.COLUMN_NAME);
            int emailColumnIndex = cursor.getColumnIndex(AmityEntry.COLUMN_EMAIL);
            int genderColumnIndex = cursor.getColumnIndex(AmityEntry.COLUMN_GENDER);
            int gmailIDColumnIndex = cursor.getColumnIndex(AmityEntry.COLUMN_GMAIL_ID);

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                String currentEmail = cursor.getString(emailColumnIndex);
                int currentGender = cursor.getInt(genderColumnIndex);
                int currentGmailID = cursor.getInt(gmailIDColumnIndex);
                // Display the values from each column of the current row in the cursor in the TextView
                displayView.append(("\n" + currentID + " - " +
                        currentName + " - " +
                        currentEmail + " - " +
                        currentGender + " - " +
                        currentGmailID));
            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }

    /**
     * Helper method to insert hardcoded pet data into the database. For debugging purposes only.
     */
    private void insertUser() {
        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and Toto's pet attributes are the values.
        ContentValues values = new ContentValues();
        values.put(AmityEntry.COLUMN_NAME, "AMITY IS AWESOME");
        values.put(AmityEntry.COLUMN_EMAIL, "amityiscool@gmail.com");
        values.put(AmityEntry.COLUMN_GENDER, AmityEntry.GENDER_FEMALE);
        values.put(AmityEntry.COLUMN_GMAIL_ID, 12093);

        // Insert a new row for Toto in the database, returning the ID of that new row.
        // The first argument for db.insert() is the pets table name.
        // The second argument provides the name of a column in which the framework
        // can insert NULL in the event that the ContentValues is empty (if
        // this is set to "null", then the framework will not insert a row when
        // there are no values).
        // The third argument is the ContentValues object containing the info for Toto.
        long newRowId = db.insert(AmityEntry.TABLE_NAME, null, values);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu options from the res/menu/menu_catalog.xml file.
//        // This adds menu items to the app bar.
//        getMenuInflater().inflate(R.menu.menu_catalog, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // User clicked on a menu option in the app bar overflow menu
//        switch (item.getItemId()) {
//            // Respond to a click on the "Insert dummy data" menu option
//            case R.id.action_insert_dummy_data:
//                insertPet();
//                displayDatabaseInfo();
//                return true;
//            // Respond to a click on the "Delete all entries" menu option
//            case R.id.action_delete_all_entries:
//                // Do nothing for now
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
