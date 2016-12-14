package org.muferobotics.olympics.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.muferobotics.olympics.R;

import java.util.ArrayList;

public class MapsActivity extends BaseActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    String uzumcu;
    String sport;
    String faculty;
    private double[] latList = {40.986708, 40.986488, 40.987108, 40.985895};
    private double[] langList = {29.051639, 29.052387, 29.053042, 29.052937};

    @Override
    protected int getContentViewLayoutResId() {
        return R.layout.activity_maps;
    }

    @Override
    protected void setUpToolbar(Toolbar toolbar) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enableToolbarNavigation();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        uzumcu = getString(R.string.activity_maps_uzumcu);
        sport = getString(R.string.activity_maps_sport);
        faculty = getString(R.string.activity_maps_faculty);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng marmara = new LatLng(40.9873405, 29.0520495);

        ArrayList<LatLng> t = new ArrayList<LatLng>();
        t.add(new LatLng(40.986409, 29.051660));
        t.add(new LatLng(40.986358, 29.051851));
        t.add(new LatLng(40.985210, 29.051676));
        t.add(new LatLng(40.985123, 29.052430));

        ArrayList<LatLng> y = new ArrayList<LatLng>();
        y.add(new LatLng(40.986524, 29.052186));
        y.add(new LatLng(40.986550, 29.051902));
        y.add(new LatLng(40.985228, 29.051719));
        y.add(new LatLng(40.985143, 29.052406));


        MarkerOptions marker = new MarkerOptions().position(marmara).title(getString(R.string.activity_maps_welcome));
        marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.logo));
        mMap.addMarker(marker);

        String[] title = {uzumcu, sport, "MUFE Robotics"};
        //Add marker
        for (int i = 0; i < title.length; i++) {
            mMap.addMarker(new MarkerOptions().position(
                    new LatLng(latList[i], langList[i]))
                    .title(title[i]));
        }
        mMap.addPolyline(new PolylineOptions()
                .addAll(t)
                .width(10)
                .color(Color.RED));
        mMap.addPolyline(new PolylineOptions()
                .addAll(y)
                .width(10)
                .color(Color.GREEN));
        CameraPosition cameraPosition = new CameraPosition.Builder().target(
                marmara).zoom(18).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

    }
}
