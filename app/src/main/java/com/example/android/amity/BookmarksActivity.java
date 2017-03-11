package com.example.android.amity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ListView;

import java.util.ArrayList;

public class BookmarksActivity extends AppCompatActivity {
    FloatingActionButton fab_expand, fab_add, fab_home, fab_profile;
    Animation FabOpen, FabClose, FabRClockwise, FabRCounterclockwise;
    boolean isOpen = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ArrayList<Post> posts = new ArrayList<Post>();
        posts.add(new Post("Title here", "Author here", "Content here"));
        posts.add(new Post("BOOKMARKS", "First Last", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla consectetur, metus ut ultrices sodales, massa felis fermentum ipsum, at volutpat felis mauris sit amet nulla. Ut sed felis tincidunt, blandit tortor eu, fringilla sapien. Ut vitae lorem enim. Donec et tellus non ipsum imperdiet malesuada eget nec lectus. Proin imperdiet varius libero nec rhoncus. In quis laoreet est. Praesent tristique, nulla at dignissim sagittis, urna velit sodales orci, in semper lectus neque eu justo. Nunc volutpat et dui sit amet euismod. Sed ultrices mattis elit ac rutrum. Vivamus commodo rutrum justo sed sagittis. Etiam faucibus maximus velit, vel elementum dui luctus at. Quisque in facilisis mi. Duis et turpis facilisis, aliquet nisi a, pulvinar neque."));
        posts.add(new Post("SuperCode is so cool", "Marilyn Zhang", "Our team is so cool. We've coded so much. Parmita Jess Marilyn Fiona Cassandra balh dfkjnkdsf blah blahc afrkjdhfkeruefhjhdjhr"));
        posts.add(new Post("BOOKMARKS", "First Last", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla consectetur, metus ut ultrices sodales, massa felis fermentum ipsum, at volutpat felis mauris sit amet nulla. Ut sed felis tincidunt, blandit tortor eu, fringilla sapien. Ut vitae lorem enim. Donec et tellus non ipsum imperdiet malesuada eget nec lectus. Proin imperdiet varius libero nec rhoncus. In quis laoreet est. Praesent tristique, nulla at dignissim sagittis, urna velit sodales orci, in semper lectus neque eu justo. Nunc volutpat et dui sit amet euismod. Sed ultrices mattis elit ac rutrum. Vivamus commodo rutrum justo sed sagittis. Etiam faucibus maximus velit, vel elementum dui luctus at. Quisque in facilisis mi. Duis et turpis facilisis, aliquet nisi a, pulvinar neque."));
        posts.add(new Post("BOOKMARKS", "First Last", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla consectetur, metus ut ultrices sodales, massa felis fermentum ipsum, at volutpat felis mauris sit amet nulla. Ut sed felis tincidunt, blandit tortor eu, fringilla sapien. Ut vitae lorem enim. Donec et tellus non ipsum imperdiet malesuada eget nec lectus. Proin imperdiet varius libero nec rhoncus. In quis laoreet est. Praesent tristique, nulla at dignissim sagittis, urna velit sodales orci, in semper lectus neque eu justo. Nunc volutpat et dui sit amet euismod. Sed ultrices mattis elit ac rutrum. Vivamus commodo rutrum justo sed sagittis. Etiam faucibus maximus velit, vel elementum dui luctus at. Quisque in facilisis mi. Duis et turpis facilisis, aliquet nisi a, pulvinar neque."));
        posts.add(new Post("BOOKMARKS", "First Last", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla consectetur, metus ut ultrices sodales, massa felis fermentum ipsum, at volutpat felis mauris sit amet nulla. Ut sed felis tincidunt, blandit tortor eu, fringilla sapien. Ut vitae lorem enim. Donec et tellus non ipsum imperdiet malesuada eget nec lectus. Proin imperdiet varius libero nec rhoncus. In quis laoreet est. Praesent tristique, nulla at dignissim sagittis, urna velit sodales orci, in semper lectus neque eu justo. Nunc volutpat et dui sit amet euismod. Sed ultrices mattis elit ac rutrum. Vivamus commodo rutrum justo sed sagittis. Etiam faucibus maximus velit, vel elementum dui luctus at. Quisque in facilisis mi. Duis et turpis facilisis, aliquet nisi a, pulvinar neque."));

        PostAdapter postAdapter = new PostAdapter (this, posts);

        ListView listView = (ListView) findViewById(R.id.template_list);

        listView.setAdapter(postAdapter);



        //          Start of FAB**************************************
         /*
        initializing fab buttons by id
         */
        fab_expand = (FloatingActionButton) findViewById(R.id.fab_expand);
        fab_add = (FloatingActionButton) findViewById(R.id.fab_add);
        fab_home = (FloatingActionButton) findViewById(R.id.fab_home);
        fab_profile = (FloatingActionButton) findViewById(R.id.fab_profile);

         /*
        initializing animations
         */
        FabOpen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        FabClose = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        FabRClockwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_clockwise);
        FabRCounterclockwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_counterclockwise);

         /*
            on click listener for expand fab
            creates animations for when expand fab is clicked
         */
        fab_expand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isOpen){
                    //close animation for 3 buttons
                    fab_add.startAnimation(FabClose);
                    fab_home.startAnimation(FabClose);
                    fab_profile.startAnimation(FabClose);
                    //rotates expand button counterclockwise
                    fab_expand.startAnimation(FabRCounterclockwise);
                    //sets clickable for 3 buttons false
                    fab_add.setClickable(false);
                    fab_home.setClickable(false);
                    fab_profile.setClickable(false);
                    isOpen = false;

                }
                else{
                    //open animation for 3 buttons
                    fab_add.startAnimation(FabOpen);
                    fab_home.startAnimation(FabOpen);
                    fab_profile.startAnimation(FabOpen);
                    //rotates expand button clockwise
                    fab_expand.startAnimation(FabRClockwise);
                    //sets clickable for 3 buttons true
                    fab_add.setClickable(true);
                    fab_home.setClickable(true);
                    fab_profile.setClickable(true);
                    isOpen = true;
                }

            }
        });

        /*
            on click listener for add fab
            goes to PostActivity
         */
        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent postIntent = new Intent(BookmarksActivity.this, CreatePostActivity.class);
                startActivity(postIntent);
            }
        });

        /*
            on click listener for home fab
            goes to MapActivity
         */
        fab_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeIntent = new Intent(BookmarksActivity.this, MapActivity.class);
                startActivity(homeIntent);
            }
        });

        /*
            on click listener for profile fab
            goes to Profile Activity
         */
        fab_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent profileIntent = new Intent(BookmarksActivity.this, ProfileActivity.class);
                startActivity(profileIntent);
            }
        });
    }

}
