package com.example.trioplanner;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.os.Build;
import java.util.Calendar;
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // Button alt = findViewById(R.id.alarm);
       /* alt.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                //setting calender
                Calendar c = Calendar.getInstance();
               // c.set(Calendar.HOUR_OF_DAY,7);
                c.set(Calendar.MINUTE,1);
               // c.set(Calendar.DAY_OF_MONTH,7);
               // c.set(Calendar.SECOND,1);
                //c.set(Calendar.MONTH,1);
                //c.set(Calendar.YEAR,2020);
                //alarm manager
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                Intent intent = new Intent(MainActivity.this,AlertReceiver.class);
                PendingIntent pi =  PendingIntent.getBroadcast(MainActivity.this,1,intent,0);
                alarmManager.setExact(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),pi);
                //alarmManager.cancel(pi);
            }
    });*/

                               }
}
