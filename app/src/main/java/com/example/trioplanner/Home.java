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
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;


public class Home extends AppCompatActivity {

    private static final String TAG = "hproj";
    // recycleView
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    HomeAdapter homeAdapter;

    List<String> names;
    String deleteName;
    // recycleView
    TextView tvNavName;
    TextView tvNavEmail;
    // nav drawer
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationView navigationView;

    private View navHeader;
    // nav drawer
    private Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.rvHome);
        mToolbar = findViewById(R.id.toolbar);
        // Navigation Drawer
        drawerLayout = findViewById(R.id.drawer);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        // TODO you must use that coz i set app them to no actice theme
        getSupportActionBar().setTitle("Upcoming");

        // #>>  Start Navigation Drawer

        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, mToolbar, R.string.Open, R.string.Close);
        mDrawerToggle.syncState();

        navigationView = findViewById(R.id.nv);
        // Navigation view header
        navHeader = navigationView.getHeaderView(0);

        tvNavName = navHeader.findViewById(R.id.tvNavName);
        tvNavEmail = navHeader.findViewById(R.id.tvNavEmail);
        ImageView ivPhoto = navHeader.findViewById(R.id.img_header_bg);
        String url = "https://static.asianetnews.com/images/01dbf5q7xb1jhrrvdvfg1fgn3z/Arya-Stark_300x250xt.jpg";
        Glide.with(this).load(url)
                .into(ivPhoto);
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
                default:
                    return true;
            }
            return true;
        });
        tvNavName.setText("ali");
        tvNavEmail.setText("hello@example.com");
        // #>> End Navigation Drawer


        // TODO sadasd
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

        // #>> End Handler RecycleView

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
                Intent intent = new Intent(Home.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(this, "add trip", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}



