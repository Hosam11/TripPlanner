package com.example.trioplanner.FirebaseDBOperation;

import android.util.Log;

import com.example.trioplanner.Uitiles;
import com.example.trioplanner.data.Trip;

import java.util.ArrayList;
import java.util.List;

import static com.example.trioplanner.Uitiles.TAG;

public class DatabaseHelper {

    List<Trip> upcomingTrips;
    List<Trip> historyTrips;

    public DatabaseHelper() {
        upcomingTrips = new ArrayList<>();
        historyTrips = new ArrayList<>();
    }

    /**
     * @param trips : list of trips to divided
     * @param status : status of list that you want to returned
     * @return
     */
    public List<Trip> getSelectedList(List<Trip> trips, String status) {
        for (Trip mTrip : trips) {
            if (mTrip.getStatus().equals(Uitiles.STATUS_UPCOMING)) {
                upcomingTrips.add(mTrip);
            } else {
                historyTrips.add(mTrip);
            }
        }
        if (status.equals(Uitiles.STATUS_UPCOMING)) {
            Log.i(TAG, "DatabaseHelper >> getSelectedList: size? STATUS_UPCOMING >> "
                    + upcomingTrips.size() );
            return upcomingTrips;
        } else {
            Log.i(TAG, "DatabaseHelper >> getSelectedList: size? STATUS_ANY>> "
                    + historyTrips.size() );
            return historyTrips;
        }
    }

}
