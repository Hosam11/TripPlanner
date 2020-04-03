package com.example.trioplanner;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationManagerCompat;

import com.example.trioplanner.FirebaseDBOperation.FirebaseModelImpl;
import com.example.trioplanner.FirebaseDBOperation.HomeContract;
import com.example.trioplanner.FirebaseDBOperation.HomePresenterImpl;
import com.example.trioplanner.data.Trip;

public class DismissNotificationReceiver extends BroadcastReceiver implements HomeContract.EditTripView
{

    Trip mTrip;
    HomeContract.HomePresenter editTripPresenter;


    @Override
    public void onReceive(Context context, Intent intent) {
        //dismiss notification
         mTrip = (Trip) intent.getSerializableExtra(Uitiles.KEY_PASS_TRIP);
        if (mTrip == null) {
            mTrip.setId(intent.getStringExtra(Uitiles.ID));
            mTrip.setName(intent.getStringExtra(Uitiles.NAME));
            mTrip.setStartLoc(intent.getStringExtra(Uitiles.START_LOC));
            mTrip.setEndLoc(intent.getStringExtra(Uitiles.END_LOC));
            mTrip.setDate(intent.getStringExtra(Uitiles.DATA));
            mTrip.setTime(intent.getStringExtra(Uitiles.TIME));
            mTrip.setType(intent.getStringExtra(Uitiles.TYPE));
            mTrip.setNotes(intent.getStringExtra(Uitiles.NOTES));
            mTrip.setStatus(intent.getStringExtra(Uitiles.STATUS));
            mTrip.setIsSavedOnline(intent.getStringExtra(Uitiles.IS_SAVED_ONLINE));
            mTrip.setLatLngString1(intent.getStringExtra(Uitiles.LAT_LNG_STRING_1));
            mTrip.setLatLngString2(intent.getStringExtra(Uitiles.LAT_LNG_STRING_2));
        }
        editTripPresenter = new HomePresenterImpl(new FirebaseModelImpl(), this);
        mTrip.setStatus(Uitiles.STATUS_CANCELED);
        editTripPresenter.onUpdateTrip(mTrip.getId(), mTrip);
        NotificationManagerCompat.from(context).cancel(1);
    }

    @Override
    public void onUpdateTripSuccrss(String state) {

    }
}
