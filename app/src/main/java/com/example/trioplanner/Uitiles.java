package com.example.trioplanner;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Uitiles {
    public static final String TAG = "hproj";


    /**
     *
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
