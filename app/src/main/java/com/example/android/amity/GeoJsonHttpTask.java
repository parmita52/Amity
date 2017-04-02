package com.example.android.amity;

/**
 * Created by fiona on 3/5/2017.
 */

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.ParcelFileDescriptor;

import com.mousebird.maply.MaplyBaseController;
import com.mousebird.maply.VectorInfo;
import com.mousebird.maply.VectorObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static java.security.AccessController.getContext;

public class GeoJsonHttpTask extends AsyncTask<String, Void, String> {

    MaplyBaseController controller;

    public GeoJsonHttpTask(MaplyBaseController maplyBaseController) {
        controller = maplyBaseController;
    }

    @Override
    protected String doInBackground(String... params) {

        HttpURLConnection urlConnection;
        try {
            String urlStr = params[0];
            URL url = new URL(urlStr);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setConnectTimeout(3000);
            urlConnection.setReadTimeout(7000);
            int statusCode = urlConnection.getResponseCode();

            // 200 represents HTTP OK
            if (statusCode == 200) {
                BufferedReader r = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = r.readLine()) != null) {
                    response.append(line);
                }
                return response.toString();
            }
        } catch (Exception e) {
            // didn't work
        }
        return null;
    }

    @Override
    protected void onPostExecute(String json) {
        VectorInfo vectorInfo = new VectorInfo();
        vectorInfo.setColor(Color.DKGRAY);
        vectorInfo.setLineWidth(4.f);
        VectorObject object = new VectorObject();
        object.selectable = true;
        if (object.fromGeoJSON(json)) {
            controller.addVector(object, vectorInfo, MaplyBaseController.ThreadMode.ThreadAny);
        }
    }

}