package com.example.trioplanner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import com.example.trioplanner.Services.FloatingService;

public class StartTripActivity extends AppCompatActivity {
    private static final int SYSTEM_ALERT_WINDOW_PERMISSION = 2084;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //todo get lat and long from intent
        setContentView(R.layout.activity_start_trip);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Open Google Maps ?")
                .setCancelable(true)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //todo : add lat and long of notification
                        String latitude = String.valueOf(30.044281); //the lat of destination
                        String longitude = String.valueOf(31.340002); //long of destination
                        Uri intentUri = Uri.parse("google.navigation:q="+latitude+","+longitude);
                        Intent mapIntent = new Intent(Intent.ACTION_VIEW,intentUri);
                        mapIntent.setPackage("com.google.android.apps.maps");
                        try{
                            if(mapIntent.resolveActivity(getPackageManager())!=null){
                                startActivity(mapIntent);
                            }

                        }catch (NullPointerException e){
                            Log.e("MAP", "map error !! " );
                            Toast.makeText(getApplicationContext(),"Couldn't open map!! ",Toast.LENGTH_LONG).show();
                        }
                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                            startService(new Intent(getApplicationContext(), FloatingService.class));
                            finish();
                        } else if (Settings.canDrawOverlays(StartTripActivity.this)) {
                            startService(new Intent(getApplicationContext(), FloatingService.class));
                            finish();
                        } else {
                            askPermission();
                            Toast.makeText(getApplicationContext(), "we Need permission to display your notes in floating view", Toast.LENGTH_SHORT).show();
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
}
