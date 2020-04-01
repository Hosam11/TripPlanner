package com.example.trioplanner;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationManagerCompat;

import java.util.Calendar;

public class SnoozeReceiver extends BroadcastReceiver {
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onReceive(Context context, Intent intent) {
        //dismiss notification
        NotificationManagerCompat.from(context).cancel(1);
        //setting calender
        Calendar c = Calendar.getInstance();
        c.set(Calendar.MINUTE,5);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent alertIntent = new Intent(context,AlertReceiver.class);
        PendingIntent pi =  PendingIntent.getBroadcast(context,1,alertIntent,0);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),pi);
        //alarmManager.cancel(pi);
    }
}
