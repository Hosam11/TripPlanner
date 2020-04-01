package com.example.trioplanner;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Uitiles {
    public static final String TAG = "hproj";
    public static final String SAVED_ONLINE = "t";
    public static final String SAVED_OFFLINE = "t";
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
}
