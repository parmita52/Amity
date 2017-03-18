package com.example.android.amity;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mousebird.maply.AttrDictionary;
import com.mousebird.maply.GlobeController;
import com.mousebird.maply.GlobeMapFragment;
import com.mousebird.maply.MapController;
import com.mousebird.maply.MaplyBaseController;
import com.mousebird.maply.MarkerInfo;
import com.mousebird.maply.Point2d;
import com.mousebird.maply.Point3d;
import com.mousebird.maply.QuadImageTileLayer;
import com.mousebird.maply.RemoteTileInfo;
import com.mousebird.maply.RemoteTileSource;
import com.mousebird.maply.ScreenMarker;
import com.mousebird.maply.SelectedObject;
import com.mousebird.maply.SphericalMercatorCoordSystem;
import com.mousebird.maply.VectorObject;

import java.io.File;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;
import static android.util.Log.d;


public class HelloGlobeFragment extends GlobeMapFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle inState) {
        super.onCreateView(inflater, container, inState);

        // Do app specific setup logic.

        return baseControl.getContentView();
    }

    @Override
    protected MapDisplayType chooseDisplayType() {
        return MapDisplayType.Globe;
    }

    @Override
    protected void controlHasStarted() {
        // setup base layer tiles
        String cacheDirName = "stamen_terrain-background";
        File cacheDir = new File(getActivity().getCacheDir(), cacheDirName);
        cacheDir.mkdir();
        RemoteTileSource remoteTileSource = new RemoteTileSource(new RemoteTileInfo("http://tile.stamen.com/terrain-background/", "png", 0, 18));
        remoteTileSource.setCacheDir(cacheDir);
        SphericalMercatorCoordSystem coordSystem = new SphericalMercatorCoordSystem();

        // globeControl is the controller when using MapDisplayType.Globe
        // mapControl is the controller when using MapDisplayType.Map
        QuadImageTileLayer baseLayer = new QuadImageTileLayer(globeControl, coordSystem, remoteTileSource);
        baseLayer.setImageDepth(1);
        baseLayer.setSingleLevelLoading(false);
        baseLayer.setUseTargetZoomLevel(false);
        baseLayer.setCoverPoles(true);
        baseLayer.setHandleEdges(true);

        // add layer and position
        globeControl.addLayer(baseLayer);
        globeControl.animatePositionGeo(-3.6704803, 40.5023056, 5, 1.0);
        globeControl.setKeepNorthUp(true);
        globeControl.setZoomLimits(0.1,1);

        final String url = "https://raw.githubusercontent.com/jdomingu/ThreeGeoJSON/master/test_geojson/countries_states.geojson";
        GeoJsonHttpTask task = new GeoJsonHttpTask(globeControl);
        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, url);
        // Set controller to be gesture delegate.
        // Needed to allow selection.
        globeControl.gestureDelegate = this;

        // Insert Markers
        insertMarkers();
    }


    @Override
    public void userDidSelect(GlobeController globeControl, SelectedObject[] selObjs, Point2d loc, Point2d screenLoc) {
        loc.setValue(130,45);
        d("userDidSelect", loc.toString()); //testing
        d("userDidSelect", screenLoc.toString()); //testing
        d("userDidSelect", selObjs.toString()); //testing
        String msg = "Selected feature count: " + selObjs.length;
        for (SelectedObject obj : selObjs) {
            // GeoJSON
            if (obj.selObj instanceof VectorObject) {
                VectorObject vectorObject = (VectorObject) obj.selObj;
                d("userDidSelect", vectorObject.toString()); //testing
                AttrDictionary attributes = vectorObject.getAttributes();
                d("userDidSelect", attributes.toString()); //testing
                String adminName = attributes.getString("admin");
                msg += "\nVector Object: " + adminName + loc.getX() + ", " + loc.getY();
            }
        }

        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
    }

    private void insertMarkers() {
        MarkerInfo markerInfo = new MarkerInfo();
        Bitmap icon = BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.ic_marker);
        Point2d markerSize = new Point2d(144, 144);

        // Moskow - Москва
        ScreenMarker moskow = new ScreenMarker();
        moskow.loc = Point2d.FromDegrees(37.616667, 55.75); // Longitude, Latitude
        moskow.image = icon;
        moskow.size = markerSize;

        globeControl.addScreenMarker(moskow, markerInfo, MaplyBaseController.ThreadMode.ThreadAny);
    }

}
