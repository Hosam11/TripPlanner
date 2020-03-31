package com.example.trioplanner.FirebaseDBOperation;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.trioplanner.data.Trip;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class FirebaseModelImpl implements HomeContract.FirebaseModel {

    static {
       // FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
    private static final String TAG = "hproj";

    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference dbReference;
    String id;

    // need to assgin id to trip before save it in fb
    List<Trip> tripsReturned;

    SaveTripListener saveTripListener;
    GetAllTripLisnter getAllTripLisnter;

    OfflinebeFirebase offline = new OfflinebeFirebase();

    public FirebaseModelImpl() {



        tripsReturned = new ArrayList<>();

        mAuth = FirebaseAuth.getInstance();
        if (database == null) {
          //  database.getInstance().setPersistenceEnabled(true);
            database = FirebaseDatabase.getInstance();

            Log.i(TAG, "FirebaseModelImpl: >>  mAuth.getUid" + mAuth.getUid());
            // TODO replace "hossam" with  {mAuth.getUid()} when login activity finished

            dbReference = database.getReference("hossam");
        }

        offline.keepSynced();
        offline.queryRecentScores();
        offline.onDisconnect();
        offline.getConnectionState();

        dbReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.i(TAG, "getAllTripLisnter >> onDataChange: ");
                // it's important to clear the list before retrive data so can n't appand to list
                // and return to view a huge elements
                tripsReturned.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    tripsReturned.add(snapshot.getValue(Trip.class));
                    //   Log.i(TAG, "onDataChange: tripNa/**/me  >> " +snapshot.getValue(Trip.class).getName());
                }

                if (getAllTripLisnter != null) {
                    Log.i(TAG, "onDataChange*getAllTripLisnter != null* ");
                    getAllTripLisnter.onGetAllTripsComplete(tripsReturned);

                } else if (saveTripListener != null) {
                    Log.i(TAG, "onDataChange*saveTripListener != null* ");
                    saveTripListener.onFinishedSaved("Success");
                }
                Log.i(TAG, "FirebaseModelImpl >> onDataChange: " + tripsReturned.size());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.i(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        });

    }

    /**
     * save single trip to db
     *
     * @param saveTripListener : ref of that interface so i can use it in the callback method
     *                         *                             onDataChanged to call from it method inside presenter when save in fb
     * @param trip
     */
    @Override
    public void saveTripToFB(SaveTripListener saveTripListener, Trip trip) {
        // save trip in data base and when finised
        // >> send response code to view through presenter
        Log.i(TAG, "getAllTripLisnter saveTripToFB: ");
        id = dbReference.push().getKey();
        trip.setId(id);
//        trip.set
        Task saveTask = dbReference.child(id).setValue(trip);
        this.saveTripListener = saveTripListener;

        saveTask.addOnSuccessListener(o -> {
            Log.i(TAG, "addOnSuccessListener: ");
        });
        
        saveTask.addOnFailureListener(e -> {
            Log.i(TAG, "addOnFailureListener: ");
        });
    }
    

    /**
     * get all trips
     *
     * @param getAllTripLisnter: ref of that interface so i can use it in the callback method
     *                           onDataChanged to call from it method inside presenter when save in fb
     */
    @Override
    public void getAllTrips(GetAllTripLisnter getAllTripLisnter) {
        Log.i(TAG, "FirebaseModelImpl >> getAllTrips: ");
        this.getAllTripLisnter = getAllTripLisnter;
        //  getAllTripLisnter.onGetAllTripsComplete(trips);
    }

    @Override
    public void deleteTrip(String id) {
        dbReference.child(id).removeValue();

    }

    @Override
    public void updateTrip(String id, Trip trip) {

        Task updateTask = dbReference.child("/" + id).setValue(trip);
        updateTask.addOnCompleteListener(o -> {
            Log.i(TAG, "ClassName updateTrip: "+ getClass().getSimpleName());
            Log.i(getClass().getSimpleName(), " >> updateTrip: ");
        });

    }


}
