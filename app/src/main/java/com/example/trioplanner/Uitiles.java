package com.example.trioplanner;

import android.app.AlarmManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

public class Uitiles {
    public static final String TAG = "hproj";
    public static final String SAVED_ONLINE = "t";
    public static final String SAVED_OFFLINE = "f";
    public static final String KEY_PASS_TRIP = "tripPassed";
    public static final String STATUS_UPCOMING = "upcoming";
    public static final String STATUS_CANCELED = "canceled";
    public static final String STATUS_DONE = "done";

    // strings for passing intent bject by keys
    // id - name - startLoc - endLoc - date - time - type - notes -status -  isSavedOnline
    // LatLngString1 -LatLngString2

    public static final String ID = "_id";
    public static final String NAME = "_name";
    public static final String START_LOC = "_startLoc";
    public static final String END_LOC = "_endLoc";
    public static final String DATA = "_date";
    public static final String TIME = "_time";
    public static final String TYPE = "_type";
    public static final String NOTES = "_notes";
    public static final String STATUS = "_status";
    public static final String IS_SAVED_ONLINE = "_isSavedOnline";
    public static final String LAT_LNG_STRING_1 = "_LatLngString1";
    public static final String LAT_LNG_STRING_2 = "_LatLngString2";


    /**
     * @param context: context that called the method
     * @return true if there is a internet connection otherwise false
     */
    public static boolean checkInternetState(Context context) {
        ConnectivityManager cm = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }



    }

    public  static void showCustomDialog(View itemView, String notes, Context context, String dialogeHead) {
        //before inflating the custom alert dialog layout,
        // we will get the current activity viewgroup
        ViewGroup viewGroup = itemView.findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(context)
                .inflate(R.layout.dialoge_layout, viewGroup, false);

        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        //finally creating the alert dialog and displaying it
        AlertDialog alertDialog = builder.create();
        TextView tv = dialogView.findViewById(R.id.tvDialogeNotes);
        tv.setText(notes);
        TextView tvHead = dialogView.findViewById(R.id.dialogeHead);
        tvHead.setText(dialogeHead);
        alertDialog.show();
    }
}
