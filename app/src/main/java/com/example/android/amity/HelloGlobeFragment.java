package com.example.android.amity;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mousebird.maply.AttrDictionary;
import com.mousebird.maply.ComponentObject;
import com.mousebird.maply.GlobeController;
import com.mousebird.maply.GlobeMapFragment;
import com.mousebird.maply.MaplyBaseController;
import com.mousebird.maply.MarkerInfo;
import com.mousebird.maply.Point2d;
import com.mousebird.maply.QuadImageTileLayer;
import com.mousebird.maply.RemoteTileInfo;
import com.mousebird.maply.RemoteTileSource;
import com.mousebird.maply.ScreenMarker;
import com.mousebird.maply.SelectedObject;
import com.mousebird.maply.SphericalMercatorCoordSystem;
import com.mousebird.maply.VectorInfo;
import com.mousebird.maply.VectorObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class HelloGlobeFragment extends GlobeMapFragment {

    ComponentObject selectedComponentObject;
    ComponentObject selectedMarkerComponent;

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


        final String url = "https://raw.githubusercontent.com/johan/world.geo.json/master/countries.geo.json";
        GeoJsonHttpTask task = new GeoJsonHttpTask(globeControl);
        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, url);
        // Set controller to be gesture delegate.
        // Needed to allow selection.
        globeControl.gestureDelegate = this;

        // Insert Markers
        insertMarkers();
    }

    public class MarkerProperties {
        public String city;
        //public String subject;
    }

    public static void updateMap()
    {
        //will include code from insertMarkers
    }

//    public static int[] newMarker()
//    {
//        //int[] coordinates = cities.get();
//        //String city = ;
//        markerCoordinates.add(coordinates);
//        markerCoordinates.remove(0);
//        Log.v("city coordinates", coordinates[0] + coordinates[1] + "");
//  }

    private void insertMarkers() {

        List<ScreenMarker> markers = new ArrayList<>();

        MarkerInfo markerInfo = new MarkerInfo();
        Bitmap icon = BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.ic_marker);
        Point2d markerSize = new Point2d(144, 144);

        // Manaus
        MarkerProperties properties = new MarkerProperties();
        properties.city = "Manaus";
        ScreenMarker ms = new ScreenMarker();
        ms.loc = Point2d.FromDegrees(-60.0217, -3.1190); // Longitude, Latitude
        ms.image = icon;
        ms.size = markerSize;
        ms.selectable = true;
        ms.userObject = properties;
        markers.add(ms);

        //  Kansas City
        properties = new MarkerProperties();
        properties.city = "Kansas City";
        ScreenMarker kc = new ScreenMarker();
        kc.loc = Point2d.FromDegrees(-94.5786, 39.0997);
        kc.image = icon;
        kc.size = markerSize;
        kc.selectable = true;
        kc.userObject = properties;
        markers.add(kc);

        // Alice Springs (Australia)
        properties = new MarkerProperties();
        properties.city = "Alice Springs";
        ScreenMarker as = new ScreenMarker();
        as.loc = Point2d.FromDegrees(133.8807, -23.6980);
        as.image = icon;
        as.size = markerSize;
        as.selectable = true;
        as.userObject = properties;
        markers.add(as);

        // Prague
        properties = new MarkerProperties();
        properties.city = "Prague";
        ScreenMarker pg = new ScreenMarker();
        pg.loc = Point2d.FromDegrees(14.4378, 50.0755);
        pg.image = icon;
        pg.size = markerSize;
        pg.selectable = true;
        pg.userObject = properties;
        markers.add(pg);

        // Nagpur
        properties = new MarkerProperties();
        properties.city = "Nagpur";
        ScreenMarker np = new ScreenMarker();
        np.loc = Point2d.FromDegrees(79.0882, 21.1458);
        np.image = icon;
        np.size = markerSize;
        np.selectable = true;
        np.userObject = properties;
        markers.add(np);

        // Lodja
        properties = new MarkerProperties();
        properties.city = "Lodja";
        ScreenMarker lg = new ScreenMarker();
        lg.loc = Point2d.FromDegrees(23.5967, -3.5245);
        lg.image = icon;
        lg.size = markerSize;
        lg.selectable = true;
        lg.userObject = properties;
        markers.add(lg);

        // Add your markers to the map controller.
        ComponentObject markersComponentObject = globeControl.addScreenMarkers(markers, markerInfo, MaplyBaseController.ThreadMode.ThreadAny);

        //remove markersComponentObject
        //globeControl.removeObject(markersComponentObject, MaplyBaseController.ThreadMode.ThreadAny);
    }

    @Override
    public void userDidSelect(GlobeController globeControl, SelectedObject[] selObjs, Point2d loc, Point2d screenLoc) {
        //d("userDidSelect", loc.toString()); //testing
        //d("userDidSelect", screenLoc.toString()); //testing
        //d("userDidSelect", selObjs.toString()); //testing
        String msg = "";
        for (SelectedObject obj : selObjs) {
            // GeoJSON
            if (obj.selObj instanceof VectorObject) {
                VectorObject vectorObject = (VectorObject) obj.selObj;
                //d("userDidSelect", "this is it1"+vectorObject.centroid().toString()); //testing
                //d("userDidSelect", "this is it2"+vectorObject.pointInside(loc)); //testing
                AttrDictionary attributes = vectorObject.getAttributes();
               // attributes.setString("name","India");
                //d("userDidSelect", "this is it 3"+ attributes.toString()); //testing
                String adminName = attributes.getString("name");
                msg += adminName;
                drawVectorObjectAsSelected(vectorObject);
                MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.button);
                mp.start();
                Intent postIntent = new Intent(getActivity(), TopPostsActivity.class);
                startActivity(postIntent);
            }
            // Screen Marker
            else if (obj.selObj instanceof ScreenMarker) {
                ScreenMarker screenMarker = (ScreenMarker) obj.selObj;
                MarkerProperties properties = (MarkerProperties) screenMarker.userObject;
                //d("userDidSelect", "test" + properties.city);
                msg += "\n" + properties.city;
                drawScreenMarkerAsSelected(screenMarker);
                MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.button);
                mp.start();
                Intent postIntent = new Intent(getActivity(), PostActivity.class);
                startActivity(postIntent);
            }
        }

        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
    }

    public void drawVectorObjectAsSelected(VectorObject vectorObject) {
        if (selectedComponentObject != null) {
            globeControl.removeObject(selectedComponentObject, MaplyBaseController.ThreadMode.ThreadAny);
        }
        VectorInfo vectorInfo = new VectorInfo();
        vectorInfo.setColor(Color.argb(0, 0, 126, 175)); // sig. color
        vectorInfo.setLineWidth(10.f);
        vectorInfo.setDrawPriority(Integer.MAX_VALUE); // Make sure it draws on top of unselected vector
        selectedComponentObject = globeControl.addVector(vectorObject, vectorInfo, MaplyBaseController.ThreadMode.ThreadAny);
    }

    public void drawScreenMarkerAsSelected(ScreenMarker screenMarker) {
        if (selectedMarkerComponent != null) {
            globeControl.removeObject(selectedMarkerComponent, MaplyBaseController.ThreadMode.ThreadAny);
        }
        MarkerInfo markerInfo = new MarkerInfo();
        markerInfo.setDrawPriority(Integer.MAX_VALUE);
        Bitmap icon = BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.ic_selected_marker);
        Point2d markerSize = new Point2d(144, 144);
        screenMarker.image = icon;
        screenMarker.size = markerSize;
        screenMarker.selectable = true;
        selectedMarkerComponent= globeControl.addScreenMarker(screenMarker, markerInfo, MaplyBaseController.ThreadMode.ThreadAny);
    }
}
