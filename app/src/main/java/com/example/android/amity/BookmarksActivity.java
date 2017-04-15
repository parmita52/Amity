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
        posts.add(new Post("Blend of Cultures", "Riya Patel", "Hello friends, today I wanted to post about the time I visited my family in Nagpur, Maharashtra. I arrived wearing typical American attire--a t-shirt and jeans and I felt a bit out of place when I went to the local store with my family. Everyone else was wearing traditional Indian apparel--saris and kurtas. So later that day, we bought traditional Indian clothing for me to wear the next day. I felt so proud wearing my new kurta out and my family decided to show me the big mall in their city. When I went there I was surprised to see that everyone else was wearing jeans and tshirts and this time I was the only one wearing traditional Indian clothing! I learned that nowadays, Indian culture is pretty blended with Western culture and depending on where you go, even within in the same city, people may reflect a different part of that identity. In Nagpur, you can see people wearing clothing from completely different cultures walking side by side. The city is a blend of different influences."));
        posts.add(new Post("Zona Rosa’s Kansas City Easter Parade", "Jim Wright", "With the coming of Easter, Kansas City inhabitants are planning a day filled with fun and tradition. The parade takes place today, 4/15, at 10 AM near the KCI Airport. It first opened in 2006 and ever since then it has been an yearly event. \n" +
                "\n" +
                "Easter would not be complete without the Easter Bunny, a \"Best Dressed\" walking parade featuring Spring finery, a petting zoo, and family games at Town Square! Every Easter, I participate in the Easter Parade with my two children. We spend the whole month before the event designing and making the little bunny outfits for the parade. After the event, I organize an Easter hunt in my backyard. For those who want to create their own Easter hunt at home, I would recommend buying the Frey chocolate bunnies protected by a plastic shell from a local department store (I used Walgreens). Of course, to complete the wonderful day, there is a neighborhood barbecue party. In a barbecue party, people gather together in someone’s backyard and share a home-cooked meal of sausages, corn, sweet potato, and, of course, Easter Chocolate."));
        //posts.add(new Post("SuperCode is so cool", "Marilyn Zhang", "Our team is so cool. We've coded so much. Parmita Jess Marilyn Fiona Cassandra balh dfkjnkdsf blah blahc afrkjdhfkeruefhjhdjhr"));
        posts.add(new Post("Swiss Food", "Anita Overney", "This weekend my grandchildren from America visited me at Basel. It was the first time visiting their father’s home country, and I wanted to enrich them in Swiss Food. I asked my granddaughter, a 15 year old, what Swiss food she heard of, and she only mentioned Cheese Fondue. I was completely shocked!! Even though she was a Swiss Citizen, she had no idea about Swiss culture. Cheese Fondue is a dish targeted for tourists, not necessarily locals. It was first introduced after the World War, because there was an excess of cheese in the Swiss cheese industry. In order to get rid of the excess, cheese fondue, traditionally a mountain dish, was introduced throughout Switzerland. I found it fascinating that a once unknown dish is suddenly representing Swiss cuisine. So I decided to educate my family and lead them on a Swiss food immersion trip. We went to Zurich to eat Rosti, a simple potato dish, and to Luzern to eat Raclette, melted cheese on potatoes. In the end, we all gained at least 5 pounds, but more importantly, my grandchildren gained knowledge about their Swiss culture, and that matters."));
        posts.add(new Post("We love Technovation", "Team SuperCode", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla consectetur, metus ut ultrices sodales, massa felis fermentum ipsum, at volutpat felis mauris sit amet nulla. Ut sed felis tincidunt, blandit tortor eu, fringilla sapien. Ut vitae lorem enim. Donec et tellus non ipsum imperdiet malesuada eget nec lectus. Proin imperdiet varius libero nec rhoncus. In quis laoreet est. Praesent tristique, nulla at dignissim sagittis, urna velit sodales orci, in semper lectus neque eu justo. Nunc volutpat et dui sit amet euismod. Sed ultrices mattis elit ac rutrum. Vivamus commodo rutrum justo sed sagittis. Etiam faucibus maximus velit, vel elementum dui luctus at. Quisque in facilisis mi. Duis et turpis facilisis, aliquet nisi a, pulvinar neque."));
        posts.add(new Post("We love Technovation", "Team SuperCode", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla consectetur, metus ut ultrices sodales, massa felis fermentum ipsum, at volutpat felis mauris sit amet nulla. Ut sed felis tincidunt, blandit tortor eu, fringilla sapien. Ut vitae lorem enim. Donec et tellus non ipsum imperdiet malesuada eget nec lectus. Proin imperdiet varius libero nec rhoncus. In quis laoreet est. Praesent tristique, nulla at dignissim sagittis, urna velit sodales orci, in semper lectus neque eu justo. Nunc volutpat et dui sit amet euismod. Sed ultrices mattis elit ac rutrum. Vivamus commodo rutrum justo sed sagittis. Etiam faucibus maximus velit, vel elementum dui luctus at. Quisque in facilisis mi. Duis et turpis facilisis, aliquet nisi a, pulvinar neque."));

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
