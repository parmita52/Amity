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
        posts.add(new Post("Jean-Paul Durand", "Quebec Bonhomme Festival", "Just had a great time at the Bonhomme Festival in Quebec! C’était génial! My kids loved watching the canoe race, the snow sculpture contest, and the parade! We even went ice skating for the first time. Personally, I loved looking at all the different intricate ice sculptures. In the night, we met the Bonhomme and my kids were so excited to take a picture with him. Sadly, we weren’t able to get inside of Château Frontenac for the ball. Maybe next year!", "2017-02-18", "Canada", "Quebec"));
        posts.add(new Post("Anita Overney", "Swiss Food", "This weekend my grandchildren from America visited me at Basel. It was the first time visiting their father’s home country, and I wanted to enrich them in Swiss Food. I asked my granddaughter, a 15 year old, what Swiss food she heard of, and she only mentioned Cheese Fondue. I was completely shocked!! Even though she was a Swiss Citizen, she had no idea about Swiss culture. Cheese Fondue is a dish targeted for tourists, not necessarily locals. It was first introduced after the World War, because there was an excess of cheese in the Swiss cheese industry. In order to get rid of the excess, cheese fondue, traditionally a mountain dish, was introduced throughout Switzerland. I found it fascinating that a once unknown dish is suddenly representing Swiss cuisine. So I decided to educate my family and lead them on a Swiss food immersion trip. We went to Zurich to eat Rosti, a simple potato dish, and to Luzern to eat Raclette, melted cheese on potatoes. In the end, we all gained at least 5 pounds, but more importantly, my grandchildren gained knowledge about their Swiss culture, and that matters.", "2017-04-16", "Switzerland", "Basel"));
        
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
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
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
                profileIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(profileIntent);
            }
        });
    }

}
