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
