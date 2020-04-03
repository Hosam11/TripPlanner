package com.example.trioplanner.histroy_view;

import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trioplanner.FirebaseDBOperation.FirebaseModelImpl;
import com.example.trioplanner.FirebaseDBOperation.HomeContract;
import com.example.trioplanner.FirebaseDBOperation.HomePresenterImpl;
import com.example.trioplanner.R;
import com.example.trioplanner.Uitiles;
import com.example.trioplanner.data.Trip;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

import static com.example.trioplanner.Uitiles.TAG;


public class HistroyView extends AppCompatActivity implements HomeContract.HistoryView {

    // recycleView
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    HistroyAdapter histroyAdapter;
    TextView empty_view;

    // action when Undo delete trip
    List<Trip> tripsList;
    Trip deletedTrip;

    ProgressBar progressBar;

    HomeContract.HomePresenter homePresenter;


    // toolBarAction
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_layout);
//        setContentView(R.layout.);

        tripsList = new ArrayList<>();

        empty_view = findViewById(R.id.empty_view);

        recyclerView = findViewById(R.id.rvHistory);

        progressBar = findViewById(R.id.progressBar);
        mToolbar = findViewById(R.id.toolbar2);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //  you must use that coz i set app them to no action theme
        getSupportActionBar().setTitle("History Trips");

        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);

        recyclerView.setLayoutManager(linearLayoutManager);

        histroyAdapter = new HistroyAdapter(this);

        recyclerView.setAdapter(histroyAdapter);
        // handle swiping legt and right
        HistroyView.ItemTouchHelpSwipe itemTouchHelp = new
                HistroyView.ItemTouchHelpSwipe(0,
                ItemTouchHelper.LEFT);

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(itemTouchHelp);
        itemTouchhelper.attachToRecyclerView(recyclerView);

        // >> MVP
        homePresenter = new HomePresenterImpl(new FirebaseModelImpl(), this);

        // TODO add contion chech connectivity
        if (Uitiles.checkInternetState(this)) {
            homePresenter.onGetHistoryTrips();
        } else {
            // TODO 4- salah get from room db
        }


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
    public void getHistoryTrips(List<Trip> historyTrips) {
        // revive in that value coz i need to use it in swip delete
        if (tripsList != null) {
            Log.i(TAG, "History >> getAllTrips: TripsList !=null ");
            tripsList.clear();
        }
        for (Trip t : historyTrips) {
            tripsList.add(t);
        }
        if (historyTrips.isEmpty()) {
            recyclerView.setVisibility(View.INVISIBLE);
            empty_view.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            empty_view.setVisibility(View.INVISIBLE);
        }
        histroyAdapter.setTripsList(tripsList);
        histroyAdapter.notifyDataSetChanged();
    }

    class ItemTouchHelpSwipe extends ItemTouchHelper.SimpleCallback {

        boolean isUndoClickedNotClicked = true;


        public ItemTouchHelpSwipe(int dragDirs, int swipeDirs) {
            super(dragDirs, swipeDirs);
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            new RecyclerViewSwipeDecorator.Builder(c,
                    recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addSwipeLeftBackgroundColor(ContextCompat.getColor(HistroyView.this, R.color.deleteColor))
                    .addSwipeLeftActionIcon(R.drawable.ic_delete_black_24dp)
                    .addSwipeRightBackgroundColor(ContextCompat.getColor(HistroyView.this, R.color.startColor))
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
                    histroyAdapter.notifyItemRemoved(tripIndex);
                    Snackbar.make(recyclerView, "are you sure delete", Snackbar.LENGTH_SHORT)
                            .setAction("Undo", v -> {
                                Log.i(TAG, "onSwiped: ");
                                // user click on undo
                                tripsList.add(tripIndex, deletedTrip);
                                histroyAdapter.notifyItemInserted(tripIndex);
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
            }

        }

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder,
                              @NonNull RecyclerView.ViewHolder target) {
            return false;
        }
    }


}
