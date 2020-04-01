package com.example.trioplanner;

public class comments {



// "-M3hJ0NJTZo_IpcsenSn"

// TODO make save take an if and if is null generate id and save else take that id and save it



//                Intent addTripIntent =
    // Intent intentEditViewTrip = new Intent(context, ViewEditTrip.class);
    //    intent.setClass(context, SinglePersonActivity.class);
    //   Intent intent = new Intent(className);
    // profile = (ProfileClass) intent.getSerializableExtra(MyAdapter.PASS_PROFILE_ACTIVITY);
    //intentEditViewTrip.putExtra(KEY_PASS_TRIP, aTrip);
    //context.startActivity(intentEditViewTrip);

    // Toast.makeText(context, "name is >> " + aTrip.getName(), Toast.LENGTH_SHORT).show();

/*
    Trip trip = new Trip("data viet",
            "bahary",
            "sidi bish",
            "15/9/2012",
            "dad visit",
            "one way",
            "buy some fruit",
            "upcomming");*/

    //  homePresenter.onSaveTrip(trip);
    // take the trip that i want to updated
    //  homePresenter.onUpdateTrip(trip.getId(),trip);

    /*

 // just test
        names = new ArrayList<>();
        names.add("ali");
        names.add("hossam");
        names.add("amr");
        names.add("hend");
        names.add("hend");
        names.add("hend");
        names.add("hend");
        names.add("hend");

           // Save Works
        // dummy data
       Trip trip = new Trip("data viet",
                "bahary" ,
                "sidi bish",
                "15/9/2012" ,
                "dad visit",
                "one way",
                "buy some fruit" ,
                "upcomming");

        homePresenter.onSaveTrip(trip);

    0error.team.2.0.1.9@gmail.com
    0errorteam2020


        scoresRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, String previousChild) {
                Log.i(TAG, "The " + snapshot.getKey() + " dinosaur's score is " + snapshot.getValue());
                Log.i(TAG, "onChildAdded: ");
            }

            // [START_EXCLUDE]
            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                Log.i(TAG, "onChildRemoved: ");
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.i(TAG, "onChildMoved: ");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.i(TAG, "onCancelled: ");
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.i(TAG, "onChildChanged: ");
            }
            // [END_EXCLUDE]
        });
        // [END rtdb_query_recent_scores]        Assume that the user loses connection, goes offline, and restarts the app.
         While still offline, the app queries for the last two items from the same location.
         This query will successfully return the last two items
         because the app had loaded all four items in the query above.


        / [START rtdb_query_recent_scores_overlap]
        scoresRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, String previousChild) {
                Log.d(TAG, "The " + snapshot.getKey() + " dinosaur's score is " + snapshot.getValue());
            }

            // [START_EXCLUDE]
            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                Log.i(TAG, "onChildRemoved: ");
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.i(TAG, "onChildMoved: ");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.i(TAG, "onCancelled: ");
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.i(TAG, "onChildChanged: ");
            }
            // [END_EXCLUDE]
        });
        // [END rtdb_query_recent_scores_overlap]



  //  for (Trip t : trips) {
//        Log.i(TAG, "a trip is >>  " + t.toString());
    }

 Random random = new Random();
    int ranInt = random.nextInt(20);
    Log.i(TAG, "FirebaseModelImpl: " + "hossam"+ranInt);

    getSupportActionBar().setLogo(R.mipmap.ic_launcher);
getSupportActionBar().setDisplayShowTitleEnabled(false);


                /*    Snackbar.make(recyclerView, "are you sure delete " + deletedTrip.getName(), Snackbar.LENGTH_LONG)
                            .setAction("Undo", v -> {
                                Log.i(TAG, "onSwiped: from Snackbar");
                                isUndoClickedNotClicked = false;
                                // user click on undo
                                tripsList.add(tripIndex, deletedTrip);
                                homeAdapter.notifyItemInserted(tripIndex);
                            }).show();

//                    if (isUndoClickedNotClicked) {
//                        Log.i(TAG, "onSwiped: from isUndoClickedNotClicked");
//                        // TODO delete from database by id
//                        homePresenter.onDeleteTrip(deletedTrip.getId());
//                        //Log.i(TAG, "onSwiped: the trip deleted from fb> " + deletedTrip.getName());
//                       // Toast.makeText(Home.this, deletedTrip.getName() + " delet succfully", Toast.LENGTH_SHORT).show();
//                    }



            android:label="@string/upcoming_activity_title" >



 /**
         * Creates a Callback for the given drag and swipe allowance. These values serve as
         * defaults
         * and if you want to customize behavior per ViewHolder, you can override
         * {@link #getSwipeDirs(RecyclerView, ViewHolder)}
         * and / or {@link #getDragDirs(RecyclerView, ViewHolder)}.
         *
         * @param dragDirs  Binary OR of direction flags in which the Views can be dragged. Must be
         *                  composed of {@link #LEFT}, {@link #RIGHT}, {@link #START}, {@link
         *                  #END},
         *                  {@link #UP} and {@link #DOWN}.
         * @param swipeDirs Binary OR of direction flags in which the Views can be swiped. Must be
         *                  composed of {@link #LEFT}, {@link #RIGHT}, {@link #START}, {@link
         *                  #END},
         *                  {@link #UP} and {@link #DOWN}.
         */

/*        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            boolean isUndoClicked = false;

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int postion = viewHolder.getAdapterPosition();
                switch (direction) {
                    case ItemTouchHelper.LEFT:
                        // store the trip that user wan to delet
                        deletedTrip = tripsList.get(postion);
                        tripsList.remove(postion);
                        homeAdapter.notifyItemRemoved(postion);
                        Snackbar.make(recyclerView, "are you sure delete " + deletedTrip.getName(), Snackbar.LENGTH_LONG)
                                .setAction("Undo", v -> {
                                    isUndoClicked = true;
                                    // user click on undo
                                    tripsList.add(postion, deletedTrip);
                                    homeAdapter.notifyItemInserted(postion);
                                }).show();
                        Toast.makeText(Home.this, "DeleteTrip", Toast.LENGTH_SHORT).show();
                        Log.i(TAG, "onSwiped: delete + pos " + postion);
                        if (!isUndoClicked) {
                            // TODO delete from database by id
                            Log.i(TAG, "onSwiped: deleteTripId >> " + deletedTrip.getName());
                            Toast.makeText(Home.this, deletedTrip.getName() + " delet succfully", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case ItemTouchHelper.RIGHT:
                        *//*
                            hold id of it then
                            trip delete from list

                            update it's status by id when click wheather start or canceled
                         *//*
                        tripsList.remove(postion);
                        homeAdapter.notifyItemRemoved(postion);

                        // TODO just leave it for now until

                        Intent intent = new Intent(Home.this, MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(Home.this, "Start Trip ", Toast.LENGTH_SHORT).show();
                        Log.i(TAG, "onSwiped: start pos " + postion);
                        break;
                }
            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView,
                                    @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY,
                                    int actionState,
                                    boolean isCurrentlyActive) {
                new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                        .addSwipeLeftBackgroundColor(ContextCompat.getColor(Home.this, R.color.deleteColor))
                        .addSwipeLeftActionIcon(R.drawable.ic_delete_black_24dp)
                        .addSwipeRightBackgroundColor(ContextCompat.getColor(Home.this, R.color.startColor))
                        .addSwipeRightActionIcon(R.drawable.ic_play_arrow_black_24dp)
                        .create()
                        .decorate();
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState,
                        isCurrentlyActive);
            }
        };*/


    /**
     * /**
     *      * Firebase apps automatically handle temporary network interruptions.
     *      * Cached data is available while offline and Firebase resends any writes
     *      * when network connectivity is restored.
     *      * When you enable disk persistence, your app writes the data locally to the device
     *      * so your app can maintain state while offline,
     *      * even if the user or operating system restarts the app.
     *      * You can enable disk persistence with just one line of code.
     *      *
      /**
      *      * The Firebase Realtime Database synchronizes and stores a local copy of the data
      *      * for active listeners. In addition, you can keep specific locations in sync.
      *      *

    */

    /*


   /* public void queryRecentScores() {
        Log.i(TAG, "OfflinebeFirebase >> first of queryRecentScores: ");
        // [START rtdb_query_recent_scores]
        DatabaseReference scoresRef = FirebaseDatabase.getInstance().getReference(mAuth.getUid());
        Offline offline = new Offline();

        scoresRef.addChildEventListener(offline);
      }*/
    /*
    public void onDisconnect() {
        // [START rtdb_on_disconnect_set]
        DatabaseReference presenceRef = FirebaseDatabase.getInstance()
                .getReference("disconnectmessage");
        // Write a string when this client loses connection
        presenceRef.onDisconnect().setValue("I disconnected!");
        // [END rtdb_on_disconnect_set]

        // [START rtdb_on_disconnect_remove]
        presenceRef.onDisconnect().removeValue(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError error, @NonNull DatabaseReference reference) {
                if (error != null) {
                    Log.d(TAG, "could not establish onDisconnect event:" +
                            error.getMessage());
                }
            }
        });
        Log.i(TAG, "OfflinebeFirebase >> onDisconnect: ");
    }

    public void getConnectionState() {
        // [START rtdb_listen_connected]
        DatabaseReference connectedRef = FirebaseDatabase.getInstance().getReference(".info/connected");
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
        // [END rtdb_listen_connected]
        Log.i(TAG, "OfflinebeFirebase >> getConnectionState: ");
    }


    */

    /*
     /*
        offline.keepSynced();
        offline.queryRecentScores();
        offline.onDisconnect();
        offline.getConnectionState();
        */
        /*
        dbReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.i(TAG, "FirebaseModelImpl >> getAllTripLisnter >> onDataChange: ");
                // it's important to clear the list before retrive data so can n't appand to list
                // and return to view a huge elements
                tripsReturned.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    tripsReturned.add(snapshot.getValue(Trip.class));
                    //   Log.i(TAG, "onDataChange: tripName  >> " +snapshot.getValue(Trip.class).getName());
                }

                Log.i(TAG, "FirebaseModelImpl >> onDataChange:  " +
                        " listSize >> " + tripsReturned.size());


                if (getAllTripLisnter != null) {
                  //  Log.i(TAG, "onDataChange*getAllTripLisnter != null* ");
                    getAllTripLisnter.onGetAllTripsComplete(tripsReturned);

                } else if (saveTripListener != null) {
                    Log.i(TAG, "FirebaseModelImpl >> onDataChange*saveTripListener != null* ");
                    saveTripListener.onFinishedSaved("Success");
                }
                   //  Log.i(TAG, "FirebaseModelImpl >> onDataChange: tripsReturnedSize >>  " + tripsReturned.size());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.i(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        });
            */

}
