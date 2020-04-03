package com.example.trioplanner;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.trioplanner.data.Trip;

import static com.example.trioplanner.Uitiles.KEY_PASS_TRIP;

public class AlertReceiver extends BroadcastReceiver {
    Context c;
    Trip mTrip = new Trip();
    String TAG = "final";
    private NotificationManagerCompat nManager;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.c = context;
//        Bundle bundle = intent.getExtras();
//        mTrip = (Trip) bundle.getSerializable(KEY_PASS_TRIP);
//        mTrip = (Trip) intent.getSerializableExtra(Uitiles.KEY_PASS_TRIP);
//        String s  = intent.getStringExtra(Uitiles.KEY_PASS_TRIP);
        // id - name - startLoc - endLoc - date - time - type - notes -status -  isSavedOnline
        // LatLngString1 -LatLngString2
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
//        Log.i(TAG, "onReceive: s >> " + s);
        Log.i(TAG, "AlertReceiver >> onReceive: mTrip" + mTrip.toString());
        sendNotification(mTrip);

//        sendNotification(mTrip.getName());
    }

    void sendNotification( Trip trip) {
        Log.i(TAG, " AlertReceiversendNotification: ");
        nManager = NotificationManagerCompat.from(c);
        // mTrip = (Trip) intent.getSerializableExtra(Uitiles.KEY_PASS_TRIP);
        //edit this to open  google maps intent
        //add content intent to open trip details and start trip
        Intent start = new Intent(c, StartTripActivity.class);
        start.putExtra(Uitiles.ID, trip.getId());
        start.putExtra(Uitiles.NAME, trip.getName());
        start.putExtra(Uitiles.START_LOC, trip.getStartLoc());
        start.putExtra(Uitiles.END_LOC, trip.getEndLoc());
        start.putExtra(Uitiles.DATA, trip.getDate());
        start.putExtra(Uitiles.TIME, trip.getTime());
        start.putExtra(Uitiles.TYPE, trip.getType());
        start.putExtra(Uitiles.NOTES, trip.getNotes());
        start.putExtra(Uitiles.IS_SAVED_ONLINE, trip.getIsSavedOnline());
        start.putExtra(Uitiles.LAT_LNG_STRING_1, trip.getLatLngString1());
        start.putExtra(Uitiles.LAT_LNG_STRING_2, trip.getLatLngString2());
//        start.putExtra(KEY_PASS_TRIP, mTrip);
        PendingIntent startP = PendingIntent.getActivity(c, 0, start, 0);
        Intent snooze = new Intent("com.example.snooze");
        PendingIntent snoozeP = PendingIntent.getBroadcast(c, 0, snooze, PendingIntent.FLAG_UPDATE_CURRENT);
        Intent cancel = new Intent("com.example.cancel");
        cancel.putExtra(Uitiles.ID, trip.getId());
        cancel.putExtra(Uitiles.NAME, trip.getName());
        cancel.putExtra(Uitiles.START_LOC, trip.getStartLoc());
        cancel.putExtra(Uitiles.END_LOC, trip.getEndLoc());
        cancel.putExtra(Uitiles.DATA, trip.getDate());
        cancel.putExtra(Uitiles.TIME, trip.getTime());
        cancel.putExtra(Uitiles.TYPE, trip.getType());
        cancel.putExtra(Uitiles.NOTES, trip.getNotes());
        cancel.putExtra(Uitiles.IS_SAVED_ONLINE, trip.getIsSavedOnline());
        cancel.putExtra(Uitiles.LAT_LNG_STRING_1, trip.getLatLngString1());
        cancel.putExtra(Uitiles.LAT_LNG_STRING_2, trip.getLatLngString2());
//        cancel.putExtra(KEY_PASS_TRIP, mTrip);
        PendingIntent cancelP = PendingIntent.getBroadcast(c,
                0, cancel, PendingIntent.FLAG_CANCEL_CURRENT);
        @SuppressLint("ResourceAsColor") Notification notification = new NotificationCompat.Builder(c, "ChannelID").
                setContentTitle(mTrip.getName()).
                setContentText("Do you want to start your trip now ?")
                .setSmallIcon(R.drawable.ic_trip)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_ALARM)
                //.setContentIntent(contentIntent)
                .addAction(R.drawable.ic_check, "Start", startP)
                .addAction(R.drawable.ic_snooze, "Snooze", snoozeP)
                .addAction(R.drawable.ic_cancel, "Cancel", cancelP)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .setColor(R.color.colorAccent)
                .setDefaults(Notification.DEFAULT_ALL)
                .build();
        nManager.notify(1, notification);


    }
}
