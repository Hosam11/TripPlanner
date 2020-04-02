package com.example.trioplanner;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.trioplanner.FirebaseDBOperation.FirebaseModelImpl;
import com.example.trioplanner.FirebaseDBOperation.HomeContract;
import com.example.trioplanner.FirebaseDBOperation.HomePresenterImpl;
import com.example.trioplanner.Services.FloatingService;
import com.example.trioplanner.data.Trip;

import static com.example.trioplanner.Uitiles.KEY_PASS_TRIP;
import static com.example.trioplanner.Uitiles.TAG;


public class StartTripActivity extends AppCompatActivity implements HomeContract.EditTripView {
    private static final int SYSTEM_ALERT_WINDOW_PERMISSION = 2084;
    Trip mTrip;
    HomeContract.HomePresenter editTripPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //todo get lat and long from intent
        Intent intent = getIntent();
        mTrip = (Trip) intent.getSerializableExtra(Uitiles.KEY_PASS_TRIP);
        editTripPresenter = new HomePresenterImpl(new FirebaseModelImpl(), this);
        mTrip.setStatus(Uitiles.STATUS_DONE);
        editTripPresenter.onUpdateTrip(mTrip.getId(), mTrip);

        setContentView(R.layout.activity_start_trip);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Open Google Maps ?")
                .setCancelable(true)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //todo : add lat and long of notification
                        // String latitude = String.valueOf(30.044281); //the lat of destination
                        // String longitude = String.valueOf(31.340002); //long of destination
                      /*  double lat = Double.parseDouble("30.044281");
                        double lng = Double.parseDouble("31.34000");
                        LatLng latLng = new LatLng(lat, lng);*/

                        String latitude = mTrip.getLatLngString2().split("_")[0]; //the lat of destination
                        String longitude = mTrip.getLatLngString2().split("_")[1]; //long of destination

                        Log.i(TAG, "StartTripActivity >> onClick: lat >> " + latitude + " -- log >> " + longitude);
                        Uri intentUri = Uri.parse("google.navigation:q=" + latitude + "," + longitude);
                        Intent mapIntent = new Intent(Intent.ACTION_VIEW, intentUri);
                        mapIntent.setPackage("com.google.android.apps.maps");
                        Intent intentToService = new Intent(getApplicationContext(),
                                FloatingService.class);

                        intentToService.putExtra(KEY_PASS_TRIP, mTrip);

                        Log.i(TAG, "StartTripActivity >> onClick:  " + mTrip.getName());

                        try {
                            if (mapIntent.resolveActivity(getPackageManager()) != null) {
                                startActivity(mapIntent);
                            }

                        } catch (NullPointerException e) {
                            Log.e("MAP", "map error !! ");
//                            Toast.makeText(getApplicationContext(),
//                                    "Couldn't open map!! ", Toast.LENGTH_LONG).show();
                        }
                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                            startService(intentToService);
                            finish();
                        } else if (Settings.canDrawOverlays(StartTripActivity.this)) {
                            startService(intentToService);
                            finish();
                        } else {
                            askPermission();
//                            Toast.makeText(getApplicationContext(),
//                                    "we Need permission to display your notes in floating view", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void askPermission() {
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:" + getPackageName()));
        startActivityForResult(intent, SYSTEM_ALERT_WINDOW_PERMISSION);
    }

    @Override
    public void onUpdateTripSuccrss(String state) {

    }
}
