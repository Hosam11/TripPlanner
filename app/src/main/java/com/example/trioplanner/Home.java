package com.example.trioplanner;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.trioplanner.FirebaseDBOperation.FirebaseModelImpl;
import com.example.trioplanner.FirebaseDBOperation.HomeContract;
import com.example.trioplanner.FirebaseDBOperation.HomePresenterImpl;
import com.example.trioplanner.data.Trip;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;


public class Home extends AppCompatActivity implements HomeContract.HomeView {

    private static final String TAG = "hproj";
    // recycleView
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    HomeAdapter homeAdapter;

    // dummy data fro reyclr view
//    List<String> names;
//    String deleteName;

    // action when Undo delete trip
    List<Trip> tripsList;
    Trip deletedTrip;

    // navigation header
    TextView tvNavName;
    TextView tvNavEmail;
    // MVP
    HomeContract.HomePresenter homePresenter;
    ProgressBar progressBar;
    // nav drawer
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationView navigationView;
    private View navHeader;
    ImageView ivNavHeader;
    // toolBarAction
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        tripsList = new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.rvHome);
        mToolbar = findViewById(R.id.toolbar);

        progressBar = findViewById(R.id.progressBar);


        // Navigation Drawer
        drawerLayout = findViewById(R.id.drawer);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //  you must use that coz i set app them to no action theme
        getSupportActionBar().setTitle("Upcoming");

        // #>>  Start Navigation Drawer
        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, mToolbar, R.string.Open, R.string.Close);
        mDrawerToggle.syncState();
        navigationView = findViewById(R.id.nv);

        // Navigation view header
        navHeader = navigationView.getHeaderView(0);
        tvNavName = navHeader.findViewById(R.id.tvNavName);
        tvNavEmail = navHeader.findViewById(R.id.tvNavEmail);
         ivNavHeader = navHeader.findViewById(R.id.img_header_bg);
//        String url = "https://static.asianetnews.com/images/01dbf5q7xb1jhrrvdvfg1fgn3z/Arya-Stark_300x250xt.jpg";

//        Glide.with(this).load(url).into(ivPhoto);
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            switch (id) {
                case R.id.nav_upcoming:
                    Log.i(TAG, "onCreate:  Upc ");
                    Toast.makeText(Home.this, "Upc", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.nav_History:
                    Toast.makeText(Home.this, "Settings", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.nav_sync:
                    Toast.makeText(Home.this, "Sync", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.nav_log_out:
                    Toast.makeText(Home.this, "log", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.nav_map:
                    Toast.makeText(this, "Map", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    return true;
            }
            return true;
        });
        tvNavName.setText("ali");
        tvNavEmail.setText("hello@example.com");

        // #>> End Navigation Drawer


        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView

        //  #>> Start Handler RecycleView
        // custom line sperator
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);

        recyclerView.setLayoutManager(linearLayoutManager);

        DividerItemDecoration verticalDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);

        Drawable verticalDivider = ContextCompat.getDrawable(this, R.drawable.vertical_divider);
        verticalDecoration.setDrawable(verticalDivider);

        recyclerView.addItemDecoration(verticalDecoration);

        homeAdapter = new HomeAdapter(this);

        recyclerView.setAdapter(homeAdapter);
        // handle swiping legt and right
        ItemTouchHelp itemTouchHelp = new ItemTouchHelp(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(itemTouchHelp);
        itemTouchhelper.attachToRecyclerView(recyclerView);

        // #>> End Handler RecycleView

        // >> MVP
        homePresenter = new HomePresenterImpl(new FirebaseModelImpl(), this);

        homePresenter.onGetAllTrips();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item))
            return true;
        switch (item.getItemId()) {
            case R.id.barBtnAdd:
                // TODO change that by add activity
//                Intent intent = new Intent(Home.this, MainActivity.class);
//                startActivity(intent);
                Toast.makeText(this, "add trip", Toast.LENGTH_SHORT).show();
                // Save Works
                // dummy data
                Trip trip = new Trip("data viet",
                        "bahary",
                        "sidi bish",
                        "15/9/2012",
                        "dad visit",
                        "one way",
                        "buy some fruit",
                        "upcomming");
                homePresenter.onSaveTrip(trip);
                // take the trip that i want to updated
                //  homePresenter.onUpdateTrip(trip.getId(),trip);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onTripSaveSuccess(String state) {
        Log.i(TAG, "onTripSaveSuccess: ");
    }

    @Override
    public void onTripSaveFailed(String state) {
        Log.i(TAG, "onTripSaveFailed: ");
    }

    @Override
    public void getAllTrips(List<Trip> trips) {
        // revive in that value coz i need to use it in swip delete
        if (tripsList != null) {
            Log.i(TAG, "getAllTrips: TripsList !=null ");
            tripsList.clear();
        }
        for (Trip t : trips) {
            tripsList.add(t);
        }
        homeAdapter.setTripsList(tripsList);
        homeAdapter.notifyDataSetChanged();
        Log.i(TAG, "setTripsToList: trips.size " + tripsList.size());
        Log.i(TAG, "setTripsToList: trips.size " + trips.size());

    }


    class ItemTouchHelp extends ItemTouchHelper.SimpleCallback {

        boolean isUndoClickedNotClicked = true;


        public ItemTouchHelp(int dragDirs, int swipeDirs) {
            super(dragDirs, swipeDirs);
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            new RecyclerViewSwipeDecorator.Builder(c,
                    recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addSwipeLeftBackgroundColor(ContextCompat.getColor(Home.this, R.color.deleteColor))
                    .addSwipeLeftActionIcon(R.drawable.ic_delete_black_24dp)
                    .addSwipeRightBackgroundColor(ContextCompat.getColor(Home.this, R.color.startColor))
                    .addSwipeRightActionIcon(R.drawable.ic_play_arrow_black_24dp)
                    .create()
                    .decorate();
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState,
                    isCurrentlyActive);
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            // index of selected trip
            int tripIndex = viewHolder.getAdapterPosition();
            switch (direction) {
                case ItemTouchHelper.LEFT:
                    // store the trip that user wan to delet
                    deletedTrip = tripsList.get(tripIndex);
                    tripsList.remove(tripIndex);
                    homeAdapter.notifyItemRemoved(tripIndex);
                    Snackbar.make(recyclerView, "are you sure delete", Snackbar.LENGTH_SHORT)
                            .setAction("Undo", v -> {
                                Log.i(TAG, "onSwiped: ");
                                // user click on undo
                                tripsList.add(tripIndex, deletedTrip);
                                homeAdapter.notifyItemInserted(tripIndex);
                                isUndoClickedNotClicked = false;
                            }).setCallback(new Snackbar.Callback() {
                        @Override
                        public void onDismissed(Snackbar snackbar, int event) {
                            Log.i(TAG, "onDismissed: ");
                            // delet frow ui as well fb
                            if (isUndoClickedNotClicked) {
                                Log.i(TAG, "onDismissed: from isUndoClickedNotClicked");
                                homePresenter.onDeleteTrip(deletedTrip.getId());
                            }
                        }
                    }).show();

                    break;
                case ItemTouchHelper.RIGHT:
                        /*
                            hold id of it then
                            trip delete from list
                            update it's status by id when click wheather start or canceled
                         */
                    tripsList.remove(tripIndex);
                    homeAdapter.notifyItemRemoved(tripIndex);

                    // TODO just leave it for now until

                    Intent intent = new Intent(Home.this, MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(Home.this, "Start Trip ", Toast.LENGTH_SHORT).show();
                    Log.i(TAG, "onSwiped: start pos " + tripIndex);
                    break;
            }


        }

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder,
                              @NonNull RecyclerView.ViewHolder target) {
            return false;
        }
    }
}





