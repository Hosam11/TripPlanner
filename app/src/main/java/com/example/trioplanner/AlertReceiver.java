package com.example.trioplanner;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class AlertReceiver extends BroadcastReceiver {
    private NotificationManagerCompat nManager;
    Context c;
    @Override
    public void onReceive(Context context, Intent intent) {
        this.c=context;
        sendNotification("Trip Name");
    }
    void sendNotification(String title) {
        nManager = NotificationManagerCompat.from(c);
        //edit this to open  google maps intent
        //add content intent to open trip details and start trip
        Intent start = new Intent(c,StartTripActivity.class);
        PendingIntent startP = PendingIntent.getActivity(c, 0, start, 0);
        Intent snooze = new Intent("com.example.snooze");
        PendingIntent snoozeP = PendingIntent.getBroadcast(c, 0, snooze, PendingIntent.FLAG_UPDATE_CURRENT);
        Intent cancel = new Intent("com.example.cancel");
        PendingIntent cancelP = PendingIntent.getBroadcast(c, 0, cancel, PendingIntent.FLAG_CANCEL_CURRENT);
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
