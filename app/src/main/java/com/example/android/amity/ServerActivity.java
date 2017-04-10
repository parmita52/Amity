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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.okhttp.Response;

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
                final RequestQueue requestQueue = Volley.newRequestQueue(ServerActivity.this);
                String url = "http://amitty.com/index.php";

// Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new com.android.volley.Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                            mTextView.setText(response);
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