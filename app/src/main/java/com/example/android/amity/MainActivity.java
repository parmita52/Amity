package com.example.android.amity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //LOGIN**************************
        TextView login = (TextView) findViewById(R.id.login);

        // Set a click listener on that View
        login.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link NumbersActivity}
                Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);

                // Start the new activity
                startActivity(loginIntent);
            }

        });


        //MAP**************************
        Runnable runnable = new Runnable() {
            public void run() {
                final TextView map = (TextView) findViewById(R.id.map);

                // Set a click listener on that View
                map.setOnClickListener(new View.OnClickListener() {
                    // The code in this method will be executed when the numbers category is clicked on.
                    @Override
                    public void onClick(View view) {
                        // Create a new intent to open the {@link NumbersActivity}
                        Intent mapIntent = new Intent(MainActivity.this, MapActivity.class);

                        // Start the new activity
                        startActivity(mapIntent);
                    }

                });
            }
        };
        Thread mythread = new Thread(runnable);
        mythread.start();



        //TOP POSTS**************************
        TextView topPosts = (TextView) findViewById(R.id.top_posts);

        // Set a click listener on that View
        topPosts.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link NumbersActivity}
                Intent topPostsIntent = new Intent(MainActivity.this, TopPostsActivity.class);

                // Start the new activity
                startActivity(topPostsIntent);
            }

        });

        //RESULTS**************************
        TextView results = (TextView) findViewById(R.id.results);

        // Set a click listener on that View
        results.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link NumbersActivity}
                Intent resultsIntent = new Intent(MainActivity.this, ResultsActivity.class);

                // Start the new activity
                startActivity(resultsIntent);
            }

        });

        //BOOKMARKS**************************
        TextView bookmarks = (TextView) findViewById(R.id.bookmarks);

        // Set a click listener on that View
        bookmarks.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link NumbersActivity}
                Intent bookmarksIntent = new Intent(MainActivity.this, BookmarksActivity.class);

                // Start the new activity
                startActivity(bookmarksIntent);
            }

        });

        //YOUR POSTS**************************
        TextView yourPosts = (TextView) findViewById(R.id.your_posts);

        // Set a click listener on that View
        yourPosts.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link NumbersActivity}
                Intent yourPostsIntent = new Intent(MainActivity.this, YourPostsActivity.class);

                // Start the new activity
                startActivity(yourPostsIntent);
            }

        });

        //POST**************************
        TextView post = (TextView) findViewById(R.id.post);

        // Set a click listener on that View
        post.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link NumbersActivity}
                Intent postIntent = new Intent(MainActivity.this, PostActivity.class);

                // Start the new activity
                startActivity(postIntent);
            }

        });

        //CREATE POST**************************
        TextView createPost = (TextView) findViewById(R.id.create_post);

        // Set a click listener on that View
        createPost.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link NumbersActivity}
                Intent createPostIntent = new Intent(MainActivity.this, CreatePostActivity.class);

                // Start the new activity
                startActivity(createPostIntent);
            }

        });

        //FAB TESTING**************************
        TextView fabTesting = (TextView) findViewById(R.id.fab_testing);

        // Set a click listener on that View
        fabTesting.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link NumbersActivity}
                Intent fabTestingIntent = new Intent(MainActivity.this, FABTestingActivity.class);

                // Start the new activity
                startActivity(fabTestingIntent);
            }

        });


        //NAVIGATION DRAWER**************************
        TextView navigationDrawer = (TextView) findViewById(R.id.navigation_drawer);

        // Set a click listener on that View
        navigationDrawer.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link NumbersActivity}
                Intent navigationDrawerIntent = new Intent(MainActivity.this, NavigationDrawerActivity.class);

                // Start the new activity
                startActivity(navigationDrawerIntent);
            }

        });




    }
}
