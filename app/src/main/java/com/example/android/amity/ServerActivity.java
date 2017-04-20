package com.example.android.amity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.example.android.amity.LoginActivity.requestQueue;

public class ServerActivity extends AppCompatActivity {

    Button mButton;
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server);

        mTextView = (TextView) findViewById(R.id.text);
        mButton = (Button) findViewById(R.id.button);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Instantiate the RequestQueue.

                // requestQueue.start();
//                String url = "http://amitty.com/create_post.php";
////
//// Request a string response from the provided URL.
//                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
//                        new com.android.volley.Response.Listener<String>() {
//                            @Override
//                            public void onResponse(String response) {
//
//                                requestQueue.stop();
//                            }
//                        }, new com.android.volley.Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        mTextView.setText("something went wrong");
//                    }
//                }){
//                    @Override
//                    protected Map<String, String> getParams() throws AuthFailureError {
//                        Map<String, String> params = new HashMap<String, String>();
//                        params.put("user_id","1234" );
//                        params.put("title", "1 title");
//                        params.put("content", "1 content");
//                        return params;
//
//                    }   };
//// Add the request to the RequestQueue.
//                requestQueue.add(stringRequest);


                String url = "http://amitty.com/read_posts.php";
                final RequestQueue requestQueue = Volley.newRequestQueue(ServerActivity.this);

// Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new com.android.volley.Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    String s= "";
                                    JSONArray jsonArray = new JSONArray(response);
                                   // Toast.makeText(ServerActivity.this, jsonArray.toString(), Toast.LENGTH_LONG).show();
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                                        s += jsonObject.getString("name");
                                        s += jsonObject.getString("title");
                                        s += jsonObject.getString("content");
                                        s += jsonObject.getString("date");

                                    }
                                    mTextView.setText(s);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                               requestQueue.stop();
                            }
                        }, new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mTextView.setText("something went wrong");
                    }
                }
                );
// Add the request to the RequestQueue.
                requestQueue.add(stringRequest);


//                String url = "http://amitty.com/amitytest.php";
//                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, url, null, new com.android.volley.Response.Listener<JSONArray>() {
//
//                    @Override
//                    public void onResponse(JSONArray response) {
//
//                        for (int i = 0; i < response.length(); i++){
//                            try {
//                                JSONObject jsonObject = response.getJSONObject(i);
//                                String s = jsonObject.toString();
//                                mTextView.setText(mTextView + s);
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//
//
//                        }
//                    }
//                }, new com.android.volley.Response.ErrorListener() {
//
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        mTextView.setText("error");
//                    }
//                });
//
//
//                requestQueue.add(jsonArrayRequest);
//            }
//
//        });


            }
        });

    }
//package com.example.connect2;


//public class MainActivity extends Activity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.main_layout);
//
//    }
//
//    public void onConnect(View view) {
//        new Thread() {
//            public void run() {
//                HttpClient myClient = new DefaultHttpClient();
//                HttpPost post = new HttpPost("http://192.168.43.165/login.php");
//                try {
//                    List<NameValuePair> myArgs = new ArrayList<NameValuePair>();
//                    myArgs.add(new BasicNameValuePair("username", "priyanka"));
//                    myArgs.add(new BasicNameValuePair("password", "pass"));
//                    post.setEntity(new UrlEncodedFormEntity(myArgs));
//                    HttpResponse myResponse = myClient.execute(post);
//                    BufferedReader br = new BufferedReader(new InputStreamReader(myResponse.getEntity().getContent()));
//                    String line = "";
//                    while ((line = br.readLine()) != null) {
//                        Log.d("mytag", line);
//
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();
//
//    }
}