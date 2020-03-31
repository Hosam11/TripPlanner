package com.example.trioplanner.FirebaseDBOperation;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.Nullable;


public class OfflinebeFirebase {

    String TAG = "hoff";

    /**
     * Firebase apps automatically handle temporary network interruptions.
     * Cached data is available while offline and Firebase resends any writes
     * when network connectivity is restored.
     * When you enable disk persistence, your app writes the data locally to the device
     * so your app can maintain state while offline,
     * even if the user or operating system restarts the app.
     * You can enable disk persistence with just one line of code.
     */

    /**
     * The Firebase Realtime Database synchronizes and stores a local copy of the data
     * for active listeners. In addition, you can keep specific locations in sync.
     */
    public void keepSynced() {
        // [START rtdb_keep_synced]
        DatabaseReference scoresRef = FirebaseDatabase.getInstance().getReference("hossam");
        scoresRef.keepSynced(true);
        // [END rtdb_keep_synced]

        // [START rtdb_undo_keep_synced]

        //scoresRef.keepSynced(false);
        // [END rtdb_undo_keep_synced]
        Log.i(TAG, "keepSynced: ");
    }

    public void queryRecentScores() {
        Log.i(TAG, "first of queryRecentScores: ");
        // [START rtdb_query_recent_scores]
        DatabaseReference scoresRef = FirebaseDatabase.getInstance().getReference("hossam");
        Offline offline = new Offline();

        scoresRef.addChildEventListener(offline);
      }

    public void onDisconnect() {
        // [START rtdb_on_disconnect_set]
        DatabaseReference presenceRef = FirebaseDatabase.getInstance()
                .getReference("disconnectmessage");
        // Write a string when this client loses connection
        presenceRef.onDisconnect().setValue("I disconnected!");
        // [END rtdb_on_disconnect_set]

        // [START rtdb_on_disconnect_remove]
        presenceRef.onDisconnect().removeValue((error, reference) -> {
            if (error != null) {
                Log.i(TAG, "could not establish onDisconnect event:" + error.getMessage());
            }
        });
        Log.i(TAG, "onDisconnect: ");
    }

    public void getConnectionState() {
        // [START rtdb_listen_connected]
        DatabaseReference connectedRef = FirebaseDatabase.getInstance().getReference(".info/connected");
        connectedRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean connected = snapshot.getValue(Boolean.class);
                if (connected) {
                    Log.i(TAG, "connected");
                } else {
                    Log.i(TAG, "not connected");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i(TAG, "Listener was cancelled");
            }
        });
        // [END rtdb_listen_connected]
        Log.i(TAG, "getConnectionState: ");
    }



    class Offline implements ChildEventListener{

        @Override
        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @androidx.annotation.Nullable String s) {
            Log.i(TAG, "The " + dataSnapshot.getKey() + " trips's score is " + dataSnapshot.getValue());
            Log.i(TAG, "onChildAdded: ");
        }

        @Override
        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @androidx.annotation.Nullable String s) {

        }

        @Override
        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

        }

        @Override
        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @androidx.annotation.Nullable String s) {

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    }
}
