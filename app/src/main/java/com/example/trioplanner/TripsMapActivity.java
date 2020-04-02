package com.example.trioplanner;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;

import com.example.trioplanner.FirebaseDBOperation.FirebaseModelImpl;
import com.example.trioplanner.FirebaseDBOperation.HomeContract;
import com.example.trioplanner.FirebaseDBOperation.HomePresenterImpl;
import com.example.trioplanner.data.Trip;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TripsMapActivity extends FragmentActivity implements OnMapReadyCallback, HomeContract.HomeView {
    private GoogleMap mMap;
    List<Trip> tripsList;
    HomeContract.HomePresenter homePresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trips_map);
        homePresenter = new HomePresenterImpl(new FirebaseModelImpl(),
                (HomeContract.HomeView) this);
        tripsList = new ArrayList<>();
        homePresenter.onGetUpcomingTrips();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //add lines and markers and move camera
        //Intent intent = TripsMapActivity.this.getIntent();
        // ArrayList<Trip> trips = (ArrayList<Trip>) intent.getSerializableExtra("trips");

        if (!tripsList.isEmpty()) {
          //  tripsList.get(0).getLatLngString1().split("_")[0];
            LatLng trip1 = new LatLng(Double.parseDouble(tripsList.get(0).getLatLngString1().split("_")[0]),Double.parseDouble(tripsList.get(0).getLatLngString1().split("_")[1]));
            // mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(trip1,60f));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(trip1));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(8.0f));
            int color = 0;
            int colorRnd;
            for (int i = 0; i < tripsList.size(); i++) {
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

                LatLng start = new LatLng(Double.parseDouble(tripsList.get(i).getLatLngString1().split("_")[0]),Double.parseDouble(tripsList.get(i).getLatLngString1().split("_")[1]));
                LatLng end = new LatLng(Double.parseDouble(tripsList.get(i).getLatLngString2().split("_")[0]),Double.parseDouble(tripsList.get(i).getLatLngString2().split("_")[1]));
                mMap.addPolyline(new PolylineOptions().add(start).add(end).width(4f).color(color));
            }


        }
    }
    @Override
    public void getUpcomingTrips(List<Trip> trips) {
        if (tripsList != null) {
            //Log.i(TAG, "Home >> getAllTrips: TripsList !=null ");
            tripsList.clear();
        }
        for (Trip t : trips) {
            tripsList.add(t);
        }

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
