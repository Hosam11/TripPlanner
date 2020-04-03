package com.example.trioplanner.FirebaseDBOperation;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.trioplanner.Uitiles;
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

    private static final String TAG = "hproj";

    static {
        //FirebaseDatabase.getInstance().setPersistenceEnabled(false);
    }

    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference dbReference;
    String id;

    //DataChangeListner dataChangeListner;

    // need to assgin id to trip before save it in fb
    List<Trip> tripsReturned;
    List<Trip> rightTrips;

    SaveTripListener saveTripListener;

    UpcomingTripLisnter upcomingTripLisnter;

    UpdateTripLisnter updateTripLisnter;

    HistoryTripsLisntnener historyTripsLisntnener;

    DeleteTripListener deleteTripListener;


    //OfflinebeFirebase offline;

    DatabaseHelper databaseHelper;

    public FirebaseModelImpl() {
        //offline = new OfflinebeFirebase();

        databaseHelper = new DatabaseHelper();
        tripsReturned = new ArrayList<>();
        rightTrips = new ArrayList<>();

        mAuth = FirebaseAuth.getInstance();
        if (database == null) {
            //  database.getInstance().setPersistenceEnabled(true);
            database = FirebaseDatabase.getInstance();

            dbReference = database.getReference(mAuth.getUid());
        }

        // listen for change in firebase
        dbReference.addValueEventListener(new DataChangeListner());
    }

    /**
     * save single trip to db
     *
     * @param saveTripListener : ref of that interface so i can use it in the callback method
     *                         onDataChanged to call from it method inside presenter when save in fb
     * @param trip
     */
    @Override
    public void saveTripToFB(SaveTripListener saveTripListener, Trip trip) {
        // save trip in data base and when finised
        // >> send response code to view through presenter
       // Log.i(TAG, "FirebaseModelImpl >> saveTripToFB: ");

        if (trip.getId() == null) {
            id = dbReference.push().getKey();
            // coz a trip comming wihtout id attribute
            trip.setId(id);
            Log.i(TAG, "FirebaseModelImpl >> saveTripToFB: >> id >> is null" +
                    " >> " + id);
        } else {
            id = trip.getId();
            // coz a trip comming wihtout id attribute
          //  trip.setId(id);
            Log.i(TAG, "FirebaseModelImpl >> saveTripToFB: >> id  not null" +
                    ""  + id );
        }
        // trip.set
        Task saveTask = dbReference.child(id).setValue(trip);
        this.saveTripListener = saveTripListener;

        saveTask.addOnSuccessListener(o -> {
            Log.i(TAG, "FirebaseModelImpl >> addOnSuccessListener: ");
        });
        saveTask.addOnFailureListener(e -> {
            Log.i(TAG, "FirebaseModelImpl >> addOnFailureListener: ");
        });
    }

    /**
     * get all trips
     *
     * @param upcomingTripLisnter: ref of that interface so i can use it in the callback method
     *                             onDataChanged to call from it method inside presenter when save in fb
     */
    @Override
    public void getUpcomingTrips(UpcomingTripLisnter upcomingTripLisnter) {
        // Log.i(TAG, "FirebaseModelImpl >> getAllTrips: ");
        this.upcomingTripLisnter = upcomingTripLisnter;
        //  getAllTripLisnter.onGetAllTripsComplete(trips);
    }

    @Override
    public void getHistroyTrips(HistoryTripsLisntnener historyTripsLisntnener) {
        this.historyTripsLisntnener = historyTripsLisntnener;
    }

    @Override
    public void deleteTrip(DeleteTripListener deleteTripListener, String id) {
        this.deleteTripListener = deleteTripListener;
        dbReference.child(id).removeValue();

    }

    @Override
    public void updateTrip(String id, Trip trip) {
        Task updateTask = dbReference.child("/" + trip.getId()).setValue(trip);
        updateTask.addOnCompleteListener(o -> {
            Log.i(TAG, "ClassName updateTrip: " + getClass().getSimpleName());
            Log.i(getClass().getSimpleName(), " >> updateTrip: ");
        });
    }

    class DataChangeListner implements ValueEventListener {

        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            Log.i(TAG, "FirebaseModelImpl >> getAllTripLisnter >> onDataChange: ");
            // it's important to clear the list before retrive data so can n't appand to list
            // and return to view a huge elements
            tripsReturned.clear();
            rightTrips.clear();
            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                tripsReturned.add(snapshot.getValue(Trip.class));
                //   Log.i(TAG, "onDataChange: tripName  >> " +snapshot.getValue(Trip.class).getName());
            }

            Log.i(TAG, "FirebaseModelImpl >> onDataChange:  " +
                    " listSize >> " + tripsReturned.size());

            if (upcomingTripLisnter != null) {
//                Log.i(TAG, "FirebaseModelImpl >> onDataChange * upcomingTripLisnter != null* ");
                rightTrips = databaseHelper.getSelectedList(tripsReturned, Uitiles.STATUS_UPCOMING);
                upcomingTripLisnter.getUpcomingTripsComplete(rightTrips);
                Log.i(TAG, "FirebaseModelImpl + upcomingTripLisnter != null*  + rightTripListSize() " +
                        rightTrips.size());
            } else if (saveTripListener != null) {
                Log.i(TAG, "FirebaseModelImpl >> onDataChange * saveTripListener != null* ");
                // send status to view via presenter
                saveTripListener.onFinishedSaved("Trip Added Successfully");
            } else if (updateTripLisnter != null) {
                Log.i(TAG, "FirebaseModelImpl >> onDataChange * updateTripLisnter != null* ");
                // send status to view via presenter
                updateTripLisnter.updatedTripComplete("Trip Updated Successfully");
            } else if (historyTripsLisntnener != null) {
                // if satatus not Upcomming will return HistoryTrips
                Log.i(TAG, "FirebaseModelImpl >> onDataChange * historyTripsLisntnener != null* ");
                rightTrips = databaseHelper
                        .getSelectedList(tripsReturned, "any");
                // send trips to view via presenter
                historyTripsLisntnener.getHistoryTripsCompelet(rightTrips);
                Log.i(TAG, "FirebaseModelImpl + historyTripsLisntnener: rightTripListSize() " +
                        rightTrips.size());
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            Log.i(TAG, "loadPost:onCancelled", databaseError.toException());
        }
    }

}
