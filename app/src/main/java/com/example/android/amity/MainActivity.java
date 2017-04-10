package com.example.android.amity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

//import static com.example.android.amity.R.id.setup;

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

        //LOGIN**************************
        TextView db = (TextView) findViewById(R.id.db);

        // Set a click listener on that View
        db.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link NumbersActivity}
                Intent dbIntent = new Intent(MainActivity.this, UsersActivity.class);

                // Start the new activity
                startActivity(dbIntent);
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

//        //FAB TESTING**************************
//        TextView fabTesting = (TextView) findViewById(R.id.fab_testing);
//
//        // Set a click listener on that View
//        fabTesting.setOnClickListener(new View.OnClickListener() {
//            // The code in this method will be executed when the numbers category is clicked on.
//            @Override
//            public void onClick(View view) {
//                // Create a new intent to open the {@link NumbersActivity}
//                Intent fabTestingIntent = new Intent(MainActivity.this, FABTestingActivity.class);
//
//                // Start the new activity
//                startActivity(fabTestingIntent);
//            }
//
//        });

//        //setup TESTING**************************
//        TextView setup = (TextView) findViewById(R.id.setup);
//
//        // Set a click listener on that View
//       setup.setOnClickListener(new View.OnClickListener() {
//            // The code in this method will be executed when the numbers category is clicked on.
//            @Override
//            public void onClick(View view) {
//                // Create a new intent to open the {@link NumbersActivity}
//                Intent setupIntent = new Intent(MainActivity.this, SetupActivity.class);
//
//                // Start the new activity
//                startActivity(setupIntent);
//            }
//
//        });
                //setup TESTING**************************
        TextView server = (TextView) findViewById(R.id.server);

        // Set a click listener on that View
       server.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link NumbersActivity}
                Intent serverIntent = new Intent(MainActivity.this,ServerActivity.class);

                // Start the new activity
                startActivity(serverIntent);
            }

        });


        //Profile**************************
        TextView profile = (TextView) findViewById(R.id.profile);

        // Set a click listener on that View
        profile.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link NumbersActivity}
                Intent profileIntent = new Intent(MainActivity.this, ProfileActivity.class);

                // Start the new activity
                startActivity(profileIntent);
            }

        });




    }
}
