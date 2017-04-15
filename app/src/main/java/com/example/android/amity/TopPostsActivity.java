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

public class TopPostsActivity extends AppCompatActivity {
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
        posts.add(new Post("Zona Rosa’s Kansas City Easter Parade", "Jim Wright", "With the coming of Easter, Kansas City inhabitants are planning a day filled with fun and tradition. The parade takes place today, 4/15, at 10 AM near the KCI Airport. It first opened in 2006 and ever since then it has been an yearly event. \n" +
                "\n" +
                "Easter would not be complete without the Easter Bunny, a \"Best Dressed\" walking parade featuring Spring finery, a petting zoo, and family games at Town Square! Every Easter, I participate in the Easter Parade with my two children. We spend the whole month before the event designing and making the little bunny outfits for the parade. After the event, I organize an Easter hunt in my backyard. For those who want to create their own Easter hunt at home, I would recommend buying the Frey chocolate bunnies protected by a plastic shell from a local department store (I used Walgreens). Of course, to complete the wonderful day, there is a neighborhood barbecue party. In a barbecue party, people gather together in someone’s backyard and share a home-cooked meal of sausages, corn, sweet potato, and, of course, Easter Chocolate."));
        posts.add(new Post("American Food", "Fiona Law", "Growing up in the US, one would assume that I enjoy eating hamburgers and french fries with a large cup of soda. So when a friend from China came to visit me, she was surprised to find that I preferred steamed vegetables and meat over a bowl of rice. Although I was born in the US and lived in the Bay Area all my life, I rarely eat hamburgers as a meal. The Bay Area is a mix of so many different cultures from around the world that I don’t have to limit my eating options to just burgers and fries. At home, my mom usually cooks two or three dishes that my entire family shares. For us, dinnertime is a time of sharing and community, rather than everyone eating their own burger."));
        posts.add(new Post("SuperCode is so cool", "Marilyn Zhang", "Our team is so cool. We've coded so much. Parmita Jess Marilyn Fiona Cassandra balh dfkjnkdsf blah blahc afrkjdhfkeruefhjhdjhr"));
        posts.add(new Post("TOP POSTS", "First Last", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla consectetur, metus ut ultrices sodales, massa felis fermentum ipsum, at volutpat felis mauris sit amet nulla. Ut sed felis tincidunt, blandit tortor eu, fringilla sapien. Ut vitae lorem enim. Donec et tellus non ipsum imperdiet malesuada eget nec lectus. Proin imperdiet varius libero nec rhoncus. In quis laoreet est. Praesent tristique, nulla at dignissim sagittis, urna velit sodales orci, in semper lectus neque eu justo. Nunc volutpat et dui sit amet euismod. Sed ultrices mattis elit ac rutrum. Vivamus commodo rutrum justo sed sagittis. Etiam faucibus maximus velit, vel elementum dui luctus at. Quisque in facilisis mi. Duis et turpis facilisis, aliquet nisi a, pulvinar neque."));
        posts.add(new Post("TOP POSTS", "First Last", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla consectetur, metus ut ultrices sodales, massa felis fermentum ipsum, at volutpat felis mauris sit amet nulla. Ut sed felis tincidunt, blandit tortor eu, fringilla sapien. Ut vitae lorem enim. Donec et tellus non ipsum imperdiet malesuada eget nec lectus. Proin imperdiet varius libero nec rhoncus. In quis laoreet est. Praesent tristique, nulla at dignissim sagittis, urna velit sodales orci, in semper lectus neque eu justo. Nunc volutpat et dui sit amet euismod. Sed ultrices mattis elit ac rutrum. Vivamus commodo rutrum justo sed sagittis. Etiam faucibus maximus velit, vel elementum dui luctus at. Quisque in facilisis mi. Duis et turpis facilisis, aliquet nisi a, pulvinar neque."));
        posts.add(new Post("TOP POSTS", "First Last", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla consectetur, metus ut ultrices sodales, massa felis fermentum ipsum, at volutpat felis mauris sit amet nulla. Ut sed felis tincidunt, blandit tortor eu, fringilla sapien. Ut vitae lorem enim. Donec et tellus non ipsum imperdiet malesuada eget nec lectus. Proin imperdiet varius libero nec rhoncus. In quis laoreet est. Praesent tristique, nulla at dignissim sagittis, urna velit sodales orci, in semper lectus neque eu justo. Nunc volutpat et dui sit amet euismod. Sed ultrices mattis elit ac rutrum. Vivamus commodo rutrum justo sed sagittis. Etiam faucibus maximus velit, vel elementum dui luctus at. Quisque in facilisis mi. Duis et turpis facilisis, aliquet nisi a, pulvinar neque."));

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
                Intent postIntent = new Intent(TopPostsActivity.this, CreatePostActivity.class);
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
                Intent homeIntent = new Intent(TopPostsActivity.this, MapActivity.class);
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
                Intent profileIntent = new Intent(TopPostsActivity.this, ProfileActivity.class);
                profileIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(profileIntent);
            }
        });
    }

}
