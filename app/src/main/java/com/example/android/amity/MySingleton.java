package com.example.android.amity;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Jess on 4/15/2017.
 */

public class MySingleton {

    private static MySingleton mInstance;
    private RequestQueue requestQueue;
    private static Context context;

    private MySingleton(Context context){
        this.context = context;
        requestQueue = getRequestQueue();
    }

    public static synchronized  MySingleton getInstance(Context context){
        if (mInstance == null){
            mInstance = new MySingleton(context);

        }
        return mInstance;
    }



    public RequestQueue getRequestQueue(){
        if (requestQueue == null){
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());

        }
        return requestQueue;
    }

    public <T>void addToRequestQueue(Request<T> request){
     requestQueue.add(request) ;
    }

}
