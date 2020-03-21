package com.example.trioplanner;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

import static androidx.recyclerview.widget.ItemTouchHelper.ACTION_STATE_SWIPE;


public class Home extends AppCompatActivity {

    private static final String TAG = "hproj";
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    HomeAdapter homeAdapter;

    List<String> names;
    String deleteName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.rvHome);


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
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        // custom line sperator
        DividerItemDecoration verticalDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);

        Drawable verticalDivider = ContextCompat.getDrawable(this, R.drawable.vertical_divider);
        verticalDecoration.setDrawable(verticalDivider);

        recyclerView.addItemDecoration(verticalDecoration);


        homeAdapter = new HomeAdapter(this, names);
        recyclerView.setAdapter(homeAdapter);

        deleteName = null;

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int postion = viewHolder.getAdapterPosition();
                switch (direction) {
                    case ItemTouchHelper.LEFT:
                        //
                        deleteName = names.get(postion);
                        names.remove(postion);
                        homeAdapter.notifyItemRemoved(postion);
                        Snackbar.make(recyclerView, "are you sure delete " + deleteName, Snackbar.LENGTH_LONG)
                                .setAction("Undo", v -> {
                                    names.add(postion, deleteName);
                                    homeAdapter.notifyItemInserted(postion);
                                }).show();
                        Toast.makeText(Home.this, "DeleteTrip", Toast.LENGTH_SHORT).show();
                        Log.i(TAG, "onSwiped: delete + pos " + postion);
                        break;
                    case ItemTouchHelper.RIGHT:
                        //
                        names.remove(postion);
                        homeAdapter.notifyItemRemoved(postion);
                        Intent intent = new Intent(Home.this, MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(Home.this, "Start Trip ", Toast.LENGTH_SHORT).show();
                        Log.i(TAG, "onSwiped: start pos " + postion);
                        break;
                }
            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
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
        };

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(simpleCallback);
        itemTouchhelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.barBtnAdd:
                Intent intent = new Intent(Home.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(this, "add trip", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}



