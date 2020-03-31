package com.example.trioplanner.FirebaseDBOperation;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.trioplanner.data.Trip;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.trioplanner.Uitiles.TAG;


public class OfflinebeFirebase {

    List<Trip> tripsReturnedOffline;

    FirebaseAuth mAuth;

    public OfflinebeFirebase() {
        tripsReturnedOffline = new ArrayList<>();
        mAuth = FirebaseAuth.getInstance();
        DatabaseReference scoresRef = FirebaseDatabase.getInstance()
                .getReference(mAuth.getUid());

        scoresRef.keepSynced(true);

        Offline offline = new Offline();

        scoresRef.addChildEventListener(offline);

        scoresRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.i(TAG, "<< OfflinebeFirebase >> onDataChange:  ");
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    tripsReturned.add(snapshot.getValue(Trip.class));
                       Log.i(TAG, "<OFF>onDataChange: tripName  >> " +snapshot.getValue(Trip.class).getName());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //DatabaseReference scoresRef2 = FirebaseDatabase.getInstance().getReference(mAuth.getUid());
        DatabaseReference presenceRef = FirebaseDatabase.getInstance()
                .getReference("disconnectmessage");
        // Write a string when this client loses connection
        presenceRef.onDisconnect().setValue("I disconnected!");

        presenceRef.onDisconnect().removeValue((error, reference) -> {
            if (error != null) {
                Log.d(TAG, "could not establish onDisconnect event:" +
                        error.getMessage());
            }
        });

        DatabaseReference connectedRef = FirebaseDatabase.getInstance()
                .getReference(".info/connected");
        connectedRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean connected = snapshot.getValue(Boolean.class);
                if (connected) {
                    Log.i(TAG, "OfflinebeFirebase >> connected");
                } else {
                    Log.i(TAG, "OfflinebeFirebase >> not connected");

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i(TAG, "OfflinebeFirebase >> Listener was cancelled");
            }
        });

    }


    class Offline implements ChildEventListener{
        @Override
        public void onChildAdded(@NonNull DataSnapshot dataSnapshot,
                                 @androidx.annotation.Nullable String s) {
         /*   Log.i(TAG, "#<<OffFB>>  Offline >> Key >> " +
                    dataSnapshot.getKey() +
                    " -- value >> " +
                    dataSnapshot.getValue());*/

            Trip trip = dataSnapshot.getValue(Trip.class);
            Log.i("#<<OffFB>> Offline",
                    trip.getId()+"/"+trip.getName());

           // tripsReturnedOffline.clear();
            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
               // tripsReturnedOffline.add(snapshot.getValue(Trip.class));
            }

//            Log.i(TAG, "OfflinebeFB>> Offline >> onChildAdded " +
//                    " listSize() >> " + tripsReturnedOffline.size());
//
        }

        @Override
        public void onChildChanged(@NonNull DataSnapshot dataSnapshot,
                                   @androidx.annotation.Nullable String s) {
            Log.i(TAG, "OfflinebeFB>> Offline >>  onChildChanged: ");
        }

        @Override
        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
            Log.i(TAG, "OfflinebeFirebase >>  Offline >> onChildRemoved: ");
        }

        @Override
        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @androidx.annotation.Nullable String s) {
            Log.i(TAG, "OfflinebeFirebase >> Offline >> onChildMoved: ");
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            Log.i(TAG, "OfflinebeFirebase >> Offline >> onCancelled: ");
        }
    }
}
