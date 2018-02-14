package com.example.aspire.piyushbrinder_gmap_resturantapp;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Gmaps extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Double Lati = 43.6504265;
    Double Long = -79.7364565;
    String locTitle = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gmaps);

        Intent intent = getIntent();
        Lati = Double.parseDouble(intent.getStringExtra("lati"));
        Long = Double.parseDouble(intent.getStringExtra("long"));
        locTitle = intent.getStringExtra("title");
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng loc = new LatLng(Lati,Long);
        mMap.addMarker(new MarkerOptions().position(loc).title(locTitle));
         // Move the camera instantly to toronto with a zoom of 15.
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, 20));

        // Zoom in, animating the camera.
        mMap.animateCamera(CameraUpdateFactory.zoomTo(20), 5000, null);
    }
}
