package com.example.trioplanner;

public class comments {

    //TODO handle internet connection or later user offline
    /* TODO in model firebase make two method 1 return all upcoming trips
        and anthor to return the rest of trips
    */
    // TODO display message when saved success
    // TODO add maps to open all map in google map

// "-M3hJ0NJTZo_IpcsenSn"

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



}
