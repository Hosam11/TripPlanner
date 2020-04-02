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
    private NotificationManagerCompat nManager;
    Context c;
    Trip mTrip;


    String TAG = "final";
    @Override
    public void onReceive(Context context, Intent intent) {
        this.c=context;
        mTrip = (Trip) intent.getSerializableExtra(Uitiles.KEY_PASS_TRIP);
        sendNotification(mTrip.getName());
        Log.i(TAG, "AlertReceiver >> onReceive: "+ mTrip.toString());
    }
    void sendNotification(String title) {
        Log.i(TAG, " AlertReceiversendNotification: ");
        nManager = NotificationManagerCompat.from(c);
       // mTrip = (Trip) intent.getSerializableExtra(Uitiles.KEY_PASS_TRIP);
        //edit this to open  google maps intent
        //add content intent to open trip details and start trip
        Intent start = new Intent(c,StartTripActivity.class);
        start.putExtra(KEY_PASS_TRIP, mTrip);
        PendingIntent startP = PendingIntent.getActivity(c, 0, start, 0);
        Intent snooze = new Intent("com.example.snooze");
        PendingIntent snoozeP = PendingIntent.getBroadcast(c, 0, snooze, PendingIntent.FLAG_UPDATE_CURRENT);
        Intent cancel = new Intent("com.example.cancel");
        cancel.putExtra(KEY_PASS_TRIP, mTrip);
        PendingIntent cancelP = PendingIntent.getBroadcast(c,
                0, cancel, PendingIntent.FLAG_CANCEL_CURRENT);
        @SuppressLint("ResourceAsColor") Notification notification= new NotificationCompat.Builder(c,"ChannelID").
                setContentTitle(title).
                setContentText("Do you want to start your trip now ?")
                .setSmallIcon(R.drawable.ic_trip)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_ALARM)
                //.setContentIntent(contentIntent)
                .addAction(R.drawable.ic_check,"Start",startP)
                .addAction(R.drawable.ic_snooze,"Snooze",snoozeP)
                .addAction(R.drawable.ic_cancel,"Cancel",cancelP)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .setColor(R.color.colorAccent)
                .setDefaults(Notification.DEFAULT_ALL)
                .build();
        nManager.notify(1,notification);


    }
}
