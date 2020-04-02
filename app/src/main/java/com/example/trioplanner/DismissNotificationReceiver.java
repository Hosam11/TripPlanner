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
        editTripPresenter = new HomePresenterImpl(new FirebaseModelImpl(), this);
        mTrip.setStatus(Uitiles.STATUS_CANCELED);
        editTripPresenter.onUpdateTrip(mTrip.getId(), mTrip);
        NotificationManagerCompat.from(context).cancel(1);
    }

    @Override
    public void onUpdateTripSuccrss(String state) {

    }
}
