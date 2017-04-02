package com.example.android.amity;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mousebird.maply.AttrDictionary;
import com.mousebird.maply.ComponentObject;
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
import com.mousebird.maply.VectorInfo;
import com.mousebird.maply.VectorObject;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.selectable;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;
import static android.util.Log.d;


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


        final String url = "https://raw.githubusercontent.com/superCodeTeam/Amity/master/app/src/main/assets/countries.geo.json";
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

    private void insertMarkers() {

        List<ScreenMarker> markers = new ArrayList<>();

        MarkerInfo markerInfo = new MarkerInfo();
        Bitmap icon = BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.ic_marker);
        Point2d markerSize = new Point2d(144, 144);

        // Moskow - Москва
        MarkerProperties properties = new MarkerProperties();
        properties.city = "Moskow";
        ScreenMarker moskow = new ScreenMarker();
        moskow.loc = Point2d.FromDegrees(37.616667, 55.75); // Longitude, Latitude
        moskow.image = icon;
        moskow.size = markerSize;
        moskow.selectable = true;
        moskow.userObject = properties;
        markers.add(moskow);

        //  Saint Petersburg - Санкт-Петербург
        properties = new MarkerProperties();
        properties.city = "Saint Petersburg";
        ScreenMarker stPetersburg = new ScreenMarker();
        stPetersburg.loc = Point2d.FromDegrees(30.3, 59.95);
        stPetersburg.image = icon;
        stPetersburg.size = markerSize;
        stPetersburg.selectable = true;
        stPetersburg.userObject = properties;
        markers.add(stPetersburg);

        // Novosibirsk - Новосибирск
        properties = new MarkerProperties();
        properties.city = "Novosibirsk";
        ScreenMarker novosibirsk = new ScreenMarker();
        novosibirsk.loc = Point2d.FromDegrees(82.95, 55.05);
        novosibirsk.image = icon;
        novosibirsk.size = markerSize;
        novosibirsk.selectable = true;
        novosibirsk.userObject = properties;
        markers.add(novosibirsk);

        // Yekaterinburg - Екатеринбург
        properties = new MarkerProperties();
        properties.city = "Yekaterinburg";
        ScreenMarker yekaterinburg = new ScreenMarker();
        yekaterinburg.loc = Point2d.FromDegrees(60.583333, 56.833333);
        yekaterinburg.image = icon;
        yekaterinburg.size = markerSize;
        yekaterinburg.selectable = true;
        yekaterinburg.userObject = properties;
        markers.add(yekaterinburg);

        // Nizhny Novgorod - Нижний Новгород
        properties = new MarkerProperties();
        properties.city = "Nizhny Novgorod";
        ScreenMarker nizhnyNovgorod = new ScreenMarker();
        nizhnyNovgorod.loc = Point2d.FromDegrees(44.0075, 56.326944);
        nizhnyNovgorod.image = icon;
        nizhnyNovgorod.size = markerSize;
        nizhnyNovgorod.selectable = true;
        nizhnyNovgorod.userObject = properties;
        markers.add(nizhnyNovgorod);

        // Add your markers to the map controller.
        ComponentObject markersComponentObject = globeControl.addScreenMarkers(markers, markerInfo, MaplyBaseController.ThreadMode.ThreadAny);

        //remove markersComponentObject
        //globeControl.removeObject(markersComponentObject, MaplyBaseController.ThreadMode.ThreadAny);
    }

    @Override
    public void userDidSelect(GlobeController globeControl, SelectedObject[] selObjs, Point2d loc, Point2d screenLoc) {
        d("userDidSelect", loc.toString()); //testing
        d("userDidSelect", screenLoc.toString()); //testing
        d("userDidSelect", selObjs.toString()); //testing
        String msg = "Selected feature count: " + selObjs.length;
        for (SelectedObject obj : selObjs) {
            // GeoJSON
            if (obj.selObj instanceof VectorObject) {
                VectorObject vectorObject = (VectorObject) obj.selObj;
                d("userDidSelect", "this is it1"+vectorObject.centroid().toString()); //testing
                d("userDidSelect", "this is it2"+vectorObject.pointInside(loc)); //testing
                AttrDictionary attributes = vectorObject.getAttributes();
               // attributes.setString("name","India");
                d("userDidSelect", "this is it 3"+ attributes.toString()); //testing
                String adminName = attributes.getString("name");
                msg += "\nVector Object: " + adminName;
                drawVectorObjectAsSelected(vectorObject);
            }
            // Screen Marker
            else if (obj.selObj instanceof ScreenMarker) {
                ScreenMarker screenMarker = (ScreenMarker) obj.selObj;
                MarkerProperties properties = (MarkerProperties) screenMarker.userObject;
                d("userDidSelect", "test" + properties.city);
                msg += "\nScreen Marker: " + properties.city;
                drawScreenMarkerAsSelected(screenMarker);
            }
        }

        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
    }

    public void drawVectorObjectAsSelected(VectorObject vectorObject) {
        if (selectedComponentObject != null) {
            globeControl.removeObject(selectedComponentObject, MaplyBaseController.ThreadMode.ThreadAny);
        }
        VectorInfo vectorInfo = new VectorInfo();
        vectorInfo.setColor(Color.argb(255,255,140,0)); // Gold
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
