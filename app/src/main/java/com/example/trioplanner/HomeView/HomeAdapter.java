package com.example.trioplanner.HomeView;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trioplanner.MainActivity;
import com.example.trioplanner.R;
import com.example.trioplanner.ViewEditTrip;
import com.example.trioplanner.data.Trip;

import java.util.ArrayList;
import java.util.List;

import static com.example.trioplanner.Uitiles.KEY_PASS_TRIP;
import static com.example.trioplanner.Uitiles.TAG;


public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {

    Context context;
   // List<String> names;
    List<Trip> trips = new ArrayList<>();

//    public HomeAdapter(Context context , List<String> names) {
//        this.context = context;
//        this.names = names;
//    }

    public HomeAdapter(Context context) {
        this.context = context;
    }

    public void setTripsList(List<Trip> trips) {
        this.trips = trips;
        Log.i(TAG, "HomeAdapter >> setTripsList: tripsSize()"+ trips.size());
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.single_row_home, parent, false);
        HomeViewHolder viewHolder = new HomeViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        holder.tvTripName.setText(trips.get(position).getName());
        holder.tvTripDate.setText(trips.get(position).getDate());
        holder.tvTripTime.setText(trips.get(position).getTime());
        holder.tvTripStartLoc.setText(trips.get(position).getStartLoc());
        holder.tvTripEndLoc.setText(trips.get(position).getEndLoc());

        Trip aTrip = trips.get(position);

        holder.consSingleRow.setOnClickListener(v -> {
//                Intent addTripIntent =
            Intent intentEditViewTrip = new Intent(context, ViewEditTrip.class);
            //    intent.setClass(context, SinglePersonActivity.class);
            //   Intent intent = new Intent(className);
            // TODO profile = (ProfileClass) intent.getSerializableExtra(MyAdapter.PASS_PROFILE_ACTIVITY);
            intentEditViewTrip.putExtra(KEY_PASS_TRIP, aTrip);
            context.startActivity(intentEditViewTrip);

            // Toast.makeText(context, "name is >> " + aTrip.getName(), Toast.LENGTH_SHORT).show();
        });

        // if Needed
        holder.consSingleRow.setOnLongClickListener(v -> {
           // Intent intent = new Intent(context, MainActivity.class);
           // context.startActivity(intent);
           // Toast.makeText(context, "long clicked edit trip ", Toast.LENGTH_SHORT).show();
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return trips.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder {

        View consSingleRow;
        TextView tvTripName;
        TextView tvTripDate;
        TextView tvTripTime;
        TextView tvTripStartLoc;
        TextView tvTripEndLoc;

        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTripName = itemView.findViewById(R.id.tvTripName);
            tvTripDate = itemView.findViewById(R.id.tvTripDate);
            tvTripTime = itemView.findViewById(R.id.tvTripTime);
            tvTripStartLoc = itemView.findViewById(R.id.tvStartLoc);
            tvTripEndLoc = itemView.findViewById(R.id.tvEndLoc);

            consSingleRow = itemView.findViewById(R.id.singleRowHome);
        }
    }

}
