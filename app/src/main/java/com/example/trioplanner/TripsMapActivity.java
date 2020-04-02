package com.example.trioplanner;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;

import com.example.trioplanner.data.Trip;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.Random;

public class TripsMapActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trips_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //add lines and markers and move camera
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        ArrayList<Trip> trips = (ArrayList<Trip>) args.getSerializable("ARRAYLIST");
        LatLng trip1 = new LatLng(Long.parseLong(Location.convert(trips.get(0).getStart().getLatitude(), Location.FORMAT_DEGREES))
                , Long.parseLong(Location.convert(trips.get(0).getStart().getLongitude(), Location.FORMAT_DEGREES)));
        // mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(trip1,60f));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(trip1));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(6.0f));
        int color = 0;
        int colorRnd;
        for (int i = 0; i < trips.size(); i++) {
            colorRnd = new Random().nextInt(4);
            switch (colorRnd) {
                case 0:
                    color = Color.RED;
                    break;
                case 1:
                    color = Color.YELLOW;
                    break;
                case 2:
                    color = Color.GREEN;
                    break;
                case 3:
                    color = Color.BLUE;
                    break;
                case 4:
                    color = Color.MAGENTA;
                    break;
            }

            LatLng start = new LatLng(Long.parseLong(Location.convert(trips.get(i).getStart().getLatitude(), Location.FORMAT_DEGREES))
                    , Long.parseLong(Location.convert(trips.get(i).getStart().getLongitude(), Location.FORMAT_DEGREES)));
            LatLng end = new LatLng(Long.parseLong(Location.convert(trips.get(i).getStart().getLatitude(), Location.FORMAT_DEGREES))
                    , Long.parseLong(Location.convert(trips.get(i).getStart().getLongitude(), Location.FORMAT_DEGREES)));
            mMap.addPolyline(new PolylineOptions().add(start).add(end).width(4f).color(color));
        }


    }
}
