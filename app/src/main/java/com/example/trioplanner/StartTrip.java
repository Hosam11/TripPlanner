package com.example.trioplanner;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;

import com.example.trioplanner.Services.FloatingService;

public class StartTrip {
    private static final int SYSTEM_ALERT_WINDOW_PERMISSION = 2084;
    public void askPermission(Context context) {
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:" + context.getPackageName()));
        ////startActivityForResult(intent, SYSTEM_ALERT_WINDOW_PERMISSION);
    }
    public void startMap(Context context,long lat,long lon){
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Open Google Maps ?")
                .setCancelable(true)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String latitude = String.valueOf(lat); //the lat of destination
                        String longitude = String.valueOf(lon); //long of destination
                        Uri intentUri = Uri.parse("google.navigation:q="+latitude+","+longitude);
                        Intent mapIntent = new Intent(Intent.ACTION_VIEW,intentUri);
                        mapIntent.setPackage("com.google.android.apps.maps");
                        try{
                            if(mapIntent.resolveActivity(context.getPackageManager())!=null){
                                context.startActivity(mapIntent);
                            }

                        }catch (NullPointerException e){
                            Log.e("MAP", "map error !! " );
                            Toast.makeText(context.getApplicationContext(),"Couldn't open map!! ",Toast.LENGTH_LONG).show();
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
    public void viewFloatingIcon(Context context){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            context.startService(new Intent(context, FloatingService.class));
           //// finish();
        } else if (Settings.canDrawOverlays(context)) {
            context.startService(new Intent(context, FloatingService.class));
          ////  finish();
        } else {
            askPermission(context);
            Toast.makeText(context, "we Need permission to display your notes in floating view", Toast.LENGTH_SHORT).show();
        }
    }

}
