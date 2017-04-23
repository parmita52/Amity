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
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.android.amity.LoginActivity.userGmailID;

public class YourPostsActivity extends AppCompatActivity {
    FloatingActionButton fab_expand, fab_add, fab_home, fab_profile;
    Animation FabOpen, FabClose, FabRClockwise, FabRCounterclockwise;
    boolean isOpen = false;

    private static ArrayList<Post> yourPosts = new ArrayList<Post>();
    private static PostAdapter postAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        updateYourPosts();
      //  posts.add(new Post("Nicaragua", "Fiona Law", "Last summer, I went to Nicaragua and stayed in a local village. I played with the neighborhood kids everyday.  They brought me to the local river and taught me how to play soccer, and I scored my first goal. I hope that I can go back and visit them someday.", "af", "fad", "Fa"));
      //  posts.add(new Post("American Food", "Fiona Law", "Growing up in the US, one would assume that I enjoy eating hamburgers and french fries with a large cup of soda. So when a friend from China came to visit me, she was surprised to find that I preferred steamed vegetables and meat over a bowl of rice. Although I was born in the US and lived in the Bay Area all my life, I rarely eat hamburgers as a meal. The Bay Area is a mix of so many different cultures from around the world that I don’t have to limit my eating options to just burgers and fries. At home, my mom usually cooks two or three dishes that my entire family shares. For us, dinnertime is a time of sharing and community, rather than everyone eating their own burger.", "sd","sd" , "df"));
      //  posts.add(new Post("Japanese Restaurants in Silicon Valley", "Fiona Law", "Recently I visited several Japanese restaurants recommended by my Japanese teacher. The first one I went to was called Dan, and it was located on Saratoga Avenue. The food was really good, especially the yellowtail sashimi and salmon rice bowl. The interior of Dan was small but cozy with genuine Japanese decorations. There was also a vast collection of Sake to choose from. The second Japanese restaurant that I visited was Gochi located in Cupertino. This restaurant was much more traditional. The food was also pretty good, but I thought Dan tasted better overall.", "af", "Afds ","Afsd"));
//
//        PostAdapter postAdapter = new PostAdapter (this, posts);
//
//        ListView listView = (ListView) findViewById(R.id.template_list);
//
//        listView.setAdapter(postAdapter);



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
                Intent postIntent = new Intent(YourPostsActivity.this, CreatePostActivity.class);
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
                Intent homeIntent = new Intent(YourPostsActivity.this, MapActivity.class);
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
                Intent profileIntent = new Intent(YourPostsActivity.this, ProfileActivity.class);
                profileIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(profileIntent);
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        updateYourPosts();
    }

    private void updateYourPosts() {
        yourPosts.clear();


        String url = "http://amitty.com/your_posts.php";
        RequestQueue requestQueue = MySingleton.getInstance(null).getRequestQueue();

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            yourPosts.clear();
                            //String s = "";
                            JSONArray jsonArray = new JSONArray(response);
                            // Toast.makeText(ServerActivity.this, jsonArray.toString(), Toast.LENGTH_LONG).show();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String name = jsonObject.getString("name");
                                String title = jsonObject.getString("title");
                                String content = jsonObject.getString("content");
                                String date = jsonObject.getString("date");
                                String country = jsonObject.getString("country");
                                String city = jsonObject.getString("city");
                                yourPosts.add(0, new Post(name, title, content, date, country, city));
                                //   Toast.makeText(TopPostsActivity.this, topPosts.toString(), Toast.LENGTH_LONG).show();

                            }
                            updateListView();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(YourPostsActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();
            }
        }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("user_id", userGmailID);

                return params;

            }
        };
// Add the request to the RequestQueue.
        requestQueue.add(stringRequest);


    }
    public void updateListView(){
        postAdapter = new PostAdapter(this, yourPosts);

        ListView listView = (ListView) findViewById(R.id.template_list);

        listView.setAdapter(postAdapter);
    }
}
