package com.example.trioplanner.roomOps;

import android.content.Context;

import androidx.room.Room;


public class TripClient {
    private Context mCtx;
    private static TripClient mInstance;
    private TripDB tripDB;

    private TripClient(Context mCtx) {
        this.mCtx = mCtx;
        tripDB = Room.databaseBuilder(mCtx,TripDB.class,"trips.db").build();

    }
    public static synchronized TripClient getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new TripClient(mCtx);
        }
        return mInstance;
    }

    public TripDB getTripDB() {
        return tripDB;
    }
    public void closeTripDB(){
        tripDB.close();
    }
}
